package cz.csas.scenarios.model;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class WebApiConfiguration {

    private String mBaseApiURL;
    private String mWebApiKey;
    private String mAuthorizationToken;

    public WebApiConfiguration(String webApiKey, Environment mBaseApiURL, String authorizationToken) {
        this.mBaseApiURL = mBaseApiURL.getUrl();
        mWebApiKey = webApiKey;
        mAuthorizationToken = authorizationToken;
    }

    public WebApiConfiguration(String mBaseApiURL) {
        this.mBaseApiURL = mBaseApiURL;
    }

    public String getBaseApiURL() {
        return mBaseApiURL;
    }

    public String getmWebApiKey() {
        return mWebApiKey;
    }

    public String getmAuthorizationToken() {
        return mAuthorizationToken;
    }
}
