package cz.csas.scenarios;

import java.util.HashMap;

import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class ScenariosClient extends RestClient {

    private final String CLIENT_PATH = "/api/v1/scenarios/";

    private final String HEADER_ACCEPT = "accept";
    private final String HEADER_CONTENT_TYPE = "content-type";
    private final String HEADER_AUTHORIZATION = "authorization";
    private final String HEADER_WEB_API_KEY = "web-api-key";
    private final String HEADER_ACCEPT_LANGUAGE = "accept-language";

    public ScenariosClient(WebApiConfiguration webApiConfiguration) {
        super(webApiConfiguration);
        HashMap<String, String> headers = new HashMap<>();
        headers.put(HEADER_ACCEPT, "application/json;charset=UTF-8");
        headers.put(HEADER_CONTENT_TYPE, "application/json;charset=UTF-8");
        headers.put(HEADER_AUTHORIZATION, webApiConfiguration.getmAuthorizationToken());
        headers.put(HEADER_WEB_API_KEY, webApiConfiguration.getmWebApiKey());
        headers.put(HEADER_ACCEPT_LANGUAGE, "cs-CZ");
        setHeaders(headers);
    }

    public EventsResource getEventsResource() {
        return new EventsResource(CLIENT_PATH + "events", this);
    }

    public String getClientPath() {
        return CLIENT_PATH;
    }
}
