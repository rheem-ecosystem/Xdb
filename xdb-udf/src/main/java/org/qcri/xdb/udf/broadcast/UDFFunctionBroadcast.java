package org.qcri.xdb.udf.broadcast;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Function;

public class UDFFunctionBroadcast<InputType, OutputType, BroadcastType> implements Function<InputType, OutputType>, Serializable /* implements FunctionDescriptor.SerializableFunction<InputType, OutputType>, ExtendedFunction*/{

    private Collection<BroadcastType> broadcastCollection;
    private String name;
    private FunctionBroadcast<InputType, OutputType, BroadcastType> functionBroadcast;


    public UDFFunctionBroadcast(String name, FunctionBroadcast<InputType, OutputType, BroadcastType> functionBroadcast){
        this.name = name;
        this.functionBroadcast = functionBroadcast;
    }
/*
    @Override
    public void open(ExecutionContext executionContext) {
        this.broadcastCollection = executionContext.<BroadcastType>getBroadcast(this.name);
    ]*/


    @Override
    public OutputType apply(InputType inputType) {
        return this.functionBroadcast.apply(inputType, this.broadcastCollection);
    }
}
