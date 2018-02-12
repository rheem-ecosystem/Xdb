package org.qcri.xdb.parser.rheemlatin.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.qcri.xdb.core.context.XdbContext;
import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;
import org.qcri.xdb.parser.rheemlatin.mapping.ExpressionMapping;
import org.qcri.xdb.parser.rheemlatin.mapping.MappingFinal;
import org.qcri.xdb.parser.rheemlatin.mapping.enums.ExpressionTypeClass;
import org.qcri.xdb.parser.rheemlatin.mapping.transition.MappingTransform;
import org.qcri.xdb.parser.rheemlatin.mapping.transition.ParserLatinTransition;
import org.qcri.xdb.parser.rheemlatin.parser.LatinLexer;
import org.qcri.xdb.util.reflexion.ImportClass;

import java.io.IOException;
import java.net.URI;

public class ParserRheemLatinContext extends XdbContext {

    private static LoadMockupClass LOADER = null;
    private static ImportClass IMPORTS = null;

    public ParserRheemLatinContext(URI location_configuration) {
        super(location_configuration);
    }

    @Override
    protected void initVariables() {
        if(LOADER == null) {
            LOADER = new LoadMockupClass();
            IMPORTS = new ImportClass();
            putConfiguration("mockupClass", LOADER);
            putConfiguration("imports", IMPORTS);
        }
    }

    @Override
    public void loadContext() {
        loadOperators();

    }

    public static LoadMockupClass getLoaderClass(){
        return LOADER;
    }

    public static ImportClass getImportClass() {
        return IMPORTS;
    }


    public void loadOperators(){
        try {
            ObjectMapper mapper = new ObjectMapper();

            ParserLatinTransition mapping = mapper.readValue(this.location_configuration.toURL(), ParserLatinTransition.class);

            for(MappingTransform trans: mapping.getOperator_mapping()){
                MappingFinal trans_final = trans.transform();
                LOADER.addOperatorMapping(trans_final.getName(), trans_final);
                LatinLexer.addOperator(trans_final.getName());
            }

            for(MappingTransform trans: mapping.getExpression_mapping()){
                MappingFinal trans_final = trans.transform();
                LOADER.addExpressionsMapping(trans_final.getName(), trans_final);
                ExpressionTypeClass et = ((ExpressionMapping)trans_final).getType_class();
                switch (et){
                    case UNARY:
                        LatinLexer.addPrefixOperator(trans_final.getName());
                        break;
                    case BINARY:
                        LatinLexer.addBinaryOperator(trans_final.getName());
                        break;
                    default:
                        throw new ParserRheemLatinException("Problems with the type of the class");
                }
            }

            for(MappingTransform trans: mapping.getFunction_mapping()){
                MappingFinal trans_final = trans.transform();
                LOADER.addFunctionMapping(trans_final.getName(), trans_final);
                LatinLexer.addFunction(trans_final.getName());
            }

            for(MappingTransform trans: mapping.getWrapper_mapping()){
                MappingFinal trans_final = trans.transform();
                LOADER.addWrapperMapping(trans_final.getName(), trans_final);
                LatinLexer.addOperator(trans_final.getName());
            }
        } catch (IOException e) {
            throw new ParserRheemLatinException(e);
        }
    }
}
