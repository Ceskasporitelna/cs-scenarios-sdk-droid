package cz.csas.scenarios;

import java.util.List;

import cz.csas.scenarios.model.Callback;
import cz.csas.scenarios.model.Event;
import cz.csas.scenarios.model.Method;

/**
 * Events resource arrange posting of single event/collection of events
 *
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class EventsResource {

    private ScenariosClient mScenarioClient;
    private String mBasePath;

    /**
     * Instantiates a new Events resource.
     *
     * @param basePath   the base path
     * @param restClient the rest client
     */
    public EventsResource(String basePath, ScenariosClient restClient) {
        mScenarioClient = restClient;
        mBasePath = basePath;
    }

    /**
     * Post single event including all event details
     *
     * @param event    the event
     * @param callback the callback
     */
    public void postSingle(Event event, Callback callback) {
        mScenarioClient.callApi(appendPathWith("single"), Method.POST, event, callback);
    }

    /**
     * Post collection of events.
     *
     * @param events   the events
     * @param callback the callback
     */
    public void postCollection(List<Event> events, Callback callback) {
        mScenarioClient.callApi(appendPathWith("collection"), Method.POST, events, callback);
    }

    /**
     * Get resource base path string.
     *
     * @return the path
     */
    public String getBasePath() {
        return mBasePath;
    }

    private String appendPathWith(String appendix) {
        if (appendix == null)
            return mBasePath;
        return mBasePath + "/" + appendix;
    }
}
