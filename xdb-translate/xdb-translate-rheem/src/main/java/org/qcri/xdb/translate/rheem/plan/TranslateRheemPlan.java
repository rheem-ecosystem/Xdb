package org.qcri.xdb.translate.rheem.plan;

import org.qcri.rheem.core.api.RheemContext;
import org.qcri.rheem.core.plan.rheemplan.Operator;
import org.qcri.rheem.core.plan.rheemplan.RheemPlan;
import org.qcri.rheem.core.plan.rheemplan.UnarySink;
import org.qcri.rheem.core.platform.Platform;
import org.qcri.rheem.java.Java;
import org.qcri.xdb.core.executor.XdbExecutable;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;
import org.qcri.xdb.core.plan.operator.OperatorInput;
import org.qcri.xdb.core.plan.operator.XdbOperator;
import org.qcri.xdb.core.plan.operator.logical.SinkOperator;
import org.qcri.xdb.core.plan.structure.XdbStructure;
import org.qcri.xdb.translate.rheem.context.MirrorRheem;
import org.qcri.xdb.translate.rheem.context.TranslateRheemContext;

import java.util.*;

public class TranslateRheemPlan extends XdbPlan implements XdbExecutable {

    private RheemContext rheemContext;
    private RheemPlan rheemPlan;

    private List<Operator> operatorsRheem;

    private List<UnarySink> opSinkRheem;

    private static Map<String, Platform> platforms;

    private Collection<XdbStructure> structures;
    private Map<String, XdbEnviroment> enviroments;

    public TranslateRheemPlan(XdbPlan plan){
        if(platforms == null){
            loadPlatform();
        }
        operators      = plan.getOperators();
        expressions    = plan.getExpressions();
        alias          = plan.getAlias();
        aliasOperators = plan.getAliasOperators();
        sourceOperators= plan.getSourceOperators();
        sinkOperators  = plan.getSinkOperators();

        enviroments = plan.getEnviroments();
        structures = plan.getStructures();

    }

    public XdbPlan generateContext(){
        this.rheemContext = new RheemContext();
        this.rheemContext.with(Java.basicPlugin());
        //this.rheemContext.with(Postgres.plugin());
        //this.rheemContext.with(Spark.basicPlugin());
        return this;
    }


    public XdbPlan convert(){
        this.operatorsRheem = new ArrayList<>();
        this.opSinkRheem    = new ArrayList<>();
        Map<String, String> broadcast = new HashMap<>();
        HashMap<String, Operator> map_names = new HashMap<>();
        HashMap<XdbOperator, Operator> map = new HashMap<>();
        MirrorRheem mirror = TranslateRheemContext.getReflexion();
        for(XdbOperator op: this.operators){
            Operator opRheem = mirror.getReflexion(op);
            if(op.getPlatform() != null) {
                opRheem.addTargetPlatform(platforms.get(op.getPlatform()));
            }
            if(op.hasBroadcast()){
                for(String broadcast_name : op.getBroadcastName()) {
                    broadcast.put(op.getAlias(), broadcast_name);
                }
            }
            this.operatorsRheem.add(opRheem);
            map.put(op, opRheem);
            map_names.put(op.getAlias(), opRheem);
            if(op instanceof SinkOperator){
                this.opSinkRheem.add((UnarySink) opRheem);
            }
        }

        for(Map.Entry<String, String> link: broadcast.entrySet()){
            Operator opbroad = map_names.get(link.getValue());
            Operator opLink  = map_names.get(link.getKey());
            opbroad.broadcastTo(0, opLink, link.getValue());
        }

        for(XdbOperator op: this.operators){
            if( ! (op instanceof OperatorInput) ){
                continue;
            }

            OperatorInput current = (OperatorInput) op;
            int lenght = current.getSizeInput();
            for(int i = 0; i < lenght; i++){
                XdbOperator prev = this.aliasOperators.get( current.getAliasInput(i) );
                Operator currentRheem = map.get(prev);
                Operator nextRheem    = map.get(current);
                currentRheem.connectTo(0, nextRheem, i);
            }

        }

        Operator[] sinkop = new Operator[this.opSinkRheem.size()];
        for(int i = 0; i < this.opSinkRheem.size(); i++){
            sinkop[i] = this.opSinkRheem.get(i);
        }
        this.rheemPlan = new RheemPlan(sinkop);
        return this;
    }


    @Override
    public Object execute(){
        return this.rheemContext.createJob(null, this.rheemPlan);
        //this.rheemContext.execute(this.rheemPlan);
    }



    private void loadPlatform(){
        platforms = new HashMap<>();
        platforms.put("java", Java.platform());
        //platforms.put("spark", Spark.platform());
    }
}
