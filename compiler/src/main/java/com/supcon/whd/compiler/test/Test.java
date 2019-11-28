package com.supcon.whd.compiler.test;

public class Test {
    public static void main(String [] args){
        for (int i = 1; i < 400; i++) {
            System.out.println("<dimen name=\"dp_"+i+"\""+">"+i+"dp</dimen>");
        }
    }
}
