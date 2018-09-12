package com.mmail;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 15:54 2018/9/12
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class BigDecimalTest {
    @Test
    public void test1(){
        System.out.println(0.05+0.01);
        System.out.println(1.0-0.42);
    }

    @Test
    public void test2(){
        BigDecimal b1 = new BigDecimal(0.05);
        BigDecimal b2 = new BigDecimal(0.01);
        System.out.println(b1.add(b2));
    }

    @Test
    public void test3(){
        BigDecimal b1 = new BigDecimal("0.05");
        BigDecimal b2 = new BigDecimal("0.0012");
        BigDecimal b3 = new BigDecimal("0.2");
        System.out.println(b1.add(b2).add(b3));
    }
}
