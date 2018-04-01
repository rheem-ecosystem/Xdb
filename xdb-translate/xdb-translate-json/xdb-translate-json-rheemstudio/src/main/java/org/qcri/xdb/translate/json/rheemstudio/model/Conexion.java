package org.qcri.xdb.translate.json.rheemstudio.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Conexion implements JsonSerializable {
    private List<Object[]> objectList = new ArrayList<>();



    public Conexion() {
    }

    public void add(Object[] ele){
        this.objectList.add(ele);
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        for(int i = 0; i < this.objectList.size(); i++) {
            if( this.objectList.get(i)[0] != null && this.objectList.get(i)[1] != null ) {
                jsonGenerator.writeArrayFieldStart(Integer.toString(i));
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField((String) this.objectList.get(i)[0], (int) this.objectList.get(i)[1]);
                jsonGenerator.writeEndObject();
                jsonGenerator.writeEndArray();
            }
        }
        jsonGenerator.writeEndObject();

    }

    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {

    }


}
