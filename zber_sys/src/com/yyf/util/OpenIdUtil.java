package com.yyf.util;

import java.io.IOException;

import net.sf.json.JSONObject;

/**
 * @author xsx
 */
public class OpenIdUtil {
	
    public static String oauth2GetOpenid(String code,String classify) {
        String appid="";
        String appsecret="";
        switch (classify){
        	//搭伙小程序id
            case "1":
                //自己的配置appid
                appid = "wxdb07051dc3fc031e";
                //自己的配置APPSECRET;
                appsecret = "b2dec689f9b117a311891c6ac5ae9407";
                break;
            //周边小程序
            case "2":
                appid = "wx2c5b4fc4466c3e4e";
                appsecret = "955755f5b32225c397c9a61a044b5db6";
                break;
            case "3":
                appid = "**********";
                appsecret = "************";
                break;
            case "4":
                appid = "**********";
                appsecret = "************";
                break;
            case "5":
                appid = "**********";
                appsecret = "************";
        }

        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpUtil.get(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject  json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
        String Openid =String.valueOf(json.get("openid"));
        //System.out.println(Openid);
        return Openid;
    }
}
