package cz.csas.scenarios.model;

import cz.csas.scenarios.error.CsScenariosSDKError;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public interface Callback {

    void success();

    void failure(CsScenariosSDKError error);
}
