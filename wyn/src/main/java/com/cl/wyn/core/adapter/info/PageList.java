package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * by cl at 2020/6/24 0024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageList<T> implements Serializable {
    /**
     * 列表
     */
    @JsonProperty("Rows")
    private List<T> rows;
    /**
     * 总数
     */
    @JsonProperty("Total")
    private Integer total;
}
