package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */
@TotalValidRegion(min = 2000, max = 3000)
public class SuperVip implements CalPrice {
    @Override
    public BigDecimal calPrice(BigDecimal originalPrice) {
        return originalPrice.multiply(new BigDecimal(0.7));
    }
}
