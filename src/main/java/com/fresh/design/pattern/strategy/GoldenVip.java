package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */
@TotalValidRegion(min = 3000)
public class GoldenVip implements CalPrice {
    public BigDecimal calPrice(BigDecimal originalPrice) {
        return originalPrice.multiply(new BigDecimal(0.5));
    }
}
