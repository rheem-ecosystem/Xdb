package org.qcri.xdb.translate.rheem.plan.operator.logical;

import org.qcri.rheem.core.function.FunctionDescriptor;
import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.expression.RealFunctionExpression;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.expression.BuilderExpression;
import org.qcri.xdb.translate.rheem.expression.Expression;
import org.qcri.xdb.translate.rheem.mapping.ParameterType;
import org.qcri.xdb.translate.rheem.plan.operator.TranslateRheemOperator;
import org.qcri.xdb.udf.broadcast.FunctionBroadcast;
import org.qcri.xdb.udf.broadcast.UDFFunctionBroadcast;

public class TranslateRheemManyOperator  extends TranslateRheemOperator {

    public TranslateRheemManyOperator(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {
        super.selfCopy(element);
    }

    @Override
    public Object[] getParams() {
        Object[] obj = super.getParams();
        int class_index = 0;
        int expre_index = 0;
        Class type_return = this.outputs.getType(0);;
        for(int i = 0; i < this.params.length; i++){
            if( params[i] == ParameterType.FUNCTION || params[i] == ParameterType.PREDICATE) {
                System.out.println("lala "+this);
                if(this.inputs.getExpression(expre_index).isImplemented()){
                    RealFunctionExpression expre = (RealFunctionExpression) this.inputs.getExpression(expre_index);
                    Object[] parameters = expre.getParameters_method();
                    Object fun = TranslateRheemOperator.getLambda(expre.getClass_reference().getName(), expre.getName_method(), parameters);

                    if( fun == null ){
                        //TODO: create a exception with good message
                        throw new TranslateRheemException("We have a problem with the implementation "+expre.getName_method()+"  "+expre.getClass_reference().getName());
                    }
                    if(expre.getType_return() != type_return){
                        type_return = expre.getType_return();
                    }

                    //TODO: crear la funcion para mas parametricas para soportar mas de un broadcast
                    if(this.broadcast_name.size() > 0){
                        if(fun instanceof FunctionBroadcast && this.broadcast_name.size() == 1){
                            fun = new UDFFunctionBroadcast(this.broadcast_name.get(0), (FunctionBroadcast) fun);
                        }else{
                            //TODO: create a exception with good message
                            throw new TranslateRheemException("We have a problem with the implementation");
                        }
                    }

                    obj[i] = fun;
                    expre_index++;
                    continue;
                }


                if (params[i] == ParameterType.FUNCTION) {
                    Expression expr = BuilderExpression.builderExpression(this.inputs.getExpression(expre_index));
                    obj[i] = (FunctionDescriptor.SerializableFunction) a -> expr.evaluate(a);
                    expre_index++;
                }
                if (params[i] == ParameterType.PREDICATE) {
                    Expression expr = BuilderExpression.builderExpression(this.inputs.getExpression(expre_index));
                    obj[i] = (FunctionDescriptor.SerializablePredicate) a -> (Boolean) expr.evaluate(a);
                    expre_index++;
                }
            }
            if(params[i] == ParameterType.CLASS){
                if(class_index < this.inputs.getSize()) {
                    obj[i] = this.inputs.getType(class_index);
                    class_index++;
                }else{
                    obj[i] = type_return;
                }
            }
        }

        return obj;
    }
}
