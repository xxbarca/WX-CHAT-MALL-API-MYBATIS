package com.ly.imallbatis.dao;

import com.github.pagehelper.PageInfo;
import com.ly.imallbatis.model.Order;
import com.ly.imallbatis.vo.OrderSimplifyVo;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int save(Order order);

    List<Order> getUnPaid(@Param("uid") Long uid, @Param("unpaid") Integer unpaid);

    List<Order> getOrderByStatus(@Param("status") int status, @Param("uid") Long uid);

    List<Order> getOrderByUserId(@Param("uid") Long uid);



}
