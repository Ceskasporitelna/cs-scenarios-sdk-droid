package cz.csas.scenarios.model;

import cz.csas.scenarios.error.ScenariosSDKError;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ResponseEnvelope {
    private Response response;
    private ScenariosSDKError error;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public ScenariosSDKError getError() {
        return error;
    }

    public void setError(ScenariosSDKError error) {
        this.error = error;
    }
}
