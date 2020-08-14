package com.fly.user;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/8 20:35
 */
public class IdUtilTest {


    @Test
    public void generate(){
        Snowflake snowflake = IdUtil.getSnowflake(1L,1L);
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextIdStr());
        System.out.println(snowflake.nextIdStr().length());
    }

    @Test
    public void generateByDate(){
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        System.out.println(LocalDateTime.now().format(formatter));
    }

}
