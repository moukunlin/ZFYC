package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.AdminService;
import cn.com.zfyc.service.ShopService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private Log log = LogFactory.get();

    @Autowired
    private AdminService adminService;

    @Autowired
    private ShopService shopService;


    /**
     * @desc 商户入驻审核接口
     * @param shopId 商铺Id
     * @param pass 审核意见 true 通过 false 不通过
     */
    @RequestMapping("/auditShop")
    @ResponseBody
    public RestfulRecord auditShop(@RequestParam int shopId,@RequestParam boolean pass){
        ShopEntity shopEntity = shopService.findShopByShopId(shopId);
        if (shopEntity == null){
            return new RestfulRecord(500,"抱歉,没有查询到相应的店铺信息!");
        }
        int status ;
         if (pass){
             status = 1;
         }else {
             status = 3;
         }
         adminService.auditShop(shopId,status);
         return new RestfulRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }

    /**
     * @desc 根据条件查询商铺
     */

    @PostMapping("/findShopsByCondition")
    @ResponseBody
    public RestfulRecord findShopsByCondition(@RequestBody(required = false) Map<String,Object> param){
        String shopName = "";
        int status = 1; // 默认查询运营中的商户
        if (CollectionUtil.isNotEmpty(param)){
            log.info("管理员条件查询商户信息，查询条件：{}，查询时间：{}",param,new Date());
            if (!StringUtils.isEmpty(param.get("status"))){
                status = (int) param.get("status");
            }
            if (!StringUtils.isEmpty(param.get("shopName"))){
                shopName = param.get("shopName").toString();
            }
        }
        List<ShopEntity> shopEntities = shopService.findShopsByCondition(shopName,status);
        return new RestfulRecord(200,shopEntities);
    }

    @RequestMapping("/findAllShop")
    @ResponseBody
    public RestfulRecord findAllShop(@RequestParam int pageNum, @RequestParam int pageSize){
        int start = (pageNum-1) * pageSize;
        int end = pageNum * pageSize;
        List<ShopEntity> shopEntityList = shopService.findAllShop(start,end);
        log.info("本次查询共查询到{}条数据",shopEntityList.size());
        return new RestfulRecord(200,shopEntityList);
    }




}
