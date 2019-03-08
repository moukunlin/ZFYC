package cn.com.zfyc.dao;

import cn.com.zfyc.bean.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface ShopDAO {

    Integer save(@Param("shop") ShopEntity shop);
}
