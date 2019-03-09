package cn.com.zfyc.service.impl;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.dao.CategoryDAO;
import cn.com.zfyc.dao.FileDAO;
import cn.com.zfyc.dao.ShopDAO;
import cn.com.zfyc.service.CategoryService;
import cn.com.zfyc.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author created by putc on 2019/3/8
 */
@Service(CategoryService.CATEGORY_SERVICE_ID)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Integer save(CategoryEntity categoryEntity) {
        return categoryDAO.save(categoryEntity);
    }
}
