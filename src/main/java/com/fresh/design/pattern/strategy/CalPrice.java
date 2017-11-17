package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */
public interface CalPrice {
    BigDecimal calPrice(BigDecimal originalPrice);
}
