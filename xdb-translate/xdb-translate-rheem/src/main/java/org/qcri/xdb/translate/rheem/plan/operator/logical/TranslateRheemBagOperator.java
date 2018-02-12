package org.qcri.xdb.translate.rheem.plan.operator.logical;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.expression.RealFunctionExpression;
import org.qcri.xdb.core.plan.operator.logical.BagOperator;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.plan.operator.TranslateRheemOperator;

public class TranslateRheemBagOperator extends TranslateRheemOperator {

    private Object method_parameters;
    public TranslateRheemBagOperator(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {
        super.selfCopy(element);
        if( !(element instanceof BagOperator)){
            //TODO colocar un mensaje mas descriptivo
            throw new TranslateRheemException("no es de la clase que corresponde");
        }
        this.method_parameters = method_parameters;
    }

    @Override
    public Object[] getParams() {
        Object[] obj = super.getParams();
        RealFunctionExpression expre = (RealFunctionExpression) this.inputs.getExpression(0);
        Object[] parameters = expre.getParameters_method();
        obj[0] = TranslateRheemOperator.getLambda(expre.getClass_reference().getName(), expre.getName_method(), parameters);

        obj[1] = this.inputs.getType(0);
        obj[2] = this.outputs.getType(0);


        return obj;
    }
}
