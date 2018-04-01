package org.qcri.xdb.parser.rheemlatin.engine;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.parser.rheemlatin.context.ParserRheemLatinContext;
import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;
import org.qcri.xdb.parser.rheemlatin.parser.ConvertListener;
import org.qcri.xdb.parser.rheemlatin.parser.LatinLexer;
import org.qcri.xdb.parser.rheemlatin.parser.LatinParser;
import org.qcri.xdb.parser.rheemlatin.query.plan.FileInputPlan;
import org.qcri.xdb.parser.rheemlatin.query.plan.StringPlan;
import org.qcri.xdb.util.exception.XdbException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public class ParserRheemLatinEngine extends XdbEngine {

    private boolean strictValidate = true;
    public ParserRheemLatinEngine(String name) {
        super(name);
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            this.context = new ParserRheemLatinContext(classLoader.getResource("latin.conf.operator.json").toURI());
        } catch (URISyntaxException e) {
            throw new ParserRheemLatinException(e);
        }
    }

    public ParserRheemLatinEngine(String parser, boolean strictValidate) {
        this(parser);
        this.strictValidate = strictValidate;
    }

    @Override
    public XdbPlan preExecute(XdbPlan plan, Object... obj) {
        return plan;
    }

    @Override
    public XdbPlan doexecute(XdbPlan plan, Object... obj) {
        System.out.println("parserLatinEngine");


        try {
            CharStream charStream;
            if (plan instanceof FileInputPlan) {
                FileInputPlan filePlan = (FileInputPlan) plan;
                charStream = new ANTLRFileStream(filePlan.getPath());

            } else if (plan instanceof StringPlan) {
                StringPlan stringPlan = (StringPlan) plan;
                charStream = new ANTLRInputStream(stringPlan.getPlanString());
            } else {
                throw new ParserRheemLatinException("the type of query is not supported");
            }

            CommonTokenStream tokens = new CommonTokenStream(new LatinLexer(charStream));
            LatinParser parser = new LatinParser(tokens);

            ParserRuleContext ast = parser.query();
            ConvertListener listener = new ConvertListener(strictValidate);
            ParseTreeWalker walker = new ParseTreeWalker();

            walker.walk(listener, ast);

            XdbPlan plan_output = listener.getPlan();
            //plan_output.print();
            return plan_output;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XdbException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public XdbPlan postExecute(XdbPlan plan, Object... obj) {
        return plan;
    }
}
