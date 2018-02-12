package org.qcri.xdb.translate.rheem.expression;

public class Operation<Type1, Type2, TypeResult>  {

    private Type1      value1      = null;
    private Type2      value2      = null;
    private TypeResult valueResult = null;

    private int        position    = -1;
    private int        position1   = -1;
    private int        position2   = -1;

    private Object     Operator    = null;
}
