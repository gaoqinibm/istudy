package com.istudy.common;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author Baizhen
 * @Date 2020/5/13 21:24
 */
public class Test {

    public static void main(String[] args) {
        Arrays.asList("a","b","c").forEach(s->System.out.println(s)); // 前
        Arrays.asList("a","b","c").forEach(System.out::println); // 前
    }
}
