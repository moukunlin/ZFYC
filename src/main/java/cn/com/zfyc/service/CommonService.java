package cn.com.zfyc.service;

import cn.com.zfyc.dao.CommonDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    private CommonDao commonDao;

    public int insertAccessToken(String token){

        return commonDao.insertAccessToken(token);
    }

    public String findAccessToken(){
        return commonDao.findAccessToken();
    }

}
