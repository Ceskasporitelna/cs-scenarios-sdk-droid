package cz.csas.scenarios.model;

/**
 * Scenarios web api configuration provider
 *
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class WebApiConfiguration {

    private final String CZ_LANGUAGE = "cs-CZ";
    private Environment mEnvironment;
    private String mWebApiKey;
    private String mAuthorizationToken;
    private String mLanguage;

    public WebApiConfiguration(String webApiKey, Environment environment, String language, String authorizationToken) {
        mEnvironment = environment;
        mWebApiKey = webApiKey;
        mLanguage = language;
        mAuthorizationToken = authorizationToken;
    }

    private WebApiConfiguration(Builder builder) {
        mEnvironment = builder.environment;
        mWebApiKey = builder.webApiKey;
        mLanguage = builder.language;
        mAuthorizationToken = builder.authorizationToken;
    }

    /**
     * Configured api environment (base url)
     * default is system test
     *
     * @return environment
     */
    public Environment getEnvironment() {
        if (mEnvironment == null)
            return Environment.SYSTEM_TEST;
        return mEnvironment;
    }

    /**
     * Configured web api key used for api communication
     *
     * @return web api key
     */
    public String getWebApiKey() {
        return mWebApiKey;
    }

    /**
     * Language header used for communication with api
     * default is CZ language
     *
     * @return language
     */
    public String getLanguage() {
        if (mLanguage == null)
            return CZ_LANGUAGE;
        return mLanguage;
    }

    /**
     * Authorization token used for api communication
     *
     * @return token
     */
    public String getAuthorizationToken() {
        return mAuthorizationToken;
    }

    public static class Builder {

        private Environment environment;
        private String webApiKey;
        private String authorizationToken;
        private String language;

        /**
         * Api environment (base url)
         * default is system test
         *
         * @param environment
         * @return builder
         */
        public Builder setEnvironment(Environment environment) {
            this.environment = environment;
            return this;
        }

        /**
         * Web api key used for api communication
         *
         * @param webApiKey
         * @return builder
         */
        public Builder setWebApiKey(String webApiKey) {
            this.webApiKey = webApiKey;
            return this;
        }

        /**
         * Authorization token used for api communication
         *
         * @param authorizationToken
         * @return builder
         */
        public Builder setAuthorizationToken(String authorizationToken) {
            this.authorizationToken = authorizationToken;
            return this;
        }

        /**
         * Language header used for communication with api
         * default is CZ language
         *
         * @param language
         * @return builder
         */
        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        /**
         * Create web api configuration from builder
         *
         * @return web api configuration
         */
        public WebApiConfiguration create() {
            return new WebApiConfiguration(this);
        }

    }
}
