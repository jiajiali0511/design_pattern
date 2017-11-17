package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */
@TotalValidRegion(max = 1000)
public class Common implements CalPrice {
    @Override
    public BigDecimal calPrice(BigDecimal originalPrice) {
        return originalPrice;
    }
}
