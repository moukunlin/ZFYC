package cn.com.zfyc.service;

import cn.com.zfyc.bean.CategoryEntity;

/**
 * @author created by putc on 2019/3/9
 */
public interface CategoryService {
    String  CATEGORY_SERVICE_ID = "cn.com.zfyc.service.impl.CategoryServiceImpl";
    Integer save(CategoryEntity categoryEntity);

}
