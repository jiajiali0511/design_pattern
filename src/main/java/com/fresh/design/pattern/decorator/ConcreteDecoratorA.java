package com.fresh.design.pattern.decorator;

/**
 * create by lijiajia on 2017/12/6
 */
public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void method() {
        System.out.println("包装A");
        super.method();
        System.out.println("A包装完毕");
    }
}
