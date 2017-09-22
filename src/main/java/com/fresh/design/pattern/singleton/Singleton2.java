package com.fresh.design.pattern.singleton;

/**
 * Created by ljiajiali on 17-9-21.
 * 因为Singleton1在多线程访问的情况下会出现多个实例
 * 因此有人说在方法中加入 synchronized 关键字，但这样也不好，如下
 */
public class Singleton2 {
    private static Singleton2 singleton2;

    private Singleton2() {}

    public synchronized static Singleton2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}

/**
 * 将这个获取实例的方法同步，会使其他线程都处于挂起的状态，这样太糟糕了，会造成无畏的等待
 * 于是有了Singleton3
 */
