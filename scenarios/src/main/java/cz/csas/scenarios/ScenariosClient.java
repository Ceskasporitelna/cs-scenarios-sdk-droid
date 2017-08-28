package cz.csas.scenarios;

import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * Scenarios SDK rest client for accessing event resource
 *
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class ScenariosClient extends RestClient {

    private final String CLIENT_PATH = "/api/v1/scenarios";
    private final String HEADER_WEB_API_KEY = "web-api-key";

    public ScenariosClient(WebApiConfiguration webApiConfiguration) {
        super(webApiConfiguration.getEnvironment().getBaseUrl());
        addHeader(HEADER_AUTHORIZATION, webApiConfiguration.getAuthorizationToken());
        addHeader(HEADER_WEB_API_KEY, webApiConfiguration.getWebApiKey());
        addHeader(HEADER_ACCEPT_LANGUAGE, webApiConfiguration.getLanguage());
    }

    /**
     * Get scenarios events resource to create event records
     *
     * @return the events resource
     */
    public EventsResource getEventsResource() {
        return new EventsResource(CLIENT_PATH + "/events", this);
    }

    /**
     * Get rest client path to api
     *
     * @return the client path
     */
    public String getClientPath() {
        return CLIENT_PATH;
    }
}
