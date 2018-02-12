package org.qcri.xdb.parser.rheemlatin.mapping.transition;

import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;
import org.qcri.xdb.parser.rheemlatin.mapping.MappingFinal;
import org.qcri.xdb.parser.rheemlatin.mapping.OperatorMapping;
import org.qcri.xdb.parser.rheemlatin.mapping.enums.LambdaType;
import org.qcri.xdb.parser.rheemlatin.mapping.enums.OperatorType;

public class OperatorTransition implements MappingTransform{

    private String name;
    private int nfunction;
    private String type;
    private String lambda;
    private String output;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNfunction() {
        return nfunction;
    }

    public void setNfunction(int nfunction) {
        this.nfunction = nfunction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLambda() {
        return lambda;
    }

    public void setLambda(String lambda) {
        this.lambda = lambda;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }


    @Override
    public MappingFinal transform() {
        try {
            return new OperatorMapping(
                    this.name,
                    this.nfunction,
                    OperatorType.find(this.type),
                    (this.lambda != null) ? LambdaType.find(this.lambda) : null,
                    (this.output != null) ? Class.forName(output) : null
            );
        } catch (ClassNotFoundException e) {
            throw new ParserRheemLatinException(e);
        }
    }
}
