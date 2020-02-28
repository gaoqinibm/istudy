package com.istudy.base.doc.synchronize;

public class SynchronizedDemo {

    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }
}
