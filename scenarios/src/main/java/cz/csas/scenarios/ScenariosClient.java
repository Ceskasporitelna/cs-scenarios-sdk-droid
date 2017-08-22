package cz.csas.scenarios;

import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class ScenariosClient extends WebApiClient {

    private final String apiRoot = "api/v1";

    public ScenariosClient(WebApiConfiguration webApiConfiguration) {
        super(webApiConfiguration);
    }

    public EventsResource getEventsResource() {
        return new EventsResource("/" + apiRoot + "/" + "scenarios/events", this);
    }

}
