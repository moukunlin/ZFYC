package cn.com.zfyc.service.impl;

import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.dao.ShopDAO;
import cn.com.zfyc.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author created by putc on 2019/3/8
 */
@Service(ShopService.SHOP_SERVICE_ID)
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDAO shopDAO;

    @Override
    public Integer save(ShopEntity shop) {
        return shopDAO.save(shop);
    }
}
