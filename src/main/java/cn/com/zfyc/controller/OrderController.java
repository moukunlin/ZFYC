package cn.com.zfyc.controller;

import cn.com.zfyc.bean.OrderEntity;
import cn.com.zfyc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author created by putc on 2019/3/18
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/findAllOrderByShopId")
    public List<OrderEntity> findAllOrderByShopId(@RequestParam int shopId,@RequestParam int start,@RequestParam int end) {
        return orderService.findAllOrderByShopId(shopId, start, end);
    }

    @GetMapping("/order/findAllOrder")
    public List<OrderEntity> findAllOrder(@RequestParam int start, @RequestParam int end) {
        return orderService.findAllOrder(start, end);
    }

    @GetMapping("/order/findOrderByOrderId")
    public OrderEntity findOrderByOrderId(@RequestParam String orderId) {
        return orderService.findOrderByOrderId(orderId);
    }

    @PostMapping("/order/insertOrder")
    public int insertOrder(@RequestBody OrderEntity orderEntity) {
        return orderService.insertOrder(orderEntity);
    }

    @PostMapping("/order/updateOrderStatus")
    public int updateOrderStatus(@RequestParam String orderId,@RequestParam int status) {
        return orderService.updateOrderStatus(orderId, status);
    }

}
