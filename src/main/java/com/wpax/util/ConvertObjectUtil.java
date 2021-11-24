package com.wpax.util;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class ConvertObjectUtil {

    public static <T> T castObj(Object o, Class<?> clazz) {

        if (o == null) {
            return null;
        }
        if (o.getClass() == clazz) {
            return (T) o;
        }
        if (o.getClass() != ArrayList.class) {

            return (T) toObject(o.getClass().getSimpleName(), o.toString());
        }

        ArrayList<T> list = (ArrayList<T>) o;
        return (T) list;
    }

    public static Object toObject(String type, String value) {
        if (Boolean.TYPE.getName() == type)
            return Boolean.parseBoolean(value);

        if (Byte.TYPE.getName() == type)
            return Byte.parseByte(value);

        if (Short.TYPE.getName() == type)
            return Short.parseShort(value);

        if (Integer.TYPE.getName() == type)
            return Integer.parseInt(value);

        if (Long.TYPE.getName() == type)
            return Long.parseLong(value);

        if (Float.TYPE.getName() == type)
            return Float.parseFloat(value);

        if (Double.TYPE.getName() == type)
            return Double.parseDouble(value);

        return value;
    }
}
