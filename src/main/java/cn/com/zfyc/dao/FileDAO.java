package cn.com.zfyc.dao;

import cn.com.zfyc.bean.FileEntity;
import cn.com.zfyc.bean.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface FileDAO {
    Integer save(@Param("file") FileEntity file);
}
