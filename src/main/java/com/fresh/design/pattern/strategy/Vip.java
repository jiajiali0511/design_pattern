package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */
@TotalValidRegion(min = 1000, max = 2000)
public class Vip implements CalPrice {
    public BigDecimal calPrice(BigDecimal originalPrice) {
        return originalPrice.multiply(new BigDecimal(0.8));
    }
}
