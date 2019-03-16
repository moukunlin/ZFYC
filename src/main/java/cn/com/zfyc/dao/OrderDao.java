package cn.com.zfyc.dao;

import cn.com.zfyc.bean.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    // 查询店铺下面所有的订单
    List<OrderEntity> findAllOrderByShopId(@Param("shopId") int shopId, @Param("start") int start, @Param("end") int end);

    // 管理员查看所有订单
    List<OrderEntity> findAllOrder(@Param("start") int start, @Param("end") int end);

    OrderEntity findOrderByOrderId(@Param("orderId") String orderId);

    int insertOrder(OrderEntity orderEntity);

    int updateOrderStatus(@Param("orderId") String orderId,@Param("status") int status);


}
