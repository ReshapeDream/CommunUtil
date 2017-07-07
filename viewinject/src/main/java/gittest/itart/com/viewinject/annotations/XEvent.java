package gittest.itart.com.viewinject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nzbao
 * @CreateTime 2017/7/7
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface XEvent {
    EventType event() default EventType.Click;
    int[] value();
    /**
     * EventType对应的Listener的回调方法名
     * 例如View.OnClickListener 中的onClick方法
     * Listener中有多个回调方法，必须指定对应的会调方法名
     * 例如:AbsListView.OnScrollListener 的 onScrollStateChanged和onScroll
     * @return
     */
    String listenerMethod() default "";

}
