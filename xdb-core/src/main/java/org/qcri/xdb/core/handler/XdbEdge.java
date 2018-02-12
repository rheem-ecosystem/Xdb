package org.qcri.xdb.core.handler;

import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.exception.XdbCoreException;

import java.util.ArrayList;
import java.util.List;

public class XdbEdge {

    private List<XdbEdge> previous;
    private List<XdbEdge> next;

    private XdbEngine engine;

    public XdbEdge(XdbEngine engine){
        if(engine == null){
            throw new XdbCoreException(
                    new NullPointerException(
                            String.format("the engine is null")
                    )
            );
        }
        this.engine   = engine;
        this.next     = new ArrayList<>();
        this.previous = new ArrayList<>();
    }

    public void toConnect(XdbEdge next){
        this.next.add(next);
        next.toConnectPrevious(this);
    }

    private void toConnectPrevious(XdbEdge previus){
        this.previous.add(previus);
    }

    public XdbEngine getEngine(){
        return this.engine;
    }

    public XdbEdge getNext(int index){
        if(this.next.size() == 0){
            return null;
        }
        return this.next.get(index);
    }

    public XdbEdge getPrevius(int index){
        if(this.previous.size() == 0){
            return null;
        }
        return this.previous.get(index);
    }
}
