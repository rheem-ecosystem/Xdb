package org.qcri.xdb.translate.rheem.plan.structure;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.structure.XdbStructure;

public class TranslateRheemStructure extends XdbStructure {
    public TranslateRheemStructure(String name) {
        super(name);
    }

    public TranslateRheemStructure(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
