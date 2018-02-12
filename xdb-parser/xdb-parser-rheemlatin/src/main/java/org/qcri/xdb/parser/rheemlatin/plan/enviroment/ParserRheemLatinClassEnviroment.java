package org.qcri.xdb.parser.rheemlatin.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.enviroment.ClassEnviroment;
import org.qcri.xdb.parser.rheemlatin.context.ParserRheemLatinContext;
import org.qcri.xdb.parser.rheemlatin.exception.ParserRheemLatinException;
import org.qcri.xdb.util.reflexion.ImportClass;

import java.net.URI;

public class ParserRheemLatinClassEnviroment extends ClassEnviroment {

    static ImportClass IMPORTS;
    static {
        IMPORTS = ParserRheemLatinContext.getImportClass();
    }

    public ParserRheemLatinClassEnviroment(String name, String path) {
        this(name, URI.create(path));
    }

    public ParserRheemLatinClassEnviroment(String name, URI path) {
        super(name, path);
    }

    @Override
    public boolean validate() {
        return IMPORTS.loadClass(this.name, this.path);
    }
    @Override
    protected void importMethods(){
        this.methods = IMPORTS.getMethods(this.name);
    }

    @Override
    public boolean existMethod(String method_name){
        for(int i = 0; i < this.methods.length; i++){
            if(method_name.compareTo(this.methods[i]) == 0){
                return true;
            }
        }
        return false;
    }
    @Override
    public Object getMethod(String method_name){
        return null;
    }

    @Override
    protected void selfCopy(XdbElement element) {
        if( !(element instanceof ClassEnviroment)){
            throw new ParserRheemLatinException(
                    String.format(
                            "the class \"%s\" of the [%s]is not extension of the %s",
                            element.getClass(),
                            element,
                            ClassEnviroment.class
                    )
            );
        }
        ClassEnviroment enviroment = (ClassEnviroment) element;
        this.path = enviroment.getPath();
        this.name = enviroment.getName();
        importMethods();
        enviroment.setMethods(this.methods);
        this.path_str = enviroment.getPath_str();
    }
}
