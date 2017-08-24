package cz.csas.scenarios.error;

import java.io.IOException;

import cz.csas.scenarios.model.Response;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class CsRestError extends CsScenariosSDKError {

    public static CsRestError networkError(String url, IOException exception) {
        return new CsRestError(exception.getMessage(), url, null, Kind.NETWORK, exception);
    }

    public static CsRestError httpError(String url, Response response) {
        String message = response.getBody() + " " + response.getCode();
        return new CsRestError(message, url, response, Kind.HTTP, null);
    }

    /**
     * Identifies the event kind which triggered a {@link CsRestError}.
     */
    public enum Kind {
        // An {@link IOException} occurred while communicating to the server.
        NETWORK,
        // A non-200 HTTP status code was received from the server.
        HTTP
    }

    private String url;
    private Response response;
    private Kind kind;

    private CsRestError(String message, String url, Response response, Kind kind, Throwable exception) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code and body.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }
}
