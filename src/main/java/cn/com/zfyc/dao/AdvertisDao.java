package cn.com.zfyc.dao;

import cn.com.zfyc.bean.AdvertisEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface AdvertisDao {
    Integer save(@Param("advertis") AdvertisEntity advertis);

    Integer delete(@Param("id") Integer id);

    Integer update(@Param("id") Integer id);

    AdvertisEntity getDisplay();
 
}
