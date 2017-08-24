package cz.csas.scenarios;

import cz.csas.scenarios.error.CsScenariosError;
import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

class ScenariosImpl extends Scenarios {

    private ScenariosClient scenariosClient;

    @Override
    public Scenarios init(WebApiConfiguration webApiConfiguration) {
        scenariosClient = new ScenariosClient(webApiConfiguration);
        return this;
    }

    @Override
    public ScenariosClient getScenariosClient() {
        if (scenariosClient == null) {
            throw new CsScenariosError(CsScenariosError.Kind.NOT_INITIALIZED);
        }
        return scenariosClient;
    }
}
