package com.ly.imallbatis.bo;

import com.ly.imallbatis.dto.SkuInfoDTO;
import com.ly.imallbatis.model.Sku;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 描述 order 中的 sku
 * */
@Getter
@Setter
public class SkuOrderBO {

    private BigDecimal actualPrice;

    private Integer count;

    private Long categoryId;

    public SkuOrderBO(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.actualPrice = sku.getActualPrice();
        this.count = skuInfoDTO.getCount();
        this.categoryId = sku.getCategoryId();
    }

    public BigDecimal getTotalPrice() {
        return actualPrice.multiply(new BigDecimal(count));
    }
}
