package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.FileUploadService;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {


    @Autowired
    private FileUploadService service;

    private final static Log log = LogFactory.get();


    /**
     * @desc 单文件上传接口
     * @param multipartFile 文件
     * @return
     */
    @PostMapping("/uploadSingleFile")
    @ResponseBody
    public RestfulRecord uploadFile(@RequestParam("file") MultipartFile multipartFile){
        log.info("调用单文件上传接口");
        if ( multipartFile == null || multipartFile.isEmpty() ) {
            return new RestfulRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_450);
        }
        return service.uploadFile(multipartFile);
    }


    @PostMapping("/uploadMoreFile")
    @ResponseBody
    public RestfulRecord uploadMoreFile(@RequestParam("file") MultipartFile [] multipartFiles){
        if (multipartFiles == null || multipartFiles.length==0){
            return new RestfulRecord(200,WebMessageConstants.SCE_PORTAL_MSG_450);
        }
        return service.uploadMoreFile(multipartFiles);
    }

    @RequestMapping("/getFileResourceById")
    @ResponseBody
    public RestfulRecord getFileResourceById(@RequestParam int id){
        return  service.getFileResourceById(id);
    }


}
