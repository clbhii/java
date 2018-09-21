package com.cl.java.util.beancopy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author cl
 * @since 2018年7月9日
 */
public class BeanUtils {

    public static <T> T copyProperties(Object orig, T dest) {
        if (dest == null || orig == null) {
            return dest;
        }

        if (orig instanceof Map) {
            Map map = ((Map) orig);
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry next = (Map.Entry) iterator.next();
                for (Field field : dest.getClass().getDeclaredFields()) {
                    if (field.getName().equals(next.getKey())) {
                        Object value = next.getValue();
                        if (value == null) {
                            break;
                        }
                        if (field.getType().isInstance(value)) {
                            field.setAccessible(true);
                            try {
                                field.set(dest, value);
                            } catch (IllegalArgumentException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }

        } else /* if (orig is a standard JavaBean) */ {
            for (Field origField : orig.getClass().getDeclaredFields()) {
                Object value = null;
                try {
                    origField.setAccessible(true);
                    value = origField.get(orig);
                } catch (IllegalArgumentException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                if (value == null) {
                    continue;
                }
                for (Field field : dest.getClass().getDeclaredFields()) {
                    if (field.getName().equals(origField.getName())) {
                        try {
                            if (field.getType().isInstance(value)) {
                                field.setAccessible(true);
                                field.set(dest, value);
                            } else if (field.getType().isPrimitive() && field.getType().hashCode() == value.getClass().hashCode()) {
                                field.setAccessible(true);
                                field.set(dest, value);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
        return dest;
    }


    public static <T> T copyProperties(Object orig, Class<T> clazz) {
        if (orig == null) {
            return null;
        }
        T dest = null;
        try {
            dest = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
        return copyProperties(orig, dest);
    }
    
    public static <T> List<T> copyListProperties(List orig, Class<T> clazz){
    	List<T> list = new ArrayList();
    	for(Object obj : orig) {
    		list.add(copyProperties(obj, clazz));
    	}
    	return list;
    }

    public static <T> T deepCopyProperties(Object orig, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(orig), clazz);
    }

    public static <T> List<T> deepCopyListProperties(Object orig, Class<T> clazz) {
        return JSON.parseArray(JSON.toJSONString(orig), clazz);
    }

}
