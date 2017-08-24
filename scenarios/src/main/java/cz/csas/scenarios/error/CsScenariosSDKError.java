package cz.csas.scenarios.error;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class CsScenariosSDKError extends RuntimeException {

    public CsScenariosSDKError() {
    }

    public CsScenariosSDKError(String detailMessage) {
        super(detailMessage);
    }

    public CsScenariosSDKError(String message, Throwable cause) {
        super(message, cause);
    }

}
