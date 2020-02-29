package com.istudy.jvm.doc.online;

public class Test {
    public static void main(String[] args) {
        int s=0;
        for(int a=3;a<=10;a++){ //循环8次 a的值 3,4,5,6,7,8,9,10
            for(int b=2;b<a;b++){ //b的值 2,3,4,5,6,7,8,9
                if(a%b == 0){
                   s=s+a;
                   break;
                }
            }
        }

        System.out.println(s);
    }
}
