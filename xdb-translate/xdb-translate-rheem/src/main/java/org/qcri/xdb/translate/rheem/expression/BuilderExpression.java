package org.qcri.xdb.translate.rheem.expression;

import org.qcri.xdb.core.plan.expression.ConstantExpression;
import org.qcri.xdb.core.plan.expression.FunctionExpression;
import org.qcri.xdb.core.plan.expression.SubIDExpression;
import org.qcri.xdb.core.plan.expression.XdbExpression;

import java.util.ArrayList;
import java.util.List;

public class BuilderExpression implements Types {
    public static int nodo = 0;
    public static int record = 0;

    public static Expression builderExpression(XdbExpression expre){
        ArrayList<Element> list_nodes = new ArrayList<>();
        nodo = 0;
        generateList(expre, list_nodes);
        return new Expression(list_nodes);
    }

    private static int generateList(XdbExpression expr, List<Element> list_nodes){

        if( isLeaf(expr) || ! expr.hasChildren()){
            return -1;
        }
        Element element = new Element(expr.countChildren());
        if(expr instanceof SubIDExpression){
            element.setOperator("RecordDinamicGet");
            element.setTypeEle(ID_VALUE);
            element.setValueString(((SubIDExpression)expr).getValue());
            element.setIsFunction(true);
            element.setPositionParam(record);
            list_nodes.add(element);
            record++;
            return nodo++;
        }



        int[] position = new int[expr.countChildren()];

        int index = 0;
        expr.goFirstChildren();
        while(expr.hasMoreChildren()){
            XdbExpression sub_expr = expr.nextChildren();
            if( sub_expr != null ){
                position[index] = generateList(sub_expr, list_nodes);
            }
            index++;
        }



        element.setTypeEle(whatType(expr.getType()));
        expr.goFirstChildren();
        index = 0;

        if(expr instanceof FunctionExpression){
            element.setOperator(expr.getName());
            element.setIsFunction(true);
        }else {
            element.setOperator(expr.getReal_operator());
        }
        while( expr.hasMoreChildren() ){
            XdbExpression sub_expr = expr.nextChildren();
            if( position[index] != -1 ){
                element.setType(Types.CALCULATED);
                element.setPositon(position[index]);
            }else{
                if( sub_expr instanceof ConstantExpression){
                    ConstantExpression _sub_expr = (ConstantExpression) sub_expr;
                    if(_sub_expr.get_class().equals(Double.class) ){
                        element.setValue(
                                (Double) _sub_expr.getValue()
                        );
                        element.setType(Types.VALUE);
                    }else if(_sub_expr.get_class().equals(String.class) ){
                        element.setValueString(
                                (String) _sub_expr.getValue()
                        );
                        element.setType(Types.VALUESTRING);
                    }else if(_sub_expr.get_class().equals(Boolean.class)){
                        element.setValueLogic(
                                (Boolean) _sub_expr.getValue()
                        );
                        element.setType(Types.VALUELOGIC);
                    }
                }
            }
            index++;
            element.addIndex();
        }





        list_nodes.add(element);
        return nodo++;
    }

    private static boolean isLeaf(XdbExpression expr){
        if(expr instanceof ConstantExpression){
            return true;
        }
        return false;
    }

    private static int whatType(String type){
        switch (type){
            case "mathematical":
                return Types.MATHEMATICAL;
            case "logical":
                return Types.LOGICAL;
            case "string":
                return Types.FUNC_STRING;
            default:
                return -1;
        }
    }

}
