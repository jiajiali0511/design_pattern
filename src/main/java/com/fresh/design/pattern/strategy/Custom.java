package com.fresh.design.pattern.strategy;

import java.math.BigDecimal;

/**
 * Created by ljiajiali on 17-11-16.
 */
public class Custom {
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal currentAmount = BigDecimal.ZERO;
    private CalPrice calPrice = new Common();

    public void buy(BigDecimal amount) {
        this.currentAmount = amount;
        totalAmount = totalAmount.add(amount);
        calPrice = CalPriceFactory.getInstance().createCalPrice(this);
    }

    public BigDecimal calLastAmount() {
        return calPrice.calPrice(totalAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
