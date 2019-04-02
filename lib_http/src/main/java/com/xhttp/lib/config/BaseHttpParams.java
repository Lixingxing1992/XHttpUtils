package com.xhttp.lib.config;

/**
 * Created by lixingxing on 2019/3/26.
 */
public class BaseHttpParams {
    public String tags = "";

    public boolean openLog = true;
    // 请求地址
    public String url;
    // 参数
    public Object params;
    // 数据解析类
    public Class clz;
    // 请求方式
    public BaseHttpConfig.RequestType request_type = BaseHttpConfig.RequestType.POST;

    public boolean success;
}