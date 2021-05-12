package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class MsmServiceImpl implements MsmService {
    /**
     * 发送短信
     */
    @Override
    public boolean send(Map<String, Object> param, String phone) {

        if (StringUtils.isEmpty(phone)) return false;

        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "**code**:"+param.get("code")+",**minute**:15");
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "a09602b817fd47e59e7c6e603d3f088d");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            return true;
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }


//            DefaultProfile profile =
//                    DefaultProfile.getProfile("default", "LTAI4GJ6aa5zmUTuAMmteoss", "53eD9YFsPyTEHxBLxgpyB1mDY4ChHD");
//            IAcsClient client = new DefaultAcsClient(profile);
//
//            CommonRequest request = new CommonRequest();
//            //request.setProtocol(ProtocolType.HTTPS);固定参数
//            request.setMethod(MethodType.POST);
//            request.setDomain("dysmsapi.aliyuncs.com");
//            request.setVersion("2017-05-25");
//            request.setAction("SendSms");
//
//            request.putQueryParameter("PhoneNumbers", phone);//设置手机号
//            request.putQueryParameter("SignName", "我的好学网在线教育平台");//签名名
//            request.putQueryParameter("TemplateCode", "SMS_199222499");//模板编号
//            request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
//
//            try {
//                //最终发送
//                CommonResponse response = client.getCommonResponse(request);
//                System.out.println(response.getData());
//                return response.getHttpResponse().isSuccess();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        return false;
    }

    public static void main(String[] args) {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "25ae8fd6e4ba477c8eb12722ea4c1844";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "15574945470");
        querys.put("param", "**code**:4575,**minute**:15");
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "a09602b817fd47e59e7c6e603d3f088d");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}