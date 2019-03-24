package cn.com.zfyc.controller;

import cn.com.zfyc.bean.CouponsEntity;
import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.CouPonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class CouponsController {

    @Autowired
    private CouPonsService couponsService;

    @PostMapping("/coupons/save")
    public RestfulRecord save(@RequestBody CouponsEntity couponsEntity){
        Integer saveResult = couponsService.save(couponsEntity);
        if(null == saveResult || saveResult <=0 ){
            return new RestfulRecord(500,"优惠卷新增失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"优惠卷新增成功", WebMessageConstants.SUCCESS);
    }

    @GetMapping("/coupons/get")
    public RestfulRecord getCoupons(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer shopId){
        return new RestfulRecord(couponsService.getCouponsByShopIdOrId(id, shopId));
    }

}

