package cn.com.zfyc.dao;

import cn.com.zfyc.bean.GoodsEntity;
import cn.com.zfyc.bean.NoticeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author created by putc on 2019/3/7
 */
@Mapper
public interface NoticeDao {
    Integer save(@Param("notice") NoticeEntity notice);
    NoticeEntity getNewNotice();
}
