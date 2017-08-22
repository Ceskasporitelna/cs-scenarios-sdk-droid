package cz.csas.scenarios;

import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */
public abstract class Scenarios {

    private static Scenarios sharedInstance;

    public static Scenarios getInstance() {
        if (sharedInstance == null)
            sharedInstance = new ScenariosImpl();
        return sharedInstance;
    }

    public abstract Scenarios init(WebApiConfiguration webApiConfiguration);

    public abstract ScenariosClient getScenariosClient();

}
