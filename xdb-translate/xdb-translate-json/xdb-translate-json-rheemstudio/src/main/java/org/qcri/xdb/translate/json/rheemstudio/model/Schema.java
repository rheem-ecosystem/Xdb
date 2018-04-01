package org.qcri.xdb.translate.json.rheemstudio.model;

import java.util.ArrayList;
import java.util.List;

public class Schema {

    private String name;

    private List<Config> config;

    private List<Operator> operators;

    private List<String> sink_operators;

    private List<String> registered_platforms;

    public String getName() {
        return name;
    }

    public Schema setName(String name) {
        this.name = name;
        return this;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public Schema setOperators(List<Operator> operators) {
        this.operators = operators;
        return this;
    }

    public void addOperator(Operator op){
        if(this.operators == null){
            this.operators = new ArrayList<>();
        }
        this.operators.add(op);
    }


    public List<Config> getConfig() {
        return config;
    }

    public Schema setConfig(List<Config> config) {
        this.config = config;
        return this;
    }

    public List<String> getSink_operators() {
        if(this.sink_operators == null){
            this.sink_operators = new ArrayList<>();
        }
        return sink_operators;
    }

    public Schema setSink_operators(List<String> sink_operators) {
        this.sink_operators = sink_operators;
        return this;
    }

    public List<String> getRegistered_platforms() {
        return registered_platforms;
    }

    public Schema setRegistered_platforms(List<String> registered_platforms) {
        this.registered_platforms = registered_platforms;
        return this;
    }
}
