package cz.csas.scenarios.model;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public enum EventType {

    API("API"),
    LOAD_URI("LoadURI"),
    FORM("Form"),
    CUSTOM("Custom");

    private String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EventType fromValue(String value) {
        for (EventType type : EventType.values()) {
            if (type.getValue().equals(value))
                return type;
        }
        return null;
    }
}
