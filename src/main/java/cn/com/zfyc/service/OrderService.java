package cn.com.zfyc.service;

import cn.com.zfyc.bean.OrderEntity;
import cn.com.zfyc.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author created by putc on 2019/3/18
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    // 查询店铺下面所有的订单
    public List<OrderEntity> findAllOrderByShopId(int shopId, int start, int end) {
        return orderDao.findAllOrderByShopId(shopId, start, end);
    }

    // 管理员查看所有订单
    public List<OrderEntity> findAllOrder(int start, int end) {
        return orderDao.findAllOrder(start, end);
    }

    public OrderEntity findOrderByOrderId(String orderId) {
        return orderDao.findOrderByOrderId(orderId);
    }

    public int insertOrder(OrderEntity orderEntity) {
        return orderDao.insertOrder(orderEntity);
    }

    public int updateOrderStatus(String orderId, int status) {
        return orderDao.updateOrderStatus(orderId, status);
    }

}
