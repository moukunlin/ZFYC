package cn.com.zfyc.controller;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/save")
    public RestfulRecord save(@RequestBody CategoryEntity categoryEntity){
        Integer saveResult = categoryService.save(categoryEntity);
        if(null == saveResult || saveResult <=0 ){
            return new RestfulRecord(500,"分类新增失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"分类新增成功", WebMessageConstants.SUCCESS);
    }

    @GetMapping("/category/listAll")
    public RestfulRecord listAllCategory(){
        return new RestfulRecord(categoryService.listAllCategory());
    }

    @PostMapping("/category/delete")
    public RestfulRecord delete(@RequestParam String ids){
        if(StringUtils.isEmpty(ids)){
            return new RestfulRecord(500,"未选中分类不能删除", WebMessageConstants.FAIL);
        }
        List<Integer> idList = Arrays.asList(ids.split(",")).stream().map(Integer::valueOf).collect(Collectors.toList());
        Integer result = categoryService.delete(idList);
        if(null == result || result <= 0){
            return new RestfulRecord(500,"分类信息删除失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"分类信息删除成功", WebMessageConstants.SUCCESS);
    }
}

