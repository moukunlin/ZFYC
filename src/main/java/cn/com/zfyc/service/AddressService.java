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
        this.updateDefaultAddress(addressEntity);
        return addressDao.save(addressEntity);
    }

    private void updateDefaultAddress(AddressEntity addressEntity) {
        if (addressEntity.getChecked()) {
            AddressEntity address = new AddressEntity();
            address.setCreateUser(addressEntity.getCreateUser());
            address.setChecked(false);
            addressDao.update(address);
        }
    }

    public Integer update(AddressEntity addressEntity) {
        this.updateDefaultAddress(addressEntity);
        return addressDao.update(addressEntity);
    }

    public List<AddressEntity> listAllAddress(String createUser){
        return addressDao.listAllAddress(createUser);
    }


    public Integer delete(Integer addressId) {
        return addressDao.delete(addressId);
    }
}
