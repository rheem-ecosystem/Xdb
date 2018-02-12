package org.qcri.xdb.parser.rheemlatin.mapping.transition;

import org.qcri.xdb.parser.rheemlatin.mapping.ExpressionMapping;
import org.qcri.xdb.parser.rheemlatin.mapping.MappingFinal;
import org.qcri.xdb.parser.rheemlatin.mapping.enums.ExpressionType;
import org.qcri.xdb.parser.rheemlatin.mapping.enums.ExpressionTypeClass;

public class ExpressionTransition implements MappingTransform{

    private String name;
    private String type;
    private String real_operator;
    private String type_class;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReal_operator() {
        return real_operator;
    }

    public void setReal_operator(String real_operator) {
        this.real_operator = real_operator;
    }

    public String getType_class() {
        return type_class;
    }

    public void setType_class(String type_class) {
        this.type_class = type_class;
    }


    @Override
    public MappingFinal transform() {
        return new ExpressionMapping(
                this.name,
                ExpressionType.find(this.type),
                this.real_operator,
                ExpressionTypeClass.find(this.type_class)
        );
    }
}
