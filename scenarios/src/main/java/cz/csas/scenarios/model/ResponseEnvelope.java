package cz.csas.scenarios.model;

import cz.csas.scenarios.error.CsScenariosSDKError;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ResponseEnvelope {

    private Response response;
    private CsScenariosSDKError error;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public CsScenariosSDKError getError() {
        return error;
    }

    public void setError(CsScenariosSDKError error) {
        this.error = error;
    }
}
