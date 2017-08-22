package cz.csas.scenarios.model;

import java.util.HashMap;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class WebApiConfiguration {

    private String mBaseApiURL;
    private HashMap<String, String> mHeaders;

    private final String HEADER_ACCEPT = "accept";
    private final String HEADER_CONTENT_TYPE = "content-type";
    private final String HEADER_AUTHORIZATION = "authorization";
    private final String HEADER_WEB_API_KEY = "web-api-key";
    private final String HEADER_ACCEPT_LANGUAGE = "accept-language";

    public WebApiConfiguration(String webApiKey, Environment mBaseApiURL, String authorizationToken) {
        this.mBaseApiURL = mBaseApiURL.getUrl();
        mHeaders = new HashMap<String, String>();
        mHeaders.put(HEADER_ACCEPT, "application/json;charset=UTF-8");
        mHeaders.put(HEADER_CONTENT_TYPE, "application/json;charset=UTF-8");
        mHeaders.put(HEADER_AUTHORIZATION, authorizationToken);
        mHeaders.put(HEADER_WEB_API_KEY, webApiKey);
        mHeaders.put(HEADER_ACCEPT_LANGUAGE, "cs-CZ");
    }

    public WebApiConfiguration(String mBaseApiURL, HashMap<String, String> mHeaders) {
        this.mBaseApiURL = mBaseApiURL;
        this.mHeaders = mHeaders;
    }

    public String getBaseApiURL() {
        return mBaseApiURL;
    }

    public HashMap<String, String> getHeaders() {
        return mHeaders;
    }
}
