package org.qcri.xdb.translate.json.rheemstudio.model;

import java.util.ArrayList;
import java.util.List;

public class Operator {

    private String name;
    private String java_class;
    private int x;
    private int y;
    private String color;
    private List<Parameter> parameters;
    private int selectedConstructor;
    private boolean isbroadcast;
    private String type;
    private List<Conexion> connects_to;
    private List<Conexion> broadcasts_to;
    private int np_inputs;
    private int np_outputs;

    public String getName() {
        return name;
    }

    public Operator setName(String name) {
        this.name = name;
        return this;
    }

    public String getJava_class() {
        return java_class;
    }

    public Operator setJava_class(String java_class) {
        this.java_class = java_class;
        return this;
    }

    public int getX() {
        return x;
    }

    public Operator setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Operator setY(int y) {
        this.y = y;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Operator setColor(String color) {
        this.color = color;
        return this;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public Operator setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public int getSelectedConstructor() {
        return selectedConstructor;
    }

    public Operator setSelectedConstructor(int selectedConstructor) {
        this.selectedConstructor = selectedConstructor;
        return this;
    }

    public boolean isIsbroadcast() {
        return isbroadcast;
    }

    public Operator setIsbroadcast(boolean isbroadcast) {
        this.isbroadcast = isbroadcast;
        return this;
    }

    public String getType() {
        return type;
    }

    public Operator setType(String type) {
        this.type = type;
        return this;
    }

    public List<Conexion> getConnects_to() {
        return connects_to;
    }

    public Operator setConnects_to(List<Conexion> connects_to) {
        this.connects_to = connects_to;
        return this;
    }

    public void addConnects_to(Conexion conexion){
        if(this.connects_to == null){
            this.connects_to = new ArrayList<>();
        }
        this.connects_to.add(conexion);
    }

    public List<Conexion> getBroadcasts_to() {
        return broadcasts_to;
    }

    public Operator setBroadcasts_to(List<Conexion> broadcasts_to) {
        this.broadcasts_to = broadcasts_to;
        return this;
    }

    public int getNp_inputs() {
        return np_inputs;
    }

    public Operator setNp_inputs(int np_inputs) {
        this.np_inputs = np_inputs;
        return this;
    }

    public int getNp_outputs() {
        return np_outputs;
    }

    public Operator setNp_outputs(int np_outputs) {
        this.np_outputs = np_outputs;
        return this;
    }
}
