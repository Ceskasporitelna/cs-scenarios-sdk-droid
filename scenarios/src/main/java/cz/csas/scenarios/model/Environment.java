package cz.csas.scenarios.model;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public enum Environment {

    PRODUCTION("https://www.csas.cz/webapi"),
    SYSTEM_TEST("http://www.csast.csas.cz/webapi"),
    OTHER(null);

    public static Environment other(String url) {
        Environment other = Environment.OTHER;
        other.setUrl(url);
        return other;
    }

    private String url;

    Environment(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
