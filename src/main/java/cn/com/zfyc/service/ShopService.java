package cn.com.zfyc.service;

import cn.com.zfyc.bean.ShopEntity;

/**
 * @author created by putc on 2019/3/8
 */
public interface ShopService {
    String SHOP_SERVICE_ID = "cn.com.zfyc.service.impl.ShopServiceImpl";
    Integer save(ShopEntity shop);
}
