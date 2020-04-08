package com.supcon.whd.compiler.test;

import java.lang.reflect.Field;

public class Test {
    public static void main(String [] args){
        Class clazz=Student.class;
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getSimpleName());
        Field[] fields=clazz.getDeclaredFields();
        try {
            Student o= (Student) clazz.newInstance();
            for(Field field:fields){
                field.setAccessible(true);
                Annotation annotation=field.getAnnotation(Annotation.class);
                String name=annotation.name();
                field.set(o,name);
            }
            System.out.print("name="+o.name);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
