package cz.csas.scenarios;

import java.util.ArrayList;

import cz.csas.scenarios.model.Event;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class EventsResource {

    private WebApiClient mWebApiClient;
    private String mBasePath;

    public EventsResource(String basePath, WebApiClient webApiClient) {
        mWebApiClient = webApiClient;
        mBasePath = basePath;
    }

    public void postSingle(Event event, ApiCallback callback) {
        mWebApiClient.callApi(mBasePath + "/single", WebApiClient.POST, event, callback);
    }

    public void postCollection(ArrayList<Event> events, ApiCallback callback) {
        mWebApiClient.callApi(mBasePath + "/collection", WebApiClient.POST, events, callback);
    }
}
