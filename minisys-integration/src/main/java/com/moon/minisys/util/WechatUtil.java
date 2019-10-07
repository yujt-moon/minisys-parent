package com.moon.minisys.util;

import java.util.Arrays;

/**
 * @author yujiangtao
 * @date 2018/3/25 13:36
 */
public class WechatUtil {

    /**
     * 判断是否校验成功
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String token, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        // 排序
        Arrays.sort(arr);

        // 生成字符串
        StringBuffer content = new StringBuffer();
        for(int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        // sha1加密
        String encryptStr = SHA1.encode(content.toString());
        return encryptStr.equals(signature);
    }
}
