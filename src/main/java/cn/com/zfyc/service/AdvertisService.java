package cn.com.zfyc.service;

import cn.com.zfyc.bean.AdvertisEntity;
import cn.com.zfyc.bean.NoticeEntity;
import cn.com.zfyc.dao.AdvertisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class AdvertisService {

    @Autowired
    private AdvertisDao advertisDao;

    public Integer save(AdvertisEntity advertis) {
        return advertisDao.save(advertis);
    }

    public Integer update(Integer id){
        return advertisDao.update(id);
    }

    public Integer delete(Integer id){
        return advertisDao.delete(id);
    }

    public AdvertisEntity getDisplay(){
        return advertisDao.getDisplay();
    }


}
