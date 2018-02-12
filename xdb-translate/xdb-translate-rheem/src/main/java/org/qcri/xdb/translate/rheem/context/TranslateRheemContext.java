package org.qcri.xdb.translate.rheem.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.qcri.xdb.core.context.XdbContext;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.mapping.RheemClassMapping;
import org.qcri.xdb.translate.rheem.mapping.transition.RheemContextTransition;

import java.io.IOException;
import java.net.URI;

public class TranslateRheemContext extends XdbContext {

    private static MirrorRheem RHEEMMAPPING = null;
    public TranslateRheemContext(URI location_configuration) {
        super(location_configuration);
    }

    @Override
    protected void initVariables() {
        if(RHEEMMAPPING == null){
            RHEEMMAPPING = new MirrorRheem();
        }
    }

    @Override
    public void loadContext() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            RheemContextTransition mapping = mapper.readValue(this.location_configuration.toURL(), RheemContextTransition.class);

            for(RheemClassMapping rheem_obj: mapping.transform()){
                RHEEMMAPPING.put(rheem_obj.getLatinOperator(), rheem_obj);
            }
        }catch (IOException e) {
            throw new TranslateRheemException(e);
        }

    }

    public static MirrorRheem getReflexion(){
        return RHEEMMAPPING;
    }
}
