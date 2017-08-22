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
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import cz.csas.scenarios.model.Response;
import cz.csas.scenarios.model.ResponseContainer;
import cz.csas.scenarios.model.WebApiConfiguration;
import cz.csas.scenarios.model.error.RestError;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class WebApiClient {

    static final String POST = "POST";

    private WebApiConfiguration mWebApiConfiguration;

    public WebApiClient(WebApiConfiguration webApiConfiguration) {
        mWebApiConfiguration = webApiConfiguration;
    }

    public void callApi(final String path, final String method, final Object body, final ApiCallback callback) {

        class DownloadTask extends AsyncTask<Void, Void, ResponseContainer> {
            @Override
            protected ResponseContainer doInBackground(Void... Void) {
                return webRequest(path, method, body);
            }

            @Override
            protected void onPostExecute(ResponseContainer responseContainer) {
                if (responseContainer.getError() == null) {
                    callback.success();
                } else {
                    callback.failure(responseContainer.getError());
                }
            }
        }

        new DownloadTask().execute();
    }

    private ResponseContainer webRequest(String path, String method, Object body) {

        HttpURLConnection connection = null;
        String result = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        ResponseContainer responseContainer = new ResponseContainer();

        try {
            URL url = new URL(mWebApiConfiguration.getBaseApiURL() + path);

            if (url.toString().startsWith("https")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }

            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod(method);

            // Set headers
            for (Map.Entry<String, String> header : mWebApiConfiguration.getHeaders().entrySet()) {
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
                responseContainer.setError(new RestError(RestError.Kind.HTTP, new Response(connection.getResponseCode(), result)));
            }

            connection.connect();

            responseContainer.setResponse(new Response(connection.getResponseCode(), result));

        } catch (IOException e) {
            responseContainer.setError(new RestError(RestError.Kind.NETWORK));
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return responseContainer;
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
