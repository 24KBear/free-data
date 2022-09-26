package com.fty1.tools.free.data.kernel;


import lombok.Data;

@Data
public class RowValue {
    /**
     * 数据类型
     */
    private RowDataType rowType;
    /**
     * column 对应的数据
     */
    private Object rowValue;
}
