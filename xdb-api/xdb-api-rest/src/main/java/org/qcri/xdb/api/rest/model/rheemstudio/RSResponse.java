package org.qcri.xdb.api.rest.model.rheemstudio;

import org.qcri.xdb.translate.json.rheemstudio.model.Schema;

import java.util.Date;

public class RSResponse {
    private String name;
    private String date;
    private Schema json;

    public String getName() {
        return name;
    }

    public RSResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public RSResponse setDate() {
        this.date = String.valueOf(new Date());
        return this;
    }

    public Schema getJson() {
        return json;
    }

    public RSResponse setJson(Schema json) {
        this.json = json;
        return this;
    }
}
