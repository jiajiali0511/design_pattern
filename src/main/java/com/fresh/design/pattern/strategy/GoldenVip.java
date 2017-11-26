package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */

@TotalValidRegion(@ValidRegion(min = 3000, order = 99))
public class GoldenVip implements CalPrice {
    @Override
    public BigDecimal calPrice(BigDecimal originalPrice) {
        return originalPrice.multiply(new BigDecimal(0.5));
    }
}
