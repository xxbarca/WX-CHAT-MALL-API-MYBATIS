package com.ly.imallbatis.core.money;

import java.math.BigDecimal;

public interface IMoneyDiscount {
    /**
     * @param original 原价
     * @param discountRate 折扣率
     * */
    BigDecimal discount(BigDecimal original, BigDecimal discountRate);
}
