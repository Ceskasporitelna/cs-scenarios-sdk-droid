package cz.csas.scenarios.model.error;

import cz.csas.scenarios.model.Response;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class RestError extends BaseScenariosError {

    public enum Kind {
        NETWORK,
        HTTP
    }

    private final Kind kind;
    private Response response;

    public RestError(Kind kind, Response response) {
        this.kind = kind;
        this.response = response;
    }

    public RestError(Kind kind) {
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
