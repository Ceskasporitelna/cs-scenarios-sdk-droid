package cz.csas.scenarios;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import cz.csas.scenarios.error.CsRestError;
import cz.csas.scenarios.model.Callback;
import cz.csas.scenarios.model.Method;
import cz.csas.scenarios.model.Response;
import cz.csas.scenarios.model.ResponseEnvelope;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

class RestClient {

    protected final String HEADER_ACCEPT = "accept";
    protected final String HEADER_CONTENT_TYPE = "content-type";
    protected final String HEADER_AUTHORIZATION = "authorization";
    protected final String HEADER_ACCEPT_LANGUAGE = "accept-language";

    private String mBaseUrl;
    private HashMap<String, String> mHeaders;

    public RestClient(String baseUrl) {
        mBaseUrl = baseUrl;
        mHeaders = new HashMap<>();
        mHeaders.put(HEADER_ACCEPT, "application/json;charset=UTF-8");
        mHeaders.put(HEADER_CONTENT_TYPE, "application/json;charset=UTF-8");
    }

    public void callApi(final String path, final Method method, final Object body, final Callback callback) {
        class DownloadTask extends AsyncTask<Void, Void, ResponseEnvelope> {
            @Override
            protected ResponseEnvelope doInBackground(Void... Void) {
                return callApi(path, method, body);
            }

            @Override
            protected void onPostExecute(ResponseEnvelope responseContainer) {
                if (responseContainer.getError() == null) {
                    callback.success();
                } else {
                    callback.failure(responseContainer.getError());
                }
            }
        }

        new DownloadTask().execute();
    }

    public void addHeader(String key, String value) {
        if (mHeaders == null) {
            mHeaders = new HashMap<>();
        }
        mHeaders.put(key, value);
    }

    public void setHeaders(HashMap<String, String> mHeaders) {
        this.mHeaders = mHeaders;
    }

    private ResponseEnvelope callApi(String path, Method method, Object body) {

        HttpURLConnection connection = null;
        String result;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        String urlSpec = mBaseUrl + path;

        try {
            URL url = new URL(urlSpec);

            if (url.toString().startsWith("https")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }

            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);

            switch (method) {
                case GET:
                    connection.setRequestMethod(method.name());
                    break;
                case POST:
                    connection.setRequestMethod(method.name());
                    break;
                case PUT:
                    connection.setRequestMethod(method.name());
                    break;
                case DELETE:
                    connection.setRequestMethod(method.name());
                    break;
            }


            // Set headers
            for (Map.Entry<String, String> header : mHeaders.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            // Set body
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            byte[] bytes = gson.toJson(body).getBytes("UTF-8");
            outputStream.write(bytes);
            outputStream.close();

            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                result = readStream(connection.getInputStream());
            } else {
                result = readStream(connection.getErrorStream());
                responseEnvelope.setError(CsRestError.httpError(urlSpec, new Response(connection.getResponseCode(), result)));
            }

            connection.connect();

            responseEnvelope.setResponse(new Response(connection.getResponseCode(), result));

        } catch (IOException exception) {
            responseEnvelope.setError(CsRestError.networkError(urlSpec, exception));
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return responseEnvelope;
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    private String readStream(InputStream stream) throws IOException {
        if (stream == null) {
            return null;
        }
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        stream.close();
        return result.toString("UTF-8");
    }
}
