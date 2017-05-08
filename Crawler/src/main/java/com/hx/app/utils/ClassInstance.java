package com.hx.app.utils;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class ClassInstance<E> {

    public E getInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(className);
        return (E)c.newInstance();
    }
}
