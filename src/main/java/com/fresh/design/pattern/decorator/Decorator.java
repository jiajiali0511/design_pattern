package com.fresh.design.pattern.decorator;

/**
 * create by lijiajia on 2017/12/6
 */
public abstract class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        super();
        this.component = component;
    }

    public void method() {
        component.method();
    }
}
