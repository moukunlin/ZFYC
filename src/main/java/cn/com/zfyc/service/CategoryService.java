package cn.com.zfyc.service;

import cn.com.zfyc.bean.CategoryEntity;
import cn.com.zfyc.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class CategoryService  {

    public static final Map<Integer, String> typeMap = new HashMap<>();
    static {
        typeMap.put(1, "商品");
        typeMap.put(2, "餐饮");
    }

    @Autowired
    private CategoryDao categoryDAO;

    public Integer save(CategoryEntity categoryEntity) {
        return categoryDAO.save(categoryEntity);
    }

    public List<Map<String, Object>> listAllCategory(){
        List<CategoryEntity> allCategory = categoryDAO.listAllCategory();
        Map<Integer, List<CategoryEntity>> typeGroup = allCategory.parallelStream().collect(Collectors.groupingBy(CategoryEntity::getTypeId));
        return typeGroup.keySet().stream().map(k -> {
            Map<String,Object> map = new HashMap<>();
            map.put("typeId", k);
            map.put("typeName", typeMap.get(k));
            map.put("typeList", typeGroup.get(k));
            return map;
        }).collect(Collectors.toList());

    }

    public Integer delete(List<Integer> idList) {
        return categoryDAO.delete(idList);
    }
}
