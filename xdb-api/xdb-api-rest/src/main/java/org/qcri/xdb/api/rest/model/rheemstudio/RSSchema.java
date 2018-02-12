package org.qcri.xdb.api.rest.model.rheemstudio;

import java.util.List;

public class RSSchema {

    private String name;

    private List<RSConfig> config;

    private List<RSOperator> operators;

    private List<String> sink_operators;

    private List<String> registered_platforms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RSOperator> getOperators() {
        return operators;
    }

    public void setOperators(List<RSOperator> operators) {
        this.operators = operators;
    }
}
