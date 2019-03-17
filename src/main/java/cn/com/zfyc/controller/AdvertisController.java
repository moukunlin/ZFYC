package cn.com.zfyc.controller;

import cn.com.zfyc.bean.AdvertisEntity;
import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.AdvertisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class AdvertisController {

    @Autowired
    private AdvertisService advertisService;

    @PostMapping("/advertis/save")
    public RestfulRecord save(@RequestBody AdvertisEntity advertis){
        Integer saveResult = advertisService.save(advertis);
        if(null == saveResult || saveResult <=0 ){
            return new RestfulRecord(500,"广告新增失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"广告新增成功", WebMessageConstants.SUCCESS);
    }

    @PostMapping("/advertis/update")
    public RestfulRecord update(@RequestParam Integer id){
        Integer result = advertisService.update(id);
        if(null == result || result <=0 ){
            return new RestfulRecord(500,"广告取消显示失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"广告取消显示成功", WebMessageConstants.SUCCESS);
    }

    @PostMapping("/advertis/delete")
    public RestfulRecord delete(@RequestParam Integer id){
        Integer result = advertisService.delete(id);
        if(null == result || result <=0 ){
            return new RestfulRecord(500,"广告删除失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"广告删除成功", WebMessageConstants.SUCCESS);
    }

    @GetMapping("/advertis/get/display")
    public RestfulRecord getNewNotice(){
        return new RestfulRecord(advertisService.getDisplay());
    }

}

