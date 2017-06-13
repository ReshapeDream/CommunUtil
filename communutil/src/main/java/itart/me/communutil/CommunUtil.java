package itart.me.communutil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author nzbao
 * @CreateTime 2017/6/13
 * @Desc
 */
public class CommunUtil {
    Map<String,MethodClass> methodClassMap;//方法对象 map
    Map<String,Object> variableMap;//变量map
    private static CommunUtil instance=null;

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
    public <M>M invoke(String methodName) throws NoSuchMethodException {
        if(methodClassMap ==null){
            throw new NoSuchMethodException("methodClassMap null,pls add method first");
        }
        Iterator<Map.Entry<String, MethodClass>> iterator = methodClassMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, MethodClass> next = iterator.next();
            if(methodName.equals(next.getKey())){
               return (M) next.getValue().run(next.getValue().paramMap);
            }
        }
        throw new NoSuchMethodException("no such method");
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