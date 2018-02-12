package org.qcri.xdb.translate.rheem.context;

import org.qcri.rheem.core.plan.rheemplan.Operator;
import org.qcri.xdb.core.plan.operator.XdbOperator;
import org.qcri.xdb.core.plan.operator.logical.BagOperator;
import org.qcri.xdb.core.plan.operator.logical.ManyOperator;
import org.qcri.xdb.core.plan.operator.logical.SinkOperator;
import org.qcri.xdb.core.plan.operator.logical.SourceOperator;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.mapping.RheemClassMapping;
import org.qcri.xdb.translate.rheem.plan.operator.TranslateRheemOperator;
import org.qcri.xdb.translate.rheem.plan.operator.logical.TranslateRheemBagOperator;
import org.qcri.xdb.translate.rheem.plan.operator.logical.TranslateRheemManyOperator;
import org.qcri.xdb.translate.rheem.plan.operator.logical.TranslateRheemSinkOperator;
import org.qcri.xdb.translate.rheem.plan.operator.logical.TranslateRheemSourceOperator;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class MirrorRheem {
    private HashMap<String, RheemClassMapping> rheemMapping = null;

    public MirrorRheem(){
        this.rheemMapping = new HashMap<>();
    }


    public void put(String key, RheemClassMapping value){
        rheemMapping.put(key, value);
    }

    public Operator getReflexion(XdbOperator operator){
        TranslateRheemOperator traslator = null;
        RheemClassMapping reflex = rheemMapping.get(operator.getName());
        if(operator instanceof ManyOperator){
            traslator = new TranslateRheemManyOperator(operator)
                    .setParams(reflex.getParameterTypes());
        }
        if(operator instanceof SinkOperator){
            traslator = new TranslateRheemSinkOperator(operator)
                    .setParams(reflex.getParameterTypes());
        }
        if(operator instanceof SourceOperator){
            traslator = new TranslateRheemSourceOperator(operator)
                    .setParams(reflex.getParameterTypes());
        }
        if(operator instanceof BagOperator){
            traslator = new TranslateRheemBagOperator(operator)
                    .setParams(reflex.getParameterTypes());
        }
        if(traslator == null){
            throw new TranslateRheemException("the operator not have a mapping");
        }
        Object[] params_real = traslator.getParams();
        Operator rheem_operator = null;
        try {

            rheem_operator = (Operator) reflex.getConstructor().newInstance(params_real);
            rheem_operator.setName(operator.getAlias());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new TranslateRheemException(e);
        }

        return rheem_operator;
    }
}
