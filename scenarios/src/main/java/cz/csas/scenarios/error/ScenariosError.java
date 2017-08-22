package cz.csas.scenarios.error;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ScenariosError extends ScenariosSDKError {

    public enum Kind {

        NOT_INITIALIZED("Scenerios not initialized. Initialization is required before requesting API client.");

        private String message;

        Kind(String kind) {
            message = kind;
        }
    }

    private final Kind kind;

    public ScenariosError(ScenariosError.Kind kind) {
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }
}
