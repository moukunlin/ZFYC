package cn.com.zfyc.controller;

import cn.com.zfyc.bean.GoodsEntity;
import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/goods/save")
    public RestfulRecord save(@RequestBody GoodsEntity goodsEntity){
        Integer result = goodsService.save(goodsEntity);
        if(null == result || result <= 0){
            return new RestfulRecord(500,"抱歉，商品上架失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"商品上架成功", WebMessageConstants.SUCCESS);
    }

    @GetMapping("/goods/listAll/{shopId}")
    public RestfulRecord listAllGoods(@PathVariable Integer shopId){
        return new RestfulRecord(goodsService.listAllGoodsByShopId(shopId));
    }

    @GetMapping("/goods/update/invalid/{goodsId}")
    public RestfulRecord updateInvalid(@PathVariable Integer goodsId) {
        Integer result = goodsService.updateInvalid(goodsId);
        if(null == result || result <= 0){
            return new RestfulRecord(500,"抱歉，商品下架失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"商品下架成功", WebMessageConstants.SUCCESS);
    }

    @PostMapping("/goods/update/info")
    public RestfulRecord updateGoods(@RequestBody GoodsEntity goods) {
        Integer result = goodsService.updateInfo(goods);
        if(null == result || result <= 0){
            return new RestfulRecord(500,"抱歉，商品修改信息失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"商品修改信息成功", WebMessageConstants.SUCCESS);
    }
}

