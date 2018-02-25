package org.qcri.xdb.translate.json.rheemstudio.plan;

import org.qcri.xdb.core.executor.XdbExecutable;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;
import org.qcri.xdb.core.plan.operator.OperatorInput;
import org.qcri.xdb.core.plan.operator.XdbComponent;
import org.qcri.xdb.core.plan.operator.XdbOperator;
import org.qcri.xdb.core.plan.operator.logical.SinkOperator;
import org.qcri.xdb.core.plan.structure.XdbStructure;
import org.qcri.xdb.translate.json.rheemstudio.model.Conexion;
import org.qcri.xdb.translate.json.rheemstudio.model.Operator;
import org.qcri.xdb.translate.json.rheemstudio.model.Schema;

import java.util.*;

public class TranslateRheemStudioPlan extends XdbPlan implements XdbExecutable {

    private Schema schema;


    public TranslateRheemStudioPlan(XdbPlan plan){
        operators      = plan.getOperators();
        expressions    = plan.getExpressions();
        alias          = plan.getAlias();
        aliasOperators = plan.getAliasOperators();
        sourceOperators= plan.getSourceOperators();
        sinkOperators  = plan.getSinkOperators();
        enviroments = plan.getEnviroments();
        structures = plan.getStructures();
    }

    @Override
    public Object execute() {
        if(schema != null){
            return this.schema;
        }
        schema = new Schema();

        int pos_x = 10;
        int pos_y = 10;
        int pos_plus = 50;
        String color = "ffffff";
        for(XdbOperator xdbOperator: this.operators){
            Operator op = new Operator()
                    .setName(xdbOperator.getName())
                    //.setJava_class()
                    .setX(pos_x)
                    .setY(pos_y)
                    .setColor(color)
                    .setParameters(null)
                    //.setSelectedConstructor()
                    //.setIsbroadcast()
                    //.setType()
                    //.setBroadcasts_to()
                    .setNp_inputs(xdbOperator.getInputs().getSize())
                    .setNp_outputs(xdbOperator.getOutputs().getSize());

            XdbComponent output =  xdbOperator.getOutputs();
            for(int i =0; i< output.getSize(); i++){
                op.addConnects_to(new Conexion(output.getOperator(i).getAlias()));
            }
            pos_x += pos_plus;
            this.schema.addOperator();
        }

        return schema;
    }
}