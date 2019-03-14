package cn.com.zfyc.service;

import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.dao.AdminDao;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final static Log log = LogFactory.get();

    @Autowired
    private AdminDao adminDao;

    public int auditShop(int shopId, int status) {
        return adminDao.auditShop(shopId,status);
    }

}
