package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

@OnceValidRegion(@ValidRegion(min = 2000, max = 3000, order = 40))
public class OneFullReduce implements CalPrice {
    @Override
    public BigDecimal calPrice(BigDecimal originalPrice) {
        return originalPrice.subtract(new BigDecimal(400));
    }
}
