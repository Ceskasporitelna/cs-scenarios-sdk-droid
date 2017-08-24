package cz.csas.scenarios;

import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class ScenariosClient extends RestClient {

    private final String CLIENT_PATH = "/api/v1/scenarios/";
    private final String HEADER_WEB_API_KEY = "web-api-key";

    public ScenariosClient(WebApiConfiguration webApiConfiguration) {
        super(webApiConfiguration);
        addHeader(HEADER_AUTHORIZATION, webApiConfiguration.getmAuthorizationToken());
        addHeader(HEADER_WEB_API_KEY, webApiConfiguration.getmWebApiKey());
        addHeader(HEADER_ACCEPT_LANGUAGE, "cs-CZ");
    }

    public EventsResource getEventsResource() {
        return new EventsResource(CLIENT_PATH + "events", this);
    }

    public String getClientPath() {
        return CLIENT_PATH;
    }
}
