package com.fresh.design.pattern.strategy;

import com.google.common.collect.Lists;
import org.jboss.logging.Message;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ljiajiali on 17-11-16.
 */
public class Client {
    public static void main(String[] args) {
        Custom custom = new Custom();
        custom.buy(new BigDecimal(2200));
        System.out.println(custom.calLastAmount());
    }
}
