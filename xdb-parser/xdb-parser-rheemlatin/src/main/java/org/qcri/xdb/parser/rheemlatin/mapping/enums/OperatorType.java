package org.qcri.xdb.parser.rheemlatin.mapping.enums;

import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;

public enum OperatorType {

    BINARY_TO_UNARY("BinaryToUnary"),
    UNARY_TO_UNARY("UnaryToUnary"),
    SINK("Sink"),
    SOURCE("Source"),
    WRAPPER("Wrapper");


    private String text;

    OperatorType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static OperatorType find(String text) {
        for (OperatorType b : OperatorType.values()) {
            if (b.getText().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new ParserRheemLatinException("The " + text + " is not a valid option for" + OperatorType.class);
    }
}
