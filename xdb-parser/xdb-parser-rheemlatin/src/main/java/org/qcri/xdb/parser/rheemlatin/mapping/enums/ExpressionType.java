package org.qcri.xdb.parser.rheemlatin.mapping.enums;

import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;

public enum ExpressionType {

    MATHEMATICAL("mathematical"),
    LOGICAL("logical");

    private String text;

    ExpressionType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static ExpressionType find(String text) {
        for (ExpressionType b : ExpressionType.values()) {
            if (b.getText().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new ParserRheemLatinException("The "+text+" is not a valid option for"+ ExpressionType.class);
    }
}
