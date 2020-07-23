package com.ly.imallbatis.service;

import com.ly.imallbatis.dto.OrderDTO;
import com.ly.imallbatis.logic.OrderChecker;

public interface OrderService {

    OrderChecker isOk(Long uid, OrderDTO orderDTO);

}
