package com.team.assessment.config.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WechatUtil {

    @Value("${WeChat.appid}")
    private String appid;

    @Value("${WeChat.secret}")
    private String secret;

    private static String addidStatic;

    private static String secretStatic;

    @PostConstruct
    public void getSecret() {
        secretStatic = secret;
    }

    @PostConstruct
    public void getAppid() {
        addidStatic = appid;
    }

    public static JSONObject getSessionKeyOrOpenId(String code) throws IOException {

        System.out.println(addidStatic);
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", addidStatic);
        //小程序secret
        requestUrlParam.put("secret", secretStatic);
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }
}

