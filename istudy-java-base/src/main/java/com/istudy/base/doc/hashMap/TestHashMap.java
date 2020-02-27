package com.istudy.base.doc.hashMap;

import java.util.HashMap;

public class TestHashMap {

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(1, "测试1");
        hashMap.put(2, "测试2");

        hashMap.get(1);

    }
}
