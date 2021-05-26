package com.wuzlin.springbootquickdemo.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    public static final String KEY_ALGORITHM = "RSA";


    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }


    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
        return keyMap;
    }
  /*  *//**
     * 产生public key
     * @return public key字符串
     *//*
    public static String generateBase64PublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // encodeBase64(): Encodes binary data using the base64
        // algorithm but does not chunk the output.
        // getEncoded():返回key的原始编码形式
        return new String(Base64.encodeBase64(publicKey.getEncoded()));
    }

    *//**
     * 产生public key
     * @return public key字符串
     *//*
    public static String generateBase64PrivateKey() {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // encodeBase64(): Encodes binary data using the base64
        // algorithm but does not chunk the output.
        // getEncoded():返回key的原始编码形式
        return new String(Base64.encodeBase64(privateKey.getEncoded()));
    }*/
    /**
     * 解密数据
     * @param string 需要解密的字符串
     * @return 破解之后的字符串
     */
    public static String decryptBase64(String string, String privateKey) {
        // decodeBase64():将Base64数据解码为"八位字节”数据
        return new String(decrypt(Base64.decodeBase64(string), privateKey));
    }

    private static byte[] decrypt(byte[] byteArray, String privateKey) {
        try {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(privateKey);
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            return cipher.doFinal(byteArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    public static void main(String[] args) throws Exception {
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI6hiym1bhZSZxT2DH6CVIPBO2vdPBtN89Eruc/knIFtyHLpeTOkCLUFdJfL2Gibvhu5GI6y0lfuibbMJ0Ie9rOVSC6tuUhMjv6iqYoty68jc9RRai0IPm6XlVtw7KJw40BpqfzcWjKz/YUPV8WcAMx+aTsp2cQ42FkHK1T+KTPxAgMBAAECgYAJJjuzFTxWmjqakZ4Dj4C0YvTY2nw8lCeLx25H87ZGZzBwjqGDuoYEdrEf+6eKLlNnm9wDa10q3tZer4HlL+9LRTaRx8Ma2MyEhAkx3wh/0ILRwT1ribWr8Qqh2bHC4wry0vXC7VSSTAw0fSTebZfrhqPz3DB20p5qyusIf73E8QJBAM372MZb2peugs69l0VmIADLPWa2YU2P7jTxbyUTnKlLEOXP9zMdhZMqq+ToffQL16hZVNSYx9UyyaLP7lbPqBMCQQCxQ6DInsrCT/ZQSR939o7UjahzZkpxSoi+zKr/Y1bXjw8/IItwwe+WUbgzXUf+SK3MmCag/JnL0ok4pT2QULxrAkAqAaMqiI7igfcxHB7eSz5ZixkBek4LmD+W0mm9IOvA5osSor+hz/JumbQmYXdWLpgTP+x50pJmGw5dwpOzZnO7AkBDqydjroqEZvYGbAOhTfP0XCdj9jaQzP0qqQLV/1wVab1OQeQ5JS+K2KNSqFdlUOVX5vVIG2Wykypi/2FjCA8FAkAYYlxzZvICvHbQjP4LF7hliVOUPkpymgIAz1YauYQh26XX08aQ07x8vMDzvaAyYTFDS7n65mssOzHPWcVrxEeB";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOoYsptW4WUmcU9gx+glSDwTtr3TwbTfPRK7nP5JyBbchy6XkzpAi1BXSXy9hom74buRiOstJX7om2zCdCHvazlUgurblITI7+oqmKLcuvI3PUUWotCD5ul5VbcOyicONAaan83Foys/2FD1fFnADMfmk7KdnEONhZBytU/ikz8QIDAQAB\n";
        String enStr = encrypt("qwe123",publicKey);
        String deStr = decryptBase64("MQd971OOmVjZ2C21NnO29Iec4qSGFkRclHc1spM/81zCErid7bn9upFXsGk4J72oTHANTd6naMGrdLxj3Wp3OR/OeWlApXHi7/0epU+s4WwrMhNQIh6/hDd4Vl6tsKCvHSZtwJ6Ov6nE0wiLmyfwVVCV75wJLPrmPTi50P/52qc=",privateKey);
        System.out.println(enStr+"\n"+deStr);

    }
}
