package com.team.assessment.config.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TokenUtils {

    @Value("${WeChat.appid}")
    private String appid;

    @Value("${WeChat.secret}")
    private String secret;

    private static String appidStatic;

    private static String secretStatic;

    @PostConstruct
    public void getSecret() {
        secretStatic = secret;
    }

    @PostConstruct
    public void getAppid() {
        appidStatic = appid;
    }

    //密钥
    public static final String SECRET = "youareapig??shabixiangpojie?";
    //过期时间:秒
    public static final int EXPIRE = 86400*1000;

    /**
     * 生成token
     *
     * @param openId 小程序open_id
     * @return token
     */
    public static String createToken(String sessionKey,String openId) {

        Calendar nowTime = Calendar.getInstance();
        //过期时间
        nowTime.add(Calendar.MILLISECOND, EXPIRE);
        Date expireDate = nowTime.getTime();
        // 获得JWT构造器
        JwtBuilder builder = Jwts.builder();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sessionKey", sessionKey);
        hashMap.put("openId", openId);
        hashMap.put("appId",appidStatic);
        hashMap.put("time",System.currentTimeMillis());

        return builder.setSubject("token")
                .setClaims(hashMap)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }


    /**
     * 解析token
     *
     * @param token token
     * @param key   key
     * @return 结果
     */
    public static String getString(String token, String key) {
        if (StringUtils.isEmpty(token)) return null;
        try {
            Jws<Claims> claimsJws
                    = Jwts.parser() // 构造器
                    .setSigningKey(SECRET) // 加盐
                    .parseClaimsJws(token);
            // 获取token里的信息
            Claims claims = claimsJws.getBody();
            return (String) claims.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean verify(String token){
        try {
            Long time = getTime(token);
            Long now = System.currentTimeMillis();
            if(now - time > EXPIRE){
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    // 获取token的openId
    public static String getSessionKey(String token) {
        return getString(token, "sessionKey");
    }

    // 获取userName
    public static String getOpenId(String token) {
        return getString(token, "openId");
    }

    // 获取wechatUserId
    public static String getAppId(String token) {
        return getString(token, "appId");
    }

    public static Long getTime(String token){
        return Long.parseLong(getString(token, "time"));
    }

}

