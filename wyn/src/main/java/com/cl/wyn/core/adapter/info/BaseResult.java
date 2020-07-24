package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/6/24 0024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public  class  BaseResult<T> implements Serializable {

    /**
     * 返回代码1：成功 0：失败 -1：参数错误 -2：系统异常 -3：凭证错误
     */
    @JsonProperty("Result")
    private String result;
    /**
     * 错误信息
     */
    @JsonProperty("Message")
    private String message;
    /**
     * 返回数据
     */
    @JsonProperty("Data")
    private T data;

}
