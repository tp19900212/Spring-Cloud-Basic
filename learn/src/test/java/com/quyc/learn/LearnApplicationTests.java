package com.quyc.learn;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnApplicationTests {

    @Test
    public void test() {
        System.out.println("\"abc\".hashCode() = " + "abc".hashCode());
        System.out.println("Math.abs(Integer.MIN_VALUE) = " + Math.abs(Integer.MIN_VALUE));
    }

}
