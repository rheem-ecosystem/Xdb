package org.qcri.xdb.translate.rheem.plan.operator;

import org.qcri.xdb.core.context.XdbContext;
import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.mapping.ParameterType;
import org.qcri.xdb.udf.broadcast.FunctionBroadcast;
import org.qcri.xdb.udf.broadcast.UDFFunctionBroadcast;
import org.qcri.xdb.util.reflexion.ImportClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TranslateRheemOperator extends XdbOperator {

    protected ParameterType[] params = null;


    private static ImportClass IMPORTS;
    static{
        IMPORTS = (ImportClass) XdbContext.getConfiguration("imports");
    }

    public TranslateRheemOperator(String name) {
        super(name);
    }

    public TranslateRheemOperator(XdbElement element) {
        super(element);
    }

    public TranslateRheemOperator setParams(ParameterType[] params){
        this.params = params;
        return this;
    }

    @Override
    protected void selfCopy(XdbElement element) {
        if( !(element instanceof XdbOperator)){
            throw new TranslateRheemException(
                    String.format(
                            "the class \"%s\" of the [%s]is not extension of the %s",
                            element.getClass(),
                            element,
                            XdbOperator.class
                    )
            );
        }
        XdbOperator operator = (XdbOperator) element;
        this.name = operator.getName();
        this.alias = operator.getAlias();
        this.platform = operator.getPlatform();
        this.broadcast = operator.getBroadcast();
        this.broadcast_name = operator.getBroadcastName();
        this.lambdas = operator.getLambdas();
        this.structure_info = operator.getStructure_info();
        this.inputs  = operator.getInputs();
        this.outputs = operator.getOutputs();
    }

    public Object[] getParams(){
        if(this.params == null){
            throw new TranslateRheemException("the paramaterType[] is not initialized");
        }
        return new Object[this.params.length];
    }


    public static Object getLambda(String name, String method_name, Object[] parameter){
        return IMPORTS.getLambda(name, method_name, parameter);
    }

    public static Object getImplementation(Object method, String broadcast){
        try {
            if (broadcast == null)
                return ((Method) method).invoke(null);

            return new UDFFunctionBroadcast(broadcast, (FunctionBroadcast) ((Method) method).invoke(null));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new TranslateRheemException(e);
        }
    }

}
