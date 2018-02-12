package org.qcri.xdb.translate.template.plan.structure;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.structure.XdbStructure;

public class TranslateTemplateStructure extends XdbStructure {
    public TranslateTemplateStructure(String name) {
        super(name);
    }

    public TranslateTemplateStructure(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
