package org.qcri.xdb.parser.rheemlatin.mapping.enums;

import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;

public enum ExpressionTypeClass {
    BINARY("binary"),
    UNARY("unary"),
    POSTUNARY("post_unary");


    private String text;

    ExpressionTypeClass(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static ExpressionTypeClass find(String text) {
        for (ExpressionTypeClass b : ExpressionTypeClass.values()) {
            if (b.getText().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new ParserRheemLatinException("The "+text+" is not a valid option for"+ ExpressionTypeClass.class);
    }
}
