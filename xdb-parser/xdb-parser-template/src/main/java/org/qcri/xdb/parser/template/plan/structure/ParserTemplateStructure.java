package org.qcri.xdb.parser.template.plan.structure;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.structure.XdbStructure;

public class ParserTemplateStructure extends XdbStructure {
    public ParserTemplateStructure(String name) {
        super(name);
    }

    public ParserTemplateStructure(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
