package org.qcri.xdb.executor;

import org.qcri.xdb.core.executor.XdbExecutor;
import org.qcri.xdb.core.handler.XdbHandler;
import org.qcri.xdb.core.handler.handlers.LinealHandler;
import org.qcri.xdb.core.query.XdbQuery;

public class RheemLatin2RheemExecutor extends XdbExecutor {
    public RheemLatin2RheemExecutor(XdbHandler handler) {
        super(handler);
    }


    public static void main(String... args){

       /* XdbQuery query = new XdbFileQuery(args[0]);

        XdbHandler handler = new LinealHandler();

        handler.registre(new ParserXdbEngine("parser"));
        //  handler.registre(new OptimizerAlgebraicEngine("algebraic"));
        handler.registre(new TranslatorRheemEngine("toRheem"));

        handler.toconnect("parser", "toRheem");
        //  handler.toconnect("parser", "algebraic");
//        handler.toconnect("algebraic", "toRheem");

        RheemLatin2RheemExecutor executor = new RheemLatin2RheemExecutor(handler);

        Job job = (Job) executor.execute(query);

        job.execute();*/

    }
}
