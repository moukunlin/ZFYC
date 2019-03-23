package cn.com.zfyc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommonDao {

    @Insert("INSERT INTO config(access_token,update_date) VALUES(#{token},NOW())")
    int insertAccessToken(@Param("token") String token);

    @Select("SELECT access_token FROM config ORDER BY update_date DESC limit 1")
    String findAccessToken();
}
