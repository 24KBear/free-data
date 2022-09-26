package com.fty1.tools.free.data.kernel;


import lombok.Data;

@Data
public class RowData {
    /**
     * index
     */
    private int index;
    /**
     * 字段名称
     */
    private String column;
    /**
     * 数值
     */
    private RowValue value;
}
