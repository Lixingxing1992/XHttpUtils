package com.xhttp.lib.impl.service;

import android.util.Log;

import com.xhttp.lib.config.BaseHttpConfig;
import com.xhttp.lib.config.BaseHttpParams;
import com.xhttp.lib.interfaces.http.IHttpService;
import com.xhttp.lib.model.BaseRequestResult;
import com.xhttp.lib.util.RequestUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Json请求
 * Created by lixingxing on 2019/3/27.
 */
public class JsonHttpService implements IHttpService {

    @Override
    public Object parseParams(BaseHttpParams baseHttpParams) {
        String params = "";
        // 如果是post提交方式 处理参数
        if(baseHttpParams.request_type == BaseHttpConfig.RequestType.POST){
            if(baseHttpParams.params instanceof Object[]){
                Object[] p = (Object[]) baseHttpParams.params;
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < p.length; i+=2) {
                    try {
                        jsonObject.put((String) p[i],p[i+1]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                params = jsonObject.toString();
            }else if(baseHttpParams.params instanceof Map){
                Map p = (Map) baseHttpParams.params;
                JSONObject jsonObject = new JSONObject(p);
                params = jsonObject.toString();
            }else{
                params = (String) baseHttpParams.params;
            }
            if (baseHttpParams.openLog) {
                Log.e("BaseHttpUtils", baseHttpParams.tags + ":\n" +
                        baseHttpParams.request_type.toString() + "\n" +
                        baseHttpParams.dataParseType.toString()+ "\n" +
                        baseHttpParams.url + "\n" +
                        params);
            }
        }else{
            if (baseHttpParams.openLog) {
                Log.e("BaseHttpUtils", baseHttpParams.tags + ":\n" +
                        baseHttpParams.request_type.toString() + "\n" +
                        baseHttpParams.dataParseType.toString()+ "\n" +
                        baseHttpParams.url);
            }
        }
        return params;
    }

    @Override
    public BaseRequestResult request(BaseHttpParams baseHttpParams) {
        BaseRequestResult request = new RequestUtil(baseHttpParams)
                .Reqeust_ConnectTimeOut(baseHttpParams.timeout_connect)
                .Reqeust_ReadTimeOut(baseHttpParams.timeout_read)
                .Request_ContentType(BaseHttpConfig.ParamType.JSON)
                .Request_requestType(baseHttpParams.request_type)
                .request((baseHttpParams.params == null ? "" : baseHttpParams.params.toString()), baseHttpParams.url);
        return request;
    }

}
