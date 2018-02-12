package org.qcri.xdb.core.plan.enviroment;

import org.qcri.xdb.core.context.XdbContext;
import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.util.reflexion.ImportClass;

import java.lang.reflect.Method;
import java.net.URI;

public class ClassEnviroment extends XdbEnviroment {
    protected String path_str;
    protected URI path;
    protected String[] methods;


    static ImportClass IMPORTS;
    static {
        IMPORTS = (ImportClass) XdbContext.getConfiguration("imports");
    }

    protected ClassEnviroment(String name) {
        super(name);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }

    public ClassEnviroment(String name, String path){
        this(name, URI.create(path));
    }

    public ClassEnviroment(String name, URI path){
        this(name);
        this.path = path;
        this.path_str = this.path.getPath();
        validate();
        importMethods();
    }

    public ClassEnviroment(String name, Class clazz){
        this(name);
        IMPORTS.loadClass(name, clazz);
        importMethods();
    }


    public String getPath_str() {
        return path_str;
    }

    public void setPath_str(String path_str) {
        this.path_str = path_str;
    }

    public URI getPath() {
        return path;
    }

    public void setPath(URI path) {
        this.path = path;
    }

    public String[] getMethods() {
        return methods;
    }

    public void setMethods(String[] methods) {
        this.methods = methods;
    }

    public boolean validate() {
        return IMPORTS.loadClass(this.name, this.path);
    }
    protected void importMethods(){
        this.methods = IMPORTS.getMethods(this.name);
    }


    public boolean existMethod(String method_name){
        for(int i = 0; i < this.methods.length; i++){
            if(method_name.compareTo(this.methods[i]) == 0){
                return true;
            }
        }
        return false;
    }

    public Object getMethod(String method_name){
        return IMPORTS.getLambda(this.name, method_name, null);
    }

    public Method getRealMethod(String method_name){
        return IMPORTS.getMethod(this.name, method_name);
    }
}
