package cn.com.zfyc.service;

import cn.com.zfyc.bean.CouponsEntity;
import cn.com.zfyc.dao.CouponsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class CouPonsService {

    @Autowired
    private CouponsDao couponsDao;

    public Integer save(CouponsEntity couponsEntity) {
        return couponsDao.save(couponsEntity);
    }


    public List<CouponsEntity> getCouponsByShopIdOrId(Integer id, Integer shopId){
        CouponsEntity couponsEntity = new CouponsEntity();
        couponsEntity.setId(id);
        couponsEntity.setShopId(shopId);
        return couponsDao.select(couponsEntity);
    }


}
