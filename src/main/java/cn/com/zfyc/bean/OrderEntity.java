package cn.com.zfyc.bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderEntity {

    private String orderId;
    private Integer goodsId;
    private String goodsName;
    private Integer shopId;
    private Integer count; // 订单数量
    private Double orderAmount; // 订单金额
    private Double shopIncome; // 商家实际收入
    private String userId;
    private Integer status;
    private String note; // 订单备注
    private Timestamp createDate;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;


}
