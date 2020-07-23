package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.core.LocalUser;
import com.ly.imallbatis.core.interceptors.ScopeLevel;
import com.ly.imallbatis.dto.OrderDTO;
import com.ly.imallbatis.service.OrderService;
import com.ly.imallbatis.vo.OrderIdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    @ScopeLevel()
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO) {
        Long uid = LocalUser.getUser().getId();
        orderService.isOk(uid, orderDTO);
        return null;
    }

}
