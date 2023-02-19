package com.team.assessment.config.utils;

import org.springframework.stereotype.Component;

@Component
public class WechatUtil {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${WeChat.appid}")
//    private String appid;
//
//    @Value("${WeChat.secret}")
//    private String secret;
//
//    private static String addidStatic;
//
//    private static String secretStatic;
//
//    @PostConstruct
//    public void getSecret() {
//        secretStatic = secret;
//    }
//
//    @PostConstruct
//    public void getAppid() {
//        addidStatic = appid;
//    }
//
//    public static JSONObject getSessionKeyOrOpenId(String code) throws IOException {
//        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
//        Map<String, String> requestUrlParam = new HashMap<>();
//        //小程序appId
//        requestUrlParam.put("appid", addidStatic);
//        //小程序secret
//        requestUrlParam.put("secret", secretStatic);
//        //小程序端返回的code
//        requestUrlParam.put("js_code", code);
//        //默认参数
//        requestUrlParam.put("grant_type", "authorization_code");
//        //发送post请求读取调用微信接口获取openid用户唯一标识
//        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
//        jsonObject.put("appid", addidStatic);
//        if (jsonObject.getString("session_key") == null || jsonObject.getString("openid") == null) {
//            return null;
//        }
//        jsonObject.put("token", TokenUtils.createToken(jsonObject.getString("openid")));
//
//        String phoneNum = getPhoneNum(code);
//        jsonObject.put("phoneNum", phoneNum);
//        return jsonObject;
//    }
//
//
//    public static String getPhoneNum(String code) throws IOException {
//
//        String requestTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + addidStatic + "&secret=" + secretStatic;
//        //发送post请求读取调用微信接口获取openid用户唯一标识
//        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doGet(requestTokenUrl));
//        if (!jsonObject.containsKey("access_token")) {
//            throw new CustomException(ErrorCode.WECHAT_ERROR.getCode(), ErrorCode.WECHAT_ERROR.getMessage());
//        }
//        String accessToken = jsonObject.get("access_token").toString();
//
//        String requestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
//        JSONObject phoneInfo = new JSONObject();
//        try {
//            JSONObject jsonCode = new JSONObject();
//            jsonCode.put("code", code);
//            JSONObject jsonObject1 = JSON.parseObject(
//                    HttpClientUtil.httpPostRaw(requestUrl, jsonCode.toJSONString(), null, null));
//            if (Objects.isNull(jsonObject1)) {
//                throw new CustomException(ErrorCode.WECHAT_ERROR.getCode(), ErrorCode.WECHAT_ERROR.getMessage());
//            }
//            phoneInfo = jsonObject1.getJSONObject("phone_info");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String phontNum = phoneInfo.getString("purePhoneNumber");
//        return phontNum;
//    }
}

