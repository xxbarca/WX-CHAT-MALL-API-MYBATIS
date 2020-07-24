package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    int save(Order order);

}
