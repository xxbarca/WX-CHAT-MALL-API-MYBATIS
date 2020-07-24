package com.ly.imallbatis.api.v1;

import com.github.pagehelper.PageInfo;
import com.ly.imallbatis.bo.PageCounter;
import com.ly.imallbatis.core.LocalUser;
import com.ly.imallbatis.core.interceptors.ScopeLevel;
import com.ly.imallbatis.dto.OrderDTO;
import com.ly.imallbatis.logic.OrderChecker;
import com.ly.imallbatis.model.Order;
import com.ly.imallbatis.service.OrderService;
import com.ly.imallbatis.util.CommonUtil;
import com.ly.imallbatis.vo.OrderIdVO;
import com.ly.imallbatis.vo.OrderSimplifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    @ScopeLevel()
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO) {
        Long uid = LocalUser.getUser().getId();
        OrderChecker orderChecker = orderService.isOk(uid, orderDTO);
        Long orderId = orderService.placeOrder(uid, orderDTO, orderChecker);
        return new OrderIdVO(orderId);
    }

    /**
     * 查询未支付订单
     * */
    @ScopeLevel()
    @GetMapping("/status/unpaid")
    public PageInfo<OrderSimplifyVo> getUnpaid(@RequestParam(defaultValue = "0") Integer start,
                                               @RequestParam(defaultValue = "10") Integer count) {
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        PageInfo<OrderSimplifyVo> orderPageInfo = orderService.getUnPaid(pageCounter.getPage(), pageCounter.getCount());
        return orderPageInfo;
    }

    /**
     * 查询其他状态订单
     * */
    @ScopeLevel()
    @GetMapping("/by/status/{status}")
    public PageInfo<OrderSimplifyVo> getOrderByStatus(
                                @PathVariable int status,
                                @RequestParam(defaultValue = "0") Integer start,
                                @RequestParam(defaultValue = "10") Integer count) {
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        PageInfo<OrderSimplifyVo> orderPageInfo = orderService.getOrderByStatus(status, pageCounter.getPage(), pageCounter.getCount());
        return orderPageInfo;

    }

}
