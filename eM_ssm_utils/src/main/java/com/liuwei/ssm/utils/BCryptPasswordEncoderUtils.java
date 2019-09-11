package com.liuwei.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String psw){
        return bCryptPasswordEncoder.encode(psw);
    }

    public static void main(String[] args) {
        String psw = "123";
        System.out.println(encodePassword(psw));
    }
}
