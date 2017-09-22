package com.fresh.design.pattern.singleton;

/**
 * Created by ljiajiali on 17-9-22.
 */
public class Singleton4 {

    private Singleton4() {}

    public Singleton4 getInstance() {
        return Singleton4Instance.singleton4;
    }

    private static class Singleton4Instance {
        static Singleton4 singleton4 = new Singleton4();
    }
}

//为什么不会出现3的问题
/**
 * 类的静态属性只会在类加载时进行初始化，如果初始化一半线程想访问，那么jvm宝宝是拒绝的
 * 静态属性独有一份
 */