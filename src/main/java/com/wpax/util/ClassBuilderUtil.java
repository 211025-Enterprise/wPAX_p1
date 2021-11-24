package com.wpax.util;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.wpax.annotation.P_Key;

public class ClassBuilderUtil {

    public static Object createClassInstance(Class<?> clazz) {
        Constructor<?> noArgsConstructor = Arrays.stream(clazz.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() == 0).findFirst().orElse(null);

        try {
            return noArgsConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {

            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Field> convertStringArrayToFieldArray(Class<?> clazz, String[] strArr) {
        ArrayList<Field> fieldList = new ArrayList<Field>();
        for (int i = 0; i < strArr.length; i++) {
            fieldList.add(stringToField(clazz, strArr[i]));
        }
        return fieldList;
    }

    public static Field stringToField(Class<?> clazz, String str) {
        try {
            return clazz.getDeclaredField(str);
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object[] getAllFieldValues(Object obj, Field[] fields) {

        Object[] objArr = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            objArr[i] = getFieldValue(obj, fields[i]);
        }

        return objArr;
    }

    public static Object getFieldValue(Object obj, Field field) {

        field.setAccessible(true);
        try {

            if (field.get(obj) == null)
                return null;

            return field.get(obj);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isFieldAPrimaryKey(Field field) {
        return field.getDeclaredAnnotation(P_Key.class) != null;
    }

    public String getFieldName(Field field) {
        return field.getType().getSimpleName();
    }
}
