package cz.csas.scenarios.error;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ScenariosSDKError extends RuntimeException {
    ScenariosSDKError(String message, Throwable cause) {
        super(message, cause);
    }

    ScenariosSDKError() {
        super();
    }
}
