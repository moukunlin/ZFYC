package cn.com.zfyc.service;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.bean.GoodsEntity;
import cn.com.zfyc.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Integer save(GoodsEntity goodsEntity) {
        return goodsDao.save(goodsEntity);
    }

    public List<GoodsEntity> listAllGoodsByShopId(Integer shopId){
        return goodsDao.listAllGoods(shopId);
    }

    public Integer updateInvalid(Integer goodsId) {
        GoodsEntity goods = new GoodsEntity();
        goods.setStatus(0);
        goods.setGoodsId(goodsId);
        return goodsDao.update(goods);
    }

    public Integer updateInfo(GoodsEntity goods) {
        return goodsDao.update(goods);
    }

}
