package com.fresh.design.pattern.strategy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.SortedMap;

public class CallPriceProxy implements InvocationHandler {
    private SortedMap<Integer, Class<? extends CalPrice>> sortedMap;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        BigDecimal result = BigDecimal.ZERO;
        if (method.getName().equals("calPrice")) {
            for (Class<? extends CalPrice> clazz : sortedMap.values()) {
                if (result.compareTo(BigDecimal.ZERO) == 0) {
                    result = (BigDecimal) method.invoke(clazz.newInstance(), args);
                } else {
                    result = (BigDecimal) method.invoke(clazz.newInstance(), result);
                }
            }
        }
        return result;
    }

    private CallPriceProxy(SortedMap<Integer, Class<? extends CalPrice>> sortedMap) {
        super();
        this.sortedMap = sortedMap;
    }

    public static CalPrice getCallPriceProxy(SortedMap<Integer, Class<? extends CalPrice>> sortedMap) {
        return (CalPrice) Proxy.newProxyInstance(CallPriceProxy.class.getClassLoader(),
                new Class[]{CalPrice.class},
                new CallPriceProxy(sortedMap));
    }

}
