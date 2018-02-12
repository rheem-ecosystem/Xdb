package org.qcri.xdb.core.plan.expression;

import org.qcri.xdb.core.exception.XdbCoreException;
import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.enviroment.ClassEnviroment;
import org.qcri.xdb.udf.broadcast.FunctionBroadcast;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RealFunctionExpression extends XdbExpression{

    private ClassEnviroment class_reference;
    private String name_method;

    private Class type_return;

    private Object[] parameters_method;


    public RealFunctionExpression(String name) {
        super(name);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }

    public  RealFunctionExpression(String name, ClassEnviroment class_reference, String name_method ){
        this(name);
        this.class_reference = class_reference;
        validate(class_reference, name_method);
        this.name_method = name_method;
    }

    public  RealFunctionExpression(String name, ClassEnviroment class_reference, String name_method, Object[] parameters_method ){
        this(name);
        this.class_reference = class_reference;
        validate(class_reference, name_method);
        this.name_method = name_method;
        this.parameters_method = parameters_method;
    }

    //TODO generate the get of the method with reflextion MOVER AL TRANSLATOR;
    /*
    public Object getImplementation(String broadcast_name){
        if(broadcast_name == null)
            return class_reference.getMethod(this.name_method);

        return new UDFunctionBroadcast(broadcast_name, (FunctionBroadcast) class_reference.getMethod(this.name_method));
    }*/

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public boolean hasMoreChildren() {
        return false;
    }

    @Override
    public XdbExpression nextChildren() {
        return null;
    }

    @Override
    public void goFirstChildren() {
        return;
    }

    @Override
    public int countChildren() {
        return 0;
    }

    @Override
    public boolean isImplemented(){
        return true;
    }

    //TODO: mejorar la forma en la que se implemento
    private void validate(ClassEnviroment class_reference, String method_name){
        if( ! class_reference.existMethod(method_name) ){
            throw new XdbCoreException(
                    String.format(
                            "The method %s not exist in the class %s in the path %s",
                            method_name,
                            class_reference.getName(),
                            class_reference.getPath()
                    )
            );
        }
        Method method = class_reference.getRealMethod(method_name);

        Type type = method.getGenericReturnType();


        Class<?> returnClass = method.getReturnType();

        ParameterizedType parameterizedType;
        int position = -1;
        if (type.getClass() == Class.class) {
            this.type_return = returnClass;
            return;
        }

        if (!(type instanceof ParameterizedType)) {
            throw new XdbCoreException(
                    String.format(
                            "the return of your method %s is not posible that process",
                            method_name
                    )
            );
        }
        parameterizedType = (ParameterizedType) type;

        if (Function.class.isAssignableFrom(returnClass)) {
            position = 1;
        } else if (Predicate.class.isAssignableFrom(returnClass)) {
            position = 0;
        } else if (BiFunction.class.isAssignableFrom(returnClass)) {
            position = 2;
        } else if (Consumer.class.isAssignableFrom(returnClass)) {
            position = 0;
        } else if (FunctionBroadcast.class.isAssignableFrom(returnClass)) {
            position = 1;
        } else if (Iterable.class.isAssignableFrom(returnClass)){
            position = 0;
        }

        if (position == -1) {
            throw new XdbCoreException(
                    String.format(
                            "the return of your method %s is not posible that process, becouse is not lambda expression",
                            method_name
                    )
            );
        }

        try {
            this.type_return = (Class) parameterizedType.getActualTypeArguments()[position];
        }catch(ClassCastException e) {
            if (parameterizedType.getActualTypeArguments()[position] instanceof ParameterizedType) {
                type = parameterizedType.getActualTypeArguments()[position];
                try {
                    returnClass = Class.forName(((ParameterizedType) type).getRawType().getTypeName());
                    if (Iterable.class.isAssignableFrom(returnClass)) {
                        position = 0;
                    }
                    this.type_return = (Class) ((ParameterizedType) type).getActualTypeArguments()[position];

                    return;


                } catch (ClassNotFoundException ex) {
                    throw new XdbCoreException(ex);
                }
            }

        }

    }

    public ClassEnviroment getClass_reference() {
        return class_reference;
    }

    public String getName_method() {
        return name_method;
    }

    public Class getType_return() {
        return type_return;
    }

    public Object[] getParameters_method(){
        return this.parameters_method;
    }
}
