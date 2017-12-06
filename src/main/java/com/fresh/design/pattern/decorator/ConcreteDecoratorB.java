package com.fresh.design.pattern.decorator;

/**
 * create by lijiajia on 2017/12/6
 */
public class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void method() {
        System.out.println("包装B");
        super.method();
        System.out.println("B包装完毕");
    }

}
