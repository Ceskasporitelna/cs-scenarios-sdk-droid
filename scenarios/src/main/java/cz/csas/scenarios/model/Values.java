package cz.csas.scenarios.model;

import java.util.ArrayList;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class Values {
    private String uri;
    private ArrayList<Account> accounts;

    public Values(String uri, ArrayList<Account> accounts) {
        this.uri = uri;
        this.accounts = accounts;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        uri = uri;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
