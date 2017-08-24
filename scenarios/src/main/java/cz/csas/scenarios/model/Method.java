package cz.csas.scenarios.model;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 22/08/2017
 */

public enum Method {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String value;

    Method(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

