package org.qcri.xdb.api.rest.model.rheemstudio;

public class RSQuery {

    private String name;

    private String rheemlatinQuery;

    public RSQuery() {
    }

    public RSQuery(String name, String rheemlatinQuery) {
        this.name = name;
        this.rheemlatinQuery = rheemlatinQuery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRheemlatinQuery() {
        return rheemlatinQuery;
    }

    public void setRheemlatinQuery(String rheemlatinQuery) {
        this.rheemlatinQuery = rheemlatinQuery;
    }
}
