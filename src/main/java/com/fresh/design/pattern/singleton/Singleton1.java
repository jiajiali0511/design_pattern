package com.fresh.design.pattern.singleton;

/**
 * Created by ljiajiali on 17-9-21.
 * 不考虑并发
 */
public class Singleton1 {

    private static Singleton1 singleton1;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (singleton1 == null) {
            singleton1 = new Singleton1();
        }
        return singleton1;
    }
}
