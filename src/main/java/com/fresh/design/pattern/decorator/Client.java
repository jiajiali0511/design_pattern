package com.fresh.design.pattern.decorator;

/**
 * create by lijiajia on 2017/12/6
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        ConcreteDecoratorA concreteDecoratorA = new ConcreteDecoratorA(component);
        concreteDecoratorA.method();
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(concreteDecoratorA);
        concreteDecoratorB.method();
    }
}
