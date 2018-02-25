package org.qcri.xdb.translate.json.rheemstudio.model;

public class Query {

    private String name;

    private String rheemlatinQuery;

    public Query() {
    }

    public Query(String name, String rheemlatinQuery) {
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
