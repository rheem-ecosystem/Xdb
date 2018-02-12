package org.qcri.xdb.parser.rheemlatin.mapping.enums;

import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;

public enum LambdaType {
    PREDICATE("predicate"),
    FUNCTION("function"),
    CONSUMER("consumer"),
    BICONSUMER("biconsumer"),
    BIFUNCTION("bifunction"),
    BIPREDICATE("bipredicate"),
    SUPPLIER("supplier");


    private String text;

    LambdaType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static LambdaType find(String text) {
        for (LambdaType b : LambdaType.values()) {
            if (b.getText().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new ParserRheemLatinException("The " + text + " is not a valid option for" + LambdaType.class);
    }
}
