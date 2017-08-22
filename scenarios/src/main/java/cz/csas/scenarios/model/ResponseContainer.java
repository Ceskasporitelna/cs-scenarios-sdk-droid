package cz.csas.scenarios.model;

import cz.csas.scenarios.model.error.BaseScenariosError;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ResponseContainer {
    private Response response;
    private BaseScenariosError error;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public BaseScenariosError getError() {
        return error;
    }

    public void setError(BaseScenariosError error) {
        this.error = error;
    }
}
