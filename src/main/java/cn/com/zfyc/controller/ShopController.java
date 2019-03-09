package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.service.ShopService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class ShopController {

    @Resource(name = ShopService.SHOP_SERVICE_ID)
    private ShopService shopService;

    @PostMapping("/shop/save")
    public RestfulRecord save(@RequestBody ShopEntity shop){
        return new RestfulRecord(shopService.save(shop));
    }
}

