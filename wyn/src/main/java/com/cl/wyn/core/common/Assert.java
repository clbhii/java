package com.cl.wyn.core.common;

import com.cl.wyn.core.util.common.StringUtil;

/**
 * by cl at 2020/7/2 0002
 */
public class Assert {

    public static void notEmpty(String paramName, String paramValue) {
        if(StringUtil.isEmpty(paramValue)){
            throw new BizException(ErrorCode.PARAM_EMPTY, paramName + "不能为空");
        }
    }
}
