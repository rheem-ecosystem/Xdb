package org.qcri.xdb.parser.rheemlatin.engine;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.parser.rheemlatin.context.ParserRheemLatinContext;
import org.qcri.xdb.parser.rheemlatin.parser.ConvertListener;
import org.qcri.xdb.parser.rheemlatin.parser.LatinLexer;
import org.qcri.xdb.parser.rheemlatin.parser.LatinParser;
import org.qcri.xdb.parser.rheemlatin.query.plan.FileInputPlan;
import org.qcri.xdb.util.exception.XdbException;

import java.io.IOException;
import java.net.URI;

public class ParserRheemLatinEngine extends XdbEngine {
    public ParserRheemLatinEngine(String name) {
        super(name);
        //TODO change the path
        this.context = new ParserRheemLatinContext(URI.create("/resources/latin.conf.operator.json"));
    }

    @Override
    public XdbPlan preExecute(XdbPlan plan, Object... obj) {
        return plan;
    }

    @Override
    public XdbPlan doexecute(XdbPlan plan, Object... obj) {
        System.out.println("parserLatinEngine");
        if(plan instanceof FileInputPlan){
            FileInputPlan file = (FileInputPlan)plan;
            try {
                CharStream archivo       = new ANTLRFileStream(file.getPath());
                CommonTokenStream tokens = new CommonTokenStream(new LatinLexer(archivo));
                LatinParser parser       = new LatinParser(tokens);

                ParserRuleContext ast    = parser.query();
                ConvertListener listener = new ConvertListener();
                ParseTreeWalker walker   = new ParseTreeWalker();

                walker.walk(listener, ast);

                XdbPlan plan_output = listener.getPlan();
                plan_output.print();
                return plan_output;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XdbException e){
                e.printStackTrace();
                System.exit(1);
            }
            return null;
        }
        return null;
    }

    @Override
    public XdbPlan postExecute(XdbPlan plan, Object... obj) {
        return plan;
    }
}
