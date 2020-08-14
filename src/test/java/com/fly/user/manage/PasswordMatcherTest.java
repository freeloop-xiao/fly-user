package com.fly.user.manage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/7 17:36
 */
@SpringBootTest
public class PasswordMatcherTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void matcher(){
        String password = "123456";
        String oldPassword = "$2a$10$IH4xXp2r5LvEQrpOyatunuwt5OwWp3RmugrwtBROVcW.vlQ8cHQ5u";
        String encode = bCryptPasswordEncoder.encode(password);
        System.out.println(encode);
        System.out.println(bCryptPasswordEncoder.matches(password,oldPassword));
    }
}
