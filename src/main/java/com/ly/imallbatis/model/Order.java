package com.ly.imallbatis.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    private Long id;

    private String orderNo;
    private Long userId;
    private BigDecimal totalPrice;
    private Long totalCount;
    private String snapImg;
    private String snapTitle;
    // 过期时间
    private Date expiredTime;
    // 下单时间
    private Date placedTime;

    /**保存当前订单里面的所有要购买的sku信息*/
    private String snapItems;

    private String snapAddress;
    private String prepayId;
    private BigDecimal finalTotalPrice;
    private Integer status;
}
