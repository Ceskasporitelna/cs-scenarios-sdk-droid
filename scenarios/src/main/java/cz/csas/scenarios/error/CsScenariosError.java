package cz.csas.scenarios.error;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class CsScenariosError extends CsScenariosSDKError {

    public enum Kind {

        NOT_INITIALIZED("Scenarios not initialized. Initialization is required before requesting API client.");

        private String message;

        Kind(String message) {
            this.message = message;
        }
    }

    private Kind kind;

    public CsScenariosError(Kind kind) {
        super(kind.message);
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }
}
