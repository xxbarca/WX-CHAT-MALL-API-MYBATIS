package com.ly.imallbatis.dao;

import com.ly.imallbatis.model.Order;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int save(Order order);

    List<Order> getUnPaid(@Param("uid") Long uid, @Param("unpaid") Integer unpaid);



}
