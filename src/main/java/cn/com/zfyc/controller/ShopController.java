package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.ShopService;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

   @Autowired
    private ShopService shopService;

   private final static Log log = LogFactory.get();

    /**
     * @desc 商户入驻申请
     * @param shop
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public RestfulRecord save(@RequestBody ShopEntity shop){
        log.info("商户入驻申请，商户信息为：{}",shop);
        Integer save = shopService.save(shop);
        if (save==1){
            return new RestfulRecord(200,"申请成功，等待管理员审核", WebMessageConstants.SUCCESS);
        }else {
            return new RestfulRecord(200,"抱歉，申请失败,请检查你的资料是否有误",WebMessageConstants.FAIL);
        }

    }
}

