package com.cl.java.util.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author cl
 * @since 2018年7月9日
 */
public class BaseEnumUtil {

    public static <T> BaseEnum<T> valueOf(T value, Class<? extends Enum<? extends BaseEnum<T>>> type) {
        Enum<? extends BaseEnum<T>>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum<T>> e : enums) {
        	BaseEnum<T> baseEnum = (BaseEnum<T>) e;
            if (baseEnum.getValue().equals(value)) {
                return baseEnum;
            }
        }
        return null;
    }
    
    public static <T> BaseEnum<T> valueOf(String desc, Class<? extends Enum<? extends BaseEnum<T>>> type) {
        Enum<? extends BaseEnum<T>>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum<T>> e : enums) {
            BaseEnum<T> baseEnum = (BaseEnum<T>) e;
            if (baseEnum.getDesc().equals(desc)) {
                return baseEnum;
            }
        }
        return null;
    }
    
    public static <T> BaseEnum<T> getEnumByName(String name, Class<? extends Enum<? extends BaseEnum<T>>> type) {
        Enum<? extends BaseEnum<T>>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum<T>> e : enums) {
            BaseEnum<T> baseEnum = (BaseEnum<T>) e;
            if (e.name().equals(name)) {
                return baseEnum;
            }
        }
        return null;
    }

    public static <T> String getDescByValue(T value, Class<? extends Enum<? extends BaseEnum<T>>> type) {
        Enum<? extends BaseEnum<T>>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum<T>> e : enums) {
            BaseEnum<T> baseEnum = (BaseEnum<T>) e;
            if (baseEnum.getValue().equals(value)) {
                return baseEnum.getDesc();
            }
        }
        return "";
    }


    
	public static <T> List<BaseEnum<T>> getChildEnums(String parentName, Class<? extends Enum<? extends BaseEnum<T>>> type) {
		List<BaseEnum<T>> list = new ArrayList<>();
		Enum<? extends BaseEnum<T>>[] enums = type.getEnumConstants();
		for (Enum<? extends BaseEnum<T>> e : enums) {
			String name = e.name();
			String[] nameArr = name.split("__");
			String key = nameArr[0];
			if (key.equals(parentName.toString())){
				list.add((BaseEnum<T>)e);
			}
		}
		return list;
	}
	
	public static <T> BaseEnum<T> getParentEnum(T value, Class<? extends Enum<? extends BaseEnum<T>>> type, Class<? extends Enum<? extends BaseEnum<T>>> parentType) {
		Enum<? extends BaseEnum<T>>[] enums = type.getEnumConstants();
		for (Enum<? extends BaseEnum<T>> e : enums) {
			BaseEnum<T> baseEnum = (BaseEnum<T>) e;
            if (baseEnum.getValue().equals(value)) {
                String name = e.name();
                String[] nameArr = name.split("__");
    			return getEnumByName(nameArr[0], parentType);
            }
		}
		return null;
	}
	
}
