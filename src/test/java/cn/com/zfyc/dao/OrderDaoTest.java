package cn.com.zfyc.dao;

import cn.com.zfyc.bean.OrderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void  insertOrder(){
       /* OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId("123456789874562");
        orderEntity.setGoodsId(3);
        orderEntity.setGoodsName("测试订单商品");
        orderEntity.setCount(1);
        orderEntity.setUserId("0e1eaf7e45e847ebb4d3c2fc2df7a7d0");
        orderEntity.setNote("测试订单备注信息");
        orderEntity.setShopId(3);
        orderEntity.setOrderAmount(40.0);
        orderEntity.setShopIncome(35.78);
        orderEntity.setStatus(0);
        int i = orderDao.insertOrder(orderEntity);
        System.out.println("测试结果：" + i);*/

        OrderEntity orderByOrderId = orderDao.findOrderByOrderId("123456789874562");
        System.out.println(orderByOrderId);

    }
}
