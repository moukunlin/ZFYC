package cn.com.zfyc.service;

import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class ShopService  {

    @Autowired
    private ShopDao shopDAO;

    public Integer save(ShopEntity shop) {
        return shopDAO.save(shop);
    }

    public ShopEntity findShopByUserId(String user_id) {
        return shopDAO.findShopByUserId(user_id);
    }
}
