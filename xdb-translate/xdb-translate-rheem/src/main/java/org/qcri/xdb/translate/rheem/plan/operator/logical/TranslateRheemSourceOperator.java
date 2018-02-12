package org.qcri.xdb.translate.rheem.plan.operator.logical;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;
import org.qcri.xdb.core.plan.operator.logical.SourceOperator;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.mapping.ParameterType;
import org.qcri.xdb.translate.rheem.plan.operator.TranslateRheemOperator;

public class TranslateRheemSourceOperator extends TranslateRheemOperator {
    private String path_source;

    private String[] name_var;

    private String[] type_var;

    public TranslateRheemSourceOperator(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {
        super.selfCopy(element);
        if( !(element instanceof SourceOperator)){
            throw new TranslateRheemException(
                    String.format(
                            "the class \"%s\" of the [%s]is not extension of the %s",
                            element.getClass(),
                            element,
                            XdbOperator.class
                    )
            );
        }
        SourceOperator operator = (SourceOperator) element;
        this.path_source = operator.getPath_source();
        this.name_var = operator.getName_var();
        this.type_var = operator.getType_var();
    }

    @Override
    public Object[] getParams() {
        Object[] obj = super.getParams();
        int class_count = 0;
        for(int i = 0; i < params.length; i++){
            if(params[i] == ParameterType.STRING){
                obj[i] = this.path_source;
            }
            if(params[i] == ParameterType.STRING_ARRAY) {
                obj[i] = this.name_var;
            }
        }
        return obj;
    }
}
