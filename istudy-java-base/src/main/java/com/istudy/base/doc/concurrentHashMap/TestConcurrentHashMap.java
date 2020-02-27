package com.istudy.base.doc.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, "测试1");
        concurrentHashMap.put(2, "测试2");

        concurrentHashMap.get(1);
    }
}
