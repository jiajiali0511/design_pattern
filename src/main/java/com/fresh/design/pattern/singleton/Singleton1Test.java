package com.fresh.design.pattern.singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ljiajiali on 17-9-21.
 */
public class Singleton1Test {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Set<String> instanceSet = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Singleton1 singleton1 = Singleton1.getInstance();
                    instanceSet.add(singleton1.toString());

                }
            });
        }
        for (String str : instanceSet) {
            System.out.println(str);
        }
        executorService.shutdown();
    }
}
//一下是某次运行结果
/*com.fresh.design.pattern.singleton.Singleton1@50e26ae7
com.fresh.design.pattern.singleton.Singleton1@23bf011e*/
