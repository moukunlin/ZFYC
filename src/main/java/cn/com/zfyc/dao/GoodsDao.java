package cn.com.zfyc.dao;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.bean.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface GoodsDao {
    Integer save(@Param("goods") GoodsEntity goods);
    List<GoodsEntity> listAllGoods(@Param("shopId") Integer shopId);
    Integer update(@Param("goods") GoodsEntity goods);
}
