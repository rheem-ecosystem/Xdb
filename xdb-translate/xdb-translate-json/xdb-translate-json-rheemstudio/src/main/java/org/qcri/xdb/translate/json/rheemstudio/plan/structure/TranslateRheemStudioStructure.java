package org.qcri.xdb.translate.json.rheemstudio.plan.structure;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.structure.XdbStructure;

public class TranslateRheemStudioStructure extends XdbStructure {
    public TranslateRheemStudioStructure(String name) {
        super(name);
    }

    public TranslateRheemStudioStructure(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
