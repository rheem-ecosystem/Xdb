package org.qcri.xdb.translate.json.rheemstudio.model;

public class Conexion {
    private String name;

    public Conexion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Conexion setName(String name) {
        this.name = name;
        return this;
    }
}
