package com.cl.java.util.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author cl
 * @since 2018年7月9日
 */
public class BaseEnumUtil {

    public static BaseEnum valueOf(Integer value, Class<? extends Enum<? extends BaseEnum>> type) {
        Enum<? extends BaseEnum>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum> e : enums) {
            BaseEnum baseEnum = (BaseEnum) e;
            if (baseEnum.getValue().equals(value)) {
                return baseEnum;
            }
        }
        return null;
    }
    
    public static BaseEnum valueOf(String desc, Class<? extends Enum<? extends BaseEnum>> type) {
        Enum<? extends BaseEnum>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum> e : enums) {
            BaseEnum baseEnum = (BaseEnum) e;
            if (baseEnum.getDesc().equals(desc)) {
                return baseEnum;
            }
        }
        return null;
    }
    
    public static BaseEnum getEnumByName(String name, Class<? extends Enum<? extends BaseEnum>> type) {
        Enum<? extends BaseEnum>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum> e : enums) {
            BaseEnum baseEnum = (BaseEnum) e;
            if (e.name().equals(name)) {
                return baseEnum;
            }
        }
        return null;
    }

    public static String getDescByValue(Integer value, Class<? extends Enum<? extends BaseEnum>> type) {
        Enum<? extends BaseEnum>[] enums = type.getEnumConstants();
        for (Enum<? extends BaseEnum> e : enums) {
            BaseEnum baseEnum = (BaseEnum) e;
            if (baseEnum.getValue().equals(value)) {
                return baseEnum.getDesc();
            }
        }
        return "";
    }


    
	public static List<BaseEnum> getChildEnums(String parentName, Class<? extends Enum<? extends BaseEnum>> type) {
		List<BaseEnum> list = new ArrayList<>();
		Enum<? extends BaseEnum>[] enums = type.getEnumConstants();
		for (Enum<? extends BaseEnum> e : enums) {
			String name = e.name();
			String[] nameArr = name.split("__");
			String key = nameArr[0];
			if (key.equals(parentName.toString())){
				list.add((BaseEnum)e);
			}
		}
		return list;
	}
	
	public static BaseEnum getParentEnum(Integer value, Class<? extends Enum<? extends BaseEnum>> type, Class<? extends Enum<? extends BaseEnum>> parentType) {
		Enum<? extends BaseEnum>[] enums = type.getEnumConstants();
		for (Enum<? extends BaseEnum> e : enums) {
			BaseEnum baseEnum = (BaseEnum) e;
            if (baseEnum.getValue().equals(value)) {
                String name = e.name();
                String[] nameArr = name.split("__");
    			return getEnumByName(nameArr[0], parentType);
            }
		}
		return null;
	}
	
}
