package org.qcri.xdb.executor;

import org.qcri.rheem.core.api.Job;
import org.qcri.xdb.core.executor.XdbExecutor;
import org.qcri.xdb.core.handler.XdbHandler;
import org.qcri.xdb.core.handler.handlers.LinealHandler;
import org.qcri.xdb.core.query.XdbQuery;
import org.qcri.xdb.parser.rheemlatin.engine.ParserRheemLatinEngine;
import org.qcri.xdb.parser.rheemlatin.query.RheemLatinFileQuery;
import org.qcri.xdb.translate.rheem.engine.TranslateRheemEngine;

public class RheemLatin2RheemExecutor extends XdbExecutor {
    public RheemLatin2RheemExecutor(XdbHandler handler) {
        super(handler);
    }


    public static void main(String... args){

        XdbQuery query = new RheemLatinFileQuery(args[0]);

        XdbHandler handler = new LinealHandler();

        handler.registre(new ParserRheemLatinEngine("parser"));
        //  handler.registre(new OptimizerAlgebraicEngine("algebraic"));
        handler.registre(new TranslateRheemEngine("toRheem"));

        handler.toconnect("parser", "toRheem");
        //  handler.toconnect("parser", "algebraic");
//        handler.toconnect("algebraic", "toRheem");

        RheemLatin2RheemExecutor executor = new RheemLatin2RheemExecutor(handler);

        Job job = (Job) executor.execute(query);

        job.execute();

    }
}
