package itart.me.communutil;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author nzbao
 * @CreateTime 2017/6/13
 * @Desc 用于模块间通讯
 */
public class CommunUtil {
    Map<String,MethodClass> methodClassMap;//方法对象 map
    Map<String,Object> variableMap;//变量map
    private static volatile CommunUtil instance=null;

    public static CommunUtil getInstances(){
        if(instance==null){
            synchronized(CommunUtil.class){
                if(instance==null){
                    instance=new CommunUtil();
                }
            }
        }
        return instance;
    }
    private CommunUtil(){
    }

    public abstract static class MethodClass<T>{
        private String methodName;
        private Map<String,Object> paramMap;

        public MethodClass(String methodName, Map<String,Object> paramMap) {
            this.methodName = methodName;
            this.paramMap=paramMap;
        }
        public abstract T run(Map<String,Object> paramMap);
    }

    /**
     * 调用方法
     * @param methodName
     * @param <M> 返回值类型
     * @return
     * @throws NoSuchMethodException
     */
    @Nullable
    public <M>M invoke(String methodName) throws NoSuchMethodException {
        if(methodClassMap ==null){
            throw new NoSuchMethodException("methodClassMap null,pls add method first");
        }
        MethodClass methodClass = methodClassMap.get(methodName);
        if(methodClass==null){
            throw new NoSuchMethodException("no such method");
        }else{
            return (M) methodClass.run(methodClass.paramMap);
        }
    }

    /**
     * 添加要运行的方法
     * @param clazz
     */
    public void add(MethodClass clazz){
        if(methodClassMap ==null){
            methodClassMap =new HashMap<>();
        }
        methodClassMap.put(clazz.methodName,clazz);
    }

    /**
     * 添加变量
     * @param variableName
     * @param variable
     */
    public void add(String variableName,Object variable){
        if(variableMap==null){
            variableMap=new HashMap<>();
        }
        variableMap.put(variableName,variable);
    }

    @Nullable
    public <T> T getVariable(String variableName){
        if(variableMap==null){
            return null;
        }
        return (T) variableMap.get(variableName);
    }

    /**
     * 移除方法
     * @param methodName
     */
    public void removeMethod(String methodName){
        if(methodClassMap ==null){
            return;
        }
        methodClassMap.remove(methodName);
    }

    /**
     * 移除变量
     * @param variableName
     */
    public void removeVariable(String variableName){
        if(variableName ==null){
            return;
        }
        variableMap.remove(variableName);
    }

    /**
     * 清空方法
     */
    public void clearMethod(){
        if(methodClassMap ==null){
            return;
        }
        methodClassMap.clear();
    }

    /**
     * 清空变量
     */
    public void clearVariable(){
        if(variableMap ==null){
            return;
        }
        variableMap.clear();
    }
}
