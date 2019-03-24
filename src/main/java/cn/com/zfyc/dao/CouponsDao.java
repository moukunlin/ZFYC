package cn.com.zfyc.dao;

import cn.com.zfyc.bean.CouponsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface CouponsDao {
    Integer save(@Param("coupons") CouponsEntity coupons);

    List<CouponsEntity> select(@Param("coupons") CouponsEntity coupons);
}
