package cz.csas.scenarios.error;

import cz.csas.scenarios.model.Response;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class RestError extends ScenariosSDKError {

    public enum Kind {
        NETWORK,
        HTTP
    }

    private final Kind kind;
    private Response response;

    public RestError(Kind kind, Response response) {
        super();
        this.kind = kind;
        this.response = response;
    }

    public RestError(Kind kind) {
        this.kind = kind;
    }

    public RestError(String message, Throwable cause, Kind kind) {
        super(message, cause);
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

    public Response getResponse() {
        return response;
    }
}
