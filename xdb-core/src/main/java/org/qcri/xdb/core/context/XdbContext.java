package org.qcri.xdb.core.context;

import org.qcri.xdb.core.exception.XdbCoreException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public abstract class XdbContext {
    protected static Map<String, Object> configuration;

    static{
        configuration = new HashMap<>();
    }

    protected URI location_configuration;

    public XdbContext(URI location_configuration){
        this.location_configuration = location_configuration;
        this.initVariables();
    }

    protected abstract void initVariables();

    public abstract void loadContext();

    public static void putConfiguration(String key, Object value){
        configuration.put(key, value);
    }

    public static Object getConfiguration(String key){
        if(! configuration.containsKey(key)){
            throw new XdbCoreException(
                    String.format( "Not exist \"%s\"  in the configuration of %s", key, XdbContext.class )
            );
        }
        return configuration.get(key);
    }
}
