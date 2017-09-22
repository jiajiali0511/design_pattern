package com.fresh.design.pattern.singleton;

/**
 * Created by ljiajiali on 17-9-22.
 * 教科书中标准的单例模式
 */

public class Singleton3 {
    private static Singleton3 singleton3;
    private Singleton3() {}
    public Singleton3 getInstance() {
        if (singleton3 == null) {
            synchronized (this) {
                if (singleton3 == null) {
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }
}

/**
 * 这样比Singleton2无脑的同步好多了，单这里有两次判断null，可以想一下哦
 */

//但是在jvm层面来说可能有问题

/**
 * jvm 创建对象有共三步
 * 1.分配内存
 * 2.初始化构造器
 * 3.将对象指向分配的内存地址
 */

/**
 * 如果jvm老老实实的创建对象（如上），Singleton3是没问题的
 * 但jvm有时候根据字节码调优，有一项调优就是调整指令顺序，因此2和3可能颠倒
 * 所以:jvm可能将对象创建顺序调整为132，即分配内存后将对象指向了分配内存的地址
 * 所以后续线程获取实例就误认为已经实例化了，在使用时会出现错误
 */
//因此出现了Singleton4