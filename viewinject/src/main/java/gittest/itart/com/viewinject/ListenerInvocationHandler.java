package gittest.itart.com.viewinject;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author nzbao
 * @CreateTime 2017/7/7
 * @Desc
 */
public class ListenerInvocationHandler implements InvocationHandler{
    private Context context;
    private Map<String,Method> methodMap;

    public ListenerInvocationHandler(Context context, Map<String, Method> methodMap) {
        this.context = context;
        this.methodMap = methodMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name=method.getName();
        Method methodSelf=methodMap.get(name);
        if(methodSelf!=null){
            return methodSelf.invoke(context,args);
        }else{
            return method.invoke(proxy,args);
        }
    }
}
