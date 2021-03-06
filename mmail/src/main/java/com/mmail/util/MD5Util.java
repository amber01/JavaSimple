package com.mmail.util;

import com.mmail.common.Const;

import java.security.MessageDigest;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 18:26 2018/9/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class MD5Util {
    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    /**
     * MD5加密
     * @param origin 字符
     * @param charsetname 编码
     * @return
     */
    public static String MD5Encode(String origin){
        String resultString = null;
        String charsetname = "utf8";
        try{
            resultString = new String(origin + Const.PASSWORD_SALT);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(null == charsetname || "".equals(charsetname)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        }catch (Exception e){
        }
        return resultString;
    }


    public static String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }
}
