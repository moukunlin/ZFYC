package cn.com.zfyc.service;

import cn.com.zfyc.bean.AddressEntity;
import cn.com.zfyc.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    public Integer save(AddressEntity addressEntity) {
        return addressDao.save(addressEntity);
    }

    public Integer update(AddressEntity addressEntity) {
        return addressDao.update(addressEntity);
    }

    public List<AddressEntity> listAllAddress(String createUser){
        return addressDao.listAllAddress(createUser);
    }


    public Integer delete(Integer addressId) {
        return addressDao.delete(addressId);
    }
}
