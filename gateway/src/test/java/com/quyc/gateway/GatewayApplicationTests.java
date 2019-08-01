package com.quyc.gateway;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

    @Test
    public void contextLoads() {
        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.systemDefault());
        System.out.println("now = " + now);
    }

}
