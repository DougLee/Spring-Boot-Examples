package com.example.springbootshiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Douglee on 2018/3/30.
 */
public class MD5Utils {
    private static final String SALT = "clouddb";
    private static final String ALGORITH_NAME = "md5";
    public static final int HASH_ITERATIONS = 2;

    public static String encrypt(String password){
        String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String userName, String password){
        String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(userName.toLowerCase() + SALT),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }

}
