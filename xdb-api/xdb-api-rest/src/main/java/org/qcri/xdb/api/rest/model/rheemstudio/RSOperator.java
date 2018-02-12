package org.qcri.xdb.api.rest.model.rheemstudio;

import java.util.List;

public class RSOperator {

    private String name;
    private String java_class;
    private int x;
    private int y;
    private String color;
    private List<RSParameter> parameters;
    private int selectedConstructor;
    private boolean isbroadcast;
    private String type;
    private List<RSConexion> connects_to;
    private List<RSConexion> broadcasts_to;
    private int np_inputs;
    private int np_outputs;

}
