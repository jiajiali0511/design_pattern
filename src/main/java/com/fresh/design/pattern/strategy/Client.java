package com.fresh.design.pattern.strategy;

import org.jboss.logging.Message;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * Created by ljiajiali on 17-11-16.
 */
public class Client {
    public static void main(String[] args) {
        Custom custom = new Custom();
        custom.buy(new BigDecimal(2200));
        System.out.println(custom.calLastAmount());
        custom.buy(new BigDecimal(500));
        System.out.println(custom.calLastAmount());
        custom.buy(new BigDecimal(1200));
        System.out.println(custom.calLastAmount());
    }
}
