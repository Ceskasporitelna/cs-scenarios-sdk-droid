package cz.csas.scenarios.model;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public enum Environment {

    PRODUCTION("https://www.csas.cz/webapi"),
    SYSTEM_TEST("http://www.csast.csas.cz/webapi"),
    OTHER(null);

    public static Environment other(String baseUrl) {
        Environment other = Environment.OTHER;
        other.baseUrl = baseUrl;
        return other;
    }

    private String baseUrl;

    Environment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
