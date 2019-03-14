package cn.com.zfyc.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

@Mapper
public interface FileDao {


    int saveFile(@Param("fileName") String saveFileName, @Param("fileSavePath") String fileSavePath);

    Map<String,Object> getFileResourceById(@Param("id") int id);

    Map<String,Object> getFileResourceByName(@Param("name") String fileName);

}
