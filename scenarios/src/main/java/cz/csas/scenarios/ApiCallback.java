package cz.csas.scenarios;

import cz.csas.scenarios.model.error.BaseScenariosError;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public interface ApiCallback {

    void success();

    void failure(BaseScenariosError error);
}
