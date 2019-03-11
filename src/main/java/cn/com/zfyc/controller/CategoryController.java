package cn.com.zfyc.controller;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/save")
    public RestfulRecord save(@RequestBody CategoryEntity categoryEntity){
        return new RestfulRecord(categoryService.save(categoryEntity));
    }
}

