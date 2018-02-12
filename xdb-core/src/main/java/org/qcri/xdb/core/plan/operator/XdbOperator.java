package org.qcri.xdb.core.plan.operator;

import org.qcri.xdb.core.exception.XdbCoreException;
import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.expression.XdbExpression;
import org.qcri.xdb.core.plan.structure.XdbStructure;

import java.util.ArrayList;
import java.util.List;

public abstract class XdbOperator extends XdbElement{
    protected String alias;
    protected String platform;
    protected List<XdbOperator> broadcast;
    protected List<String>        broadcast_name;
    protected List<XdbExpression> lambdas;
    protected XdbStructure structure_info;
    protected XdbComponent inputs;
    protected XdbComponent outputs;
    protected XdbOperator wrapper;


    {
        if(this.broadcast == null) {
            broadcast = new ArrayList<>();
        }
        if(this.broadcast_name == null){
            broadcast_name = new ArrayList<>();
        }
        if(this.lambdas == null) {
            lambdas = new ArrayList<>();
        }
    }
    public XdbOperator(String name) {
        super(name);
    }

    public XdbOperator(XdbElement element) {
        super(element);
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public XdbOperator getBroadcast(int index){
        if(index < 0 || index >= this.broadcast.size()){
            throw new XdbCoreException(
                    new ArrayIndexOutOfBoundsException(
                            String.format(
                                    "the index (%s) is not valid for broadcast, the range is 0 to %s",
                                    index,
                                    this.broadcast.size()
                            )
                    )
            );
        }
        return this.broadcast.get(index);
    }

    public String getBroadcastName(int index){
        if(index < 0 || index >= this.broadcast_name.size()){
            throw new XdbCoreException(
                    new ArrayIndexOutOfBoundsException(
                            String.format(
                                    "the index (%s) is not valid for broadcast, the range is 0 to %s",
                                    index,
                                    this.broadcast_name.size()
                            )
                    )
            );
        }
        return this.broadcast_name.get(index);
    }
    public List<XdbOperator> getBroadcast() {
        return broadcast;
    }
    public List<String> getBroadcastName() {
        return this.broadcast_name;
    }

    public boolean hasBroadcast(){
        return this.broadcast_name != null && this.broadcast_name.size() > 0;
    }

    public void addBroadcast(XdbOperator broadcast) {
        this.broadcast.add(broadcast);
    }

    public void addBroadcast(String broadcast){
        this.broadcast_name.add(broadcast);
    }

    public XdbExpression getLambda(int index){
        if(index < 0 || index >= this.lambdas.size()){
            throw new XdbCoreException(
                    new ArrayIndexOutOfBoundsException(
                            String.format(
                                    "the index (%s) is not valid for the lambdas, the range is 0 to %s",
                                    index,
                                    this.lambdas.size()
                            )
                    )
            );
        }
        return this.lambdas.get(index);
    }

    public List<XdbExpression> getLambdas() {
        return lambdas;
    }

    public void addLambdas(XdbExpression lambdas) {
        this.lambdas.add(lambdas);
    }

    public XdbStructure getStructure_info() {
        return structure_info;
    }

    public void setStructure_info(XdbStructure structure_info) {
        this.structure_info = structure_info;
    }

    public XdbComponent getInputs() {
        return inputs;
    }

    public XdbComponent getOutputs() {
        return outputs;
    }

    public XdbOperator getWrapper() {
        return wrapper;
    }

    public void setWrapper(XdbOperator wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Op( ");
        builder.append(this.name);
        if(this.platform != null){
            builder.append(", platform: ");
            builder.append(this.platform);
        }
        if(this.hasBroadcast()){
            builder.append(", broadcast: [");
            for(String broadcast_name :this.broadcast_name ) {
                builder.append(broadcast_name + ",");
            }
            builder.append(" ]");
        }
        builder.append(" ) -> \"");
        builder.append(this.alias);
        builder.append("\" ");
        if(this instanceof OperatorInput) {
            builder.append(" INPUT: {");
            builder.append(this.inputs.toString());
            builder.append(" }");
        }
        if(this instanceof OperatorOutput) {
            builder.append(" OUTPUT: {");
            builder.append(this.outputs.toString());
            builder.append(" }");
        }
        if(this.structure_info != null){
            builder.append(" structure : {");
            builder.append(this.structure_info.toString());
            builder.append(" }");
        }
        return builder.toString();
    }

    public void changeTypes(){}

    public boolean isWrapped(){
        return this.wrapper != null;
    }
}
