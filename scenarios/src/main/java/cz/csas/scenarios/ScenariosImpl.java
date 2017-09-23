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
        if (validateWebApiConfiguration(webApiConfiguration))
            scenariosClient = new ScenariosClient(webApiConfiguration);
        else
            throw new CsScenariosError(CsScenariosError.Kind.BAD_INITIALIZATION);
        return this;
    }

    @Override
    public ScenariosClient getScenariosClient() {
        if (scenariosClient == null) {
            throw new CsScenariosError(CsScenariosError.Kind.BAD_INITIALIZATION);
        }
        return scenariosClient;
    }

    private boolean validateWebApiConfiguration(WebApiConfiguration webApiConfiguration) {
        return webApiConfiguration != null &&
                webApiConfiguration.getAuthorizationToken() != null &&
                webApiConfiguration.getWebApiKey() != null;
    }
}
