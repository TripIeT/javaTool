package javaTool.lib.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
*
*/
public class MyObj {
    public static ArrayList<String> getAttribute(Object o) {
        ArrayList<String> attributeList = new ArrayList<>();
        Class<?> clazz = o.getClass();

        for (Field field : clazz.getDeclaredFields())
            attributeList.add(field.getName());

        return attributeList;
    }

    public static int getNumOfAtb(Class<?> obj) {
        return obj.getClass().getDeclaredFields().length;
    }

    public static ArrayList<String> getInfo(Object obj) {
        ArrayList<String> list = new ArrayList<>();

        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Object o;
            try {
                o = f.get(obj);
            } catch (Exception e) {
                o = e;
            }

            list.add(String.valueOf(o));
        }

        return list;
    }
}
