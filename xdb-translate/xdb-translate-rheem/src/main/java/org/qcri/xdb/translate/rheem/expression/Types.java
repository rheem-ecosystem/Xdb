package org.qcri.xdb.translate.rheem.expression;

import java.io.Serializable;

public interface Types extends Serializable {
    public static final int LOGICAL         = 0;
    public static final int MATHEMATICAL    = 1;
    public static final int FUNC_STRING     = 2;
    public static final int ID_VALUE        = 3;

    public static final int VARIABLE        = 0;
    public static final int VALUE           = 1;
    public static final int CALCULATED      = 2;
    public static final int VARIABLELOGIC   = 3;
    public static final int VALUELOGIC      = 4;
    public static final int CALCULATEDLOGIC = 5;
    public static final int VARIABLESTRING  = 6;
    public static final int VALUESTRING     = 7;
    public static final int CALCULATESTRING = 8;
    public static final int QUANTITYVARIA   = 97;
}
