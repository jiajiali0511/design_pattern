package com.fresh.design.pattern.strategy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sun.istack.internal.Nullable;
import org.jboss.logging.Message;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ljiajiali on 17-11-16.
 */
public class Client {
    public static void main(String[] args) {
        Custom custom = new Custom();
        custom.buy(new BigDecimal(2200));
        System.out.println(custom.calLastAmount());
        System.out.println(System.currentTimeMillis());
    }

}
