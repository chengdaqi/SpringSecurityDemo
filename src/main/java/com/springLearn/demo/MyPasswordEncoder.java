package com.springLearn.demo;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    private static String SALT = "123456";
    @Override
    public String encode(CharSequence rawPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();

        return encoder.encodePassword(rawPassword.toString(),SALT);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.isPasswordValid(charSequence.toString(),s,SALT);
    }
}
