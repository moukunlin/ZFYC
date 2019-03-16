package cn.com.zfyc.controller;

import cn.com.zfyc.bean.AddressEntity;
import cn.com.zfyc.bean.NoticeEntity;
import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class NoticeController {


    @Autowired
    private NoticeService noticeService;

    @PostMapping("/notice/save")
    public RestfulRecord save(@RequestBody NoticeEntity noticeEntity){
        Integer saveResult = noticeService.save(noticeEntity);
        if(null == saveResult || saveResult <=0 ){
            return new RestfulRecord(500,"公告新增失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"公告新增成功", WebMessageConstants.SUCCESS);
    }

    @GetMapping("/notice/get/new")
    public RestfulRecord getNewNotice(){
        return new RestfulRecord(noticeService.getNewNotice());
    }

}

