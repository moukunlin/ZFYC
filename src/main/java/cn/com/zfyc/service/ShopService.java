package cn.com.zfyc.service;

import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public ShopEntity findShopByShopId(int shopId){
        return shopDAO.findShopByShopId(shopId);
    }

    public List<ShopEntity> findShopsByCondition(String shopName, int status) {
        return shopDAO.findShopsByCondition(shopName,status);
    }

    public List<Map<String,Object>> findAllShop(int start, int end) {
        return shopDAO.findAllShop(start,end);
    }

    public Map<String,Object> getShopType(int shopId) {
        return shopDAO.getShopType(shopId);
    }
}
