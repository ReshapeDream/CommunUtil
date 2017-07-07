package gittest.itart.com.viewinject;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gittest.itart.com.gittest.inject.annotations.EventType;
import gittest.itart.com.gittest.inject.annotations.XEvent;
import gittest.itart.com.gittest.inject.annotations.XFindView;
import gittest.itart.com.gittest.inject.annotations.XLayoutId;

/**
 * @author nzbao
 * @CreateTime 2017/7/7
 * @Desc 支持activity不支持fragment layout注入
 */
public class InjectViewUtil {

    public static void inject(Context context) {
        /*注入layout id*/
        injectLayout(context);
        /*注入view*/
        injectView(context);
        /*注入事件*/
        injectEvent(context);
    }

    private static void injectEvent(Context context) {
        Method[] declaredMethods = context.getClass().getDeclaredMethods();
        Map<String, Method> methodMap = new HashMap<>();
        ListenerInvocationHandler handler = new ListenerInvocationHandler(context, methodMap);
        for (Method method : declaredMethods) {
            XEvent xEvent = method.getAnnotation(XEvent.class);
            if (xEvent != null) {
                EventType event = xEvent.event();
                if (!xEvent.listenerMethod().equals("")) {
                    methodMap.put(xEvent.listenerMethod(), method);
                } else {
                    methodMap.put(event.getClazz().getDeclaredMethods()[0].getName(), method);
                }
                List<View> views = getViews(context, xEvent.value());
                for (View view : views) {
                    try {
                        Method setClickListenerMethod = view.getClass().getMethod(event.getMethodName(), event.getClazz());
                        Object proxy = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(), new Class[]{event.getClazz()}, handler);
                        setClickListenerMethod.invoke(view, proxy);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private static List<View> getViews(Context context, int[] value) {
        List<View> list = new ArrayList<>();
        try {
            Method findViewById = context.getClass().getMethod("findViewById", int.class);
            for (int i = 0; i < value.length; i++) {
                View view = (View) findViewById.invoke(context, value[i]);
                list.add(view);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void injectView(Context context) {
        Field[] fields = context.getClass().getDeclaredFields();
        for (Field field : fields) {
            XFindView findView = field.getAnnotation(XFindView.class);
            if (findView != null) {
                int viewId = findView.value();
                try {
                    Method findViewMethod = context.getClass().getMethod("findViewById", int.class);
                    View view = (View) findViewMethod.invoke(context, viewId);
                    field.setAccessible(true);
                    field.set(context, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectLayout(Context context) {
        XLayoutId layoutId = context.getClass().getAnnotation(XLayoutId.class);
        if (layoutId != null) {
            try {
                Method setContentView = context.getClass().getMethod("setContentView", int.class);
                setContentView.invoke(context, layoutId.value());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
