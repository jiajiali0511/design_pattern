package com.fresh.design.pattern.strategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TotalValidRegion {
    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
}
