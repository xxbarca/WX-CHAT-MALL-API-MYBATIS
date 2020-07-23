package com.ly.imallbatis.core.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HalfUpRound implements IMoneyDiscount {

    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discountRate) {

        BigDecimal actualMoney = original.multiply(discountRate);

        BigDecimal finalMoney = actualMoney.setScale(2, RoundingMode.HALF_UP);

        return finalMoney;
    }
}
