package gittest.itart.com.viewinject.annotations;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

/**
 * @author nzbao
 * @CreateTime 2017/7/7
 * @Desc
 */
public enum EventType {
    Click("setOnClickListener", View.OnClickListener.class),
    LongClick("setOnLongClickListener",View.OnLongClickListener.class),
    ListItemClick("setOnItemClickListener", AdapterView.OnItemClickListener.class),
    ListItemLongClick("setOnItemLongClickListener", AdapterView.OnItemLongClickListener.class),
    ListScrolling("setOnScrollListener", AbsListView.OnScrollListener.class);
    String methodName;//setOnClickListener  对应view的设置监听的方法
    Class<?> clazz;//View.OnClickListener   监听对应的类

//    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//            return false;
//        }
//    });

    EventType(String methodName, Class<?> clazz) {
        this.methodName = methodName;
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
