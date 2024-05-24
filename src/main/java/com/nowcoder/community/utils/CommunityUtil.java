package com.nowcoder.community.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: 常用工具类
 * @date 2024/5/19 12:24
 */

public class CommunityUtil {
    /**
     * 生成随机字符串
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * MD5加密（传入盐值）
     */

    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    /**
     * 封装前端返回信息-通用返回类
     * @param code
     * @param msg
     * @param map
     * @return
     */

    // 初始化返回响应的格式-同通用返回类是一致的，这里用map来实现的
    public static String getJsonString(int code, String msg, Map<String, Object> map) {
    //     基于fastJson来封装json-作为响应返回
        JSONObject json = new JSONObject();

        // code、msg是固有字段
        json.put("code", code);
        json.put("msg", msg);

        // 放入map中的数据字段
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        // 封装为json
        return json.toJSONString();
    }

    /**
     * 不含数据的返回响应格式(使用重载）
     * @param code
     * @param msg
     * @return
     */
    public static String getJSONString(int code, String msg) {
        return getJsonString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJsonString(code, null, null);
    }


    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 25);
        System.out.println(getJsonString(0, "ok", map));
    }
}
