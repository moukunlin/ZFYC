package cn.com.zfyc.dao;

import cn.com.zfyc.bean.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface ShopDao {

    Integer save(@Param("shop") ShopEntity shop);

    ShopEntity findShopByUserId(@Param("userId") String user_id);

    ShopEntity findShopByShopId(@Param("shopId") int shopId);
    
    List<ShopEntity> findShopsByCondition(@Param("shopName") String shopName, @Param("status") int status);
}
