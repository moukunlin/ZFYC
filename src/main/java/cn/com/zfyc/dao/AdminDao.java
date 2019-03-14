package cn.com.zfyc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDao {

    int auditShop(@Param("shopId") int shopId, @Param("status") int status);

}


