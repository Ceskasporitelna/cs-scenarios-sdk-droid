package cz.csas.scenarios;

import java.util.ArrayList;

import cz.csas.scenarios.model.Event;
import cz.csas.scenarios.model.Method;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class EventsResource {

    private ScenariosClient mScenarioClient;
    private String mBasePath;

    public EventsResource(String basePath, ScenariosClient restClient) {
        mScenarioClient = restClient;
        mBasePath = basePath;
    }

    public void postSingle(Event event, Callback callback) {
        mScenarioClient.makeWebRequest(appendPathWith("single"), Method.POST, event, callback);
    }

    public void postCollection(ArrayList<Event> events, Callback callback) {
        mScenarioClient.makeWebRequest(appendPathWith("collection"), Method.POST, events, callback);
    }

    public String getBasePath() {
        return mBasePath;
    }

    private String appendPathWith(String appendix){
        if(appendix == null)
            return mBasePath;
        return mBasePath + "/" + appendix;
    }
}
