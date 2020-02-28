package com.istudy.base.doc.volatile_;

public class VolatileDemo {

    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }
}
