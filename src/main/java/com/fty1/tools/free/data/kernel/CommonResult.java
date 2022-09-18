package com.fty1.tools.free.data.kernel;

public interface CommonResult {


    /**
     * 是否成功
     *
     * @return
     */
    Boolean success();


    /**
     * 错误编码
     *
     * @return
     */
    String errorCode();

    /**
     * 错误信息
     *
     * @return
     */
    String errorMessage();
}
