package org.qcri.xdb.core.plan.structure;

import org.qcri.xdb.core.plan.XdbElement;

public abstract class XdbStructure extends XdbElement {
    public XdbStructure(String name) {
        super(name);
    }

    public XdbStructure(XdbElement element) {
        super(element);
    }
}
