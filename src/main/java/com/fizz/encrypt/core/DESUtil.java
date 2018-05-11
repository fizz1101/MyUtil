package com.fizz.encrypt.core;

import java.io.UnsupportedEncodingException;

/**
 * DES加密工具类
 */
public class DESUtil {

    private static String ALGORITHM = "DES";

    /**
     * 加密字符串
     * @param strMing 需加密的字符串
     * @param key 秘钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String strMing, String key) {
        String result = null;
        try {
            CipherUtil cipherUtil = new CipherUtil(strMing, null, key, ALGORITHM);
            byte[] mi = cipherUtil.encrypt();
            result = new String(Base64Util.encode(mi));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解密字节串
     * @param strMi 需解密的字符串
     * @param key 秘钥
     * @return 解密后的字节数组
     * @throws Exception
     */
    public static String decrypt(String strMi, String key) {
        String result = null;
        try {
            strMi = Base64Util.decode(strMi);
            CipherUtil cipherUtil = new CipherUtil(null, strMi.getBytes(), key, ALGORITHM);
            result = cipherUtil.decrypt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
