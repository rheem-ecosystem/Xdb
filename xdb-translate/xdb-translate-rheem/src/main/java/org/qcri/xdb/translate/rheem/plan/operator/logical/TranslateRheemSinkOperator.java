package org.qcri.xdb.translate.rheem.plan.operator.logical;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;
import org.qcri.xdb.core.plan.operator.logical.SinkOperator;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.mapping.ParameterType;
import org.qcri.xdb.translate.rheem.plan.operator.TranslateRheemOperator;

public class TranslateRheemSinkOperator extends TranslateRheemOperator {
    private String path_source;

    public TranslateRheemSinkOperator(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {
        super.selfCopy(element);
        if( !(element instanceof SinkOperator)){
            throw new TranslateRheemException(
                    String.format(
                            "the class \"%s\" of the [%s]is not extension of the %s",
                            element.getClass(),
                            element,
                            XdbOperator.class
                    )
            );
        }
        SinkOperator operator = (SinkOperator) element;
        this.path_source = operator.getPath_source();
    }

    @Override
    public Object[] getParams() {
        Object[] obj = super.getParams();
        int class_count = 0;
        for(int i = 0; i < params.length; i++){
            if(params[i] == ParameterType.STRING){
                obj[i] = this.path_source;
            }
            if(params[i] == ParameterType.CLASS){
                if(class_count < this.inputs.getSize()) {
                    obj[i] = this.inputs.getType(class_count);
                    class_count++;
                }else{
                    obj[i] = this.outputs.getType(0);
                }
            }
        }
        return obj;
    }

}
