package cz.csas.scenarios;

import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * Scenarios SDK is internal csas api for event mapping and evaluation
 * api: http://docs.ext0csasscenarioseventsv1.apiary.io/#reference/scenarios-events/scenario-events/post-events-collection
 * admin: http://uat1-cdnint.csint.cz/applications/csint/admin/scenarios-admin/#/app/dashboard
 *
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */
public abstract class Scenarios {

    private static Scenarios sharedInstance;

    /**
     * Get instance of Scenarios SDK
     *
     * @return the instance
     */
    public static Scenarios getInstance() {
        if (sharedInstance == null)
            sharedInstance = new ScenariosImpl();
        return sharedInstance;
    }

    /**
     * Initialize Scenarios SDK using required information distributed through configuration object
     *
     * @param webApiConfiguration the web api configuration
     * @return the scenarios
     */
    public abstract Scenarios init(WebApiConfiguration webApiConfiguration);

    /**
     * Get scenarios client to get access to scenarios resources
     *
     * @return the scenarios client
     */
    public abstract ScenariosClient getScenariosClient();

}
