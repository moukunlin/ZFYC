package cn.com.zfyc.bean;

import lombok.Data;

/**
 * @author created by putc on 2019/3/12
 */
@Data
public class GoodsEntity {

    private Integer goodsId;
    private Integer shopId;
    private String goodsName;
    private Integer categoryId;
    private Double salePrice;
    private Double preferentialPrice;
    private Integer status;
    private String imgUrl;

}
