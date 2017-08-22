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

import cz.csas.scenarios.error.RestError;
import cz.csas.scenarios.model.Method;
import cz.csas.scenarios.model.Response;
import cz.csas.scenarios.model.ResponseEnvelope;
import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class RestClient {

    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String GET = "GET";
    private static final String DELETE = "DELETE";

    private WebApiConfiguration mWebApiConfiguration;
    private HashMap<String, String> mHeaders;

    public RestClient(WebApiConfiguration webApiConfiguration) {
        mWebApiConfiguration = webApiConfiguration;
    }

    public void makeWebRequest(final String path, final Method method, final Object body, final Callback callback) {
        class DownloadTask extends AsyncTask<Void, Void, ResponseEnvelope> {
            @Override
            protected ResponseEnvelope doInBackground(Void... Void) {
                return webRequest(path, method, body);
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

    private ResponseEnvelope webRequest(String path, Method method, Object body) {

        HttpURLConnection connection = null;
        String result = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();

        try {
            URL url = new URL(mWebApiConfiguration.getBaseApiURL() + path);

            if (url.toString().startsWith("https")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }

            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);

            switch (method) {
                case GET:
                    connection.setRequestMethod(GET);
                    break;
                case POST:
                    connection.setRequestMethod(POST);
                    break;
                case PUT:
                    connection.setRequestMethod(PUT);
                    break;
                case DELETE:
                    connection.setRequestMethod(DELETE);
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

            InputStream inputStream = null;

            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                result = readStream(connection.getInputStream());
            } else {
                result = readStream(connection.getErrorStream());
                responseEnvelope.setError(new RestError(RestError.Kind.HTTP, new Response(connection.getResponseCode(), result)));
            }

            connection.connect();

            responseEnvelope.setResponse(new Response(connection.getResponseCode(), result));

        } catch (IOException e) {
            responseEnvelope.setError(new RestError(e.getMessage(), e.getCause(), RestError.Kind.NETWORK));
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return responseEnvelope;
    }

    public void setHeaders(HashMap<String, String> mHeaders) {
        this.mHeaders = mHeaders;
    }

    public void addHeader(String key, String value) {
        if (mHeaders == null) {
            mHeaders = new HashMap<>();
        }

        mHeaders.put(key, value);
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
