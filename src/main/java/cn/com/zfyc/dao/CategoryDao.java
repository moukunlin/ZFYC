package cn.com.zfyc.dao;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.bean.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface CategoryDao {
    Integer save(@Param("category") CategoryEntity categoryEntity);

    List<CategoryEntity> listAllCategory();

    Integer delete(@Param("idList") List<Integer> idList);

}
