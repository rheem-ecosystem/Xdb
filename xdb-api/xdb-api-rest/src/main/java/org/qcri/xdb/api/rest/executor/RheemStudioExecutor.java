package org.qcri.xdb.api.rest.executor;

import org.qcri.xdb.core.executor.XdbExecutor;
import org.qcri.xdb.core.handler.XdbHandler;
import org.qcri.xdb.core.handler.handlers.LinealHandler;
import org.qcri.xdb.core.query.XdbQuery;
import org.qcri.xdb.executor.RheemLatin2RheemExecutor;
import org.qcri.xdb.parser.rheemlatin.engine.ParserRheemLatinEngine;
import org.qcri.xdb.translate.json.rheemstudio.engine.TranslateRheemStudioEngine;

public class RheemStudioExecutor extends XdbExecutor{


    public RheemStudioExecutor(XdbHandler handler) {
        super(handler);
    }

    public Object execute(XdbQuery query){
        return super.execute(query);
    }

     public static XdbExecutor create(){
         XdbHandler handler = new LinealHandler();

         handler.registre(new ParserRheemLatinEngine("parser"));
         handler.registre(new TranslateRheemStudioEngine("toStudio"));

         handler.toconnect("parser", "toStudio");

         return new RheemLatin2RheemExecutor(handler);
     }
}
