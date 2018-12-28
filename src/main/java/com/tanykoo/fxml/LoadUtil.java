package com.tanykoo.fxml;

import com.tanykoo.util.SpringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @author Tany
 * @createtime 2018-04-28
 * @since
 */
public class LoadUtil {



    public static <T> T load(String layoutclasspath) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(SpringUtils::getBean);

        loader.setLocation(LoadUtil.class.getResource(layoutclasspath));
        T t = loader.load();
        T controller = loader.getController();
        initAnnotation(controller);
        init(loader.getChildrenLoaders());
        return t;

    }

    private static <T> void init(List<FXMLLoader> loaderList){
        for(FXMLLoader fxmlLoader : loaderList){
            T controller = fxmlLoader.getController();
            initAnnotation(controller);
            if(fxmlLoader.getChildrenLoaders().size() > 0){
                init(fxmlLoader.getChildrenLoaders());
            }
        }
    }


    private  static <T> void initAnnotation(T t){
        if(t == null){
            return;
        }
        Method[] methods = t.getClass().getDeclaredMethods();

        for (Method method: methods) {
            method.setAccessible(true);
            if(method.getAnnotation(Init.class)!= null){
                if(method.getParameterCount() == 0){
                    try {
                        method.invoke(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
