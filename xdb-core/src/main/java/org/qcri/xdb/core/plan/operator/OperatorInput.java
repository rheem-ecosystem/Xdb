package org.qcri.xdb.core.plan.operator;

import org.qcri.xdb.core.plan.expression.XdbExpression;

public interface OperatorInput {
    Class getTypeInput(int index);

    Class[] getTypesInput();

    String getAliasInput(int index);

    String[] getAllAliasInput();

    XdbOperator getOperatorInput(int index);

    XdbOperator getOperatorInput(String name);

    XdbOperator[] getOperatorsInput();

    XdbExpression getExpressionInput(int index);

    XdbExpression[] getExpressionsInput();

    String[] getExpressionsAliasInput();

    String getExpressionAliasInput(int index);

    void setTypeInput(int index, Class clazz);

    void setOperatorInput(int index, XdbOperator operator);

    void setAliasInput(int index, String alias);

    void setExpressionInput(int index, XdbExpression expression);

    void setExpressionAliasInput(int index, String alias_expression);

    int getSizeInput();
}
