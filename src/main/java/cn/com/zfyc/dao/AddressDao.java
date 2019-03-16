package cn.com.zfyc.dao;

import cn.com.zfyc.bean.AddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface AddressDao {
    Integer save(@Param("address") AddressEntity address);

    List<AddressEntity> listAllAddress(@Param("createUser") String createUser);

    Integer update(@Param("address") AddressEntity address);

    Integer delete(@Param("addressId") Integer addressId);
}
