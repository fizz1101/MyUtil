package com.fizz.encrypt;

import com.fizz.encrypt.core.Base64Util;
import com.fizz.encrypt.core.DESUtil;
import com.fizz.encrypt.core.MD5Util;

public class EncryptTest {

    public static void main(String[] args) {
        String strMing = "fizz";
        String primaryKey = "921101";
        System.out.println("Base64加密：" + Base64Util.encode(strMing));
//        System.out.println("Base64加密：" + Base64Util.encodeByApache(strMing));
        System.out.println("DES加密：" + DESUtil.encrypt(strMing, primaryKey));
        System.out.println("MD5加密：" + MD5Util.get32MD5(strMing));
    }

}
