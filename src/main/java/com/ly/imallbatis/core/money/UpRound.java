package com.ly.imallbatis.core.money;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class UpRound implements IMoneyDiscount {
    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discountRate) {

        BigDecimal actualMoney = original.multiply(discountRate);

        BigDecimal finalMoney = actualMoney.setScale(2, RoundingMode.UP);

        return finalMoney;
    }
}
