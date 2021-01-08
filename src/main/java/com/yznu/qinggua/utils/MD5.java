package com.yznu.qinggua.utils;

import org.springframework.util.DigestUtils;

public class MD5 {
    public static String getMD5(String code) {
        // MD52次加密
        return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(code.getBytes()).getBytes());
    }
}
