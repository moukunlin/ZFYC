package cn.com.zfyc.service;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class CategoryService  {

    @Autowired
    private CategoryDao categoryDAO;

    public Integer save(CategoryEntity categoryEntity) {
        return categoryDAO.save(categoryEntity);
    }
}
