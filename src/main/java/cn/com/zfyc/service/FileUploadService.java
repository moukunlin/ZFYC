package cn.com.zfyc.service;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.dao.FileDao;
import cn.com.zfyc.utils.FileUploadUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadService {

    @Value("${fileUpload.path}")
    private String fileSavePath; // 文件存储在服务器上的物理地址

    @Value("${fileUpload.mappingPath}")
    private String fileMappingPath; // 文件存储在服务器上的映射地址

    @Autowired
    private FileDao fileDao;

    private final static Log log = LogFactory.get();

    public RestfulRecord uploadFile(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            String originFileName = multipartFile.getOriginalFilename();
            String suffixName = FileUploadUtil.getFileSuffix( originFileName );
            if (suffixName.equalsIgnoreCase(".jpg") || suffixName.equalsIgnoreCase(".png")){
                String prefixName = FileUploadUtil.getFilePrefix( originFileName );
                String date = new SimpleDateFormat( "yyyyMMddHHmmss" ).format( new Date() );
                String saveFileName = prefixName + date + suffixName;
                boolean status = FileUploadUtil.uploadFile( bytes, saveFileName, fileSavePath );
                if (status){
                    log.info("文件上传成功，插入文件上传信息到数据库");
                    fileDao.saveFile(saveFileName,fileSavePath+ File.separator + saveFileName);
                    Map<String, Object> file = fileDao.getFileResourceByName(saveFileName);
                    file.put("url",fileMappingPath + File.separator + saveFileName);
                    return new RestfulRecord(200, WebMessageConstants.SCE_PORTAL_MSG_240,file);
                }else {
                    log.info("文件上传失败");
                    return new RestfulRecord(200,WebMessageConstants.SCE_PORTAL_MSG_451);
                }
            }else {
                log.info("文件后缀为:{}" + suffixName);
                return new RestfulRecord(200,WebMessageConstants.SCE_PORTAL_MSG_452);
            }
        } catch (IOException e) {
            log.error("文件上传发生异常，异常信息:{}",e);
            return  new RestfulRecord(500,WebMessageConstants.SCE_PORTAL_MSG_501,e);
        }
    }

    public RestfulRecord uploadMoreFile(MultipartFile[] multipartFiles) {
        Object[] result = new Object[multipartFiles.length];
        List<Map<String,Object>> files = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < multipartFiles.length; i++) {
            MultipartFile multipartFile = multipartFiles[i];
            try {
                byte[] bytes = multipartFile.getBytes();
                String originFileName = multipartFile.getOriginalFilename();
                String suffixName = FileUploadUtil.getFileSuffix(originFileName);
                if (suffixName.equalsIgnoreCase("jpg") || suffixName.equalsIgnoreCase("png")){
                    String saveFileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
                    boolean status = FileUploadUtil.uploadFile(bytes, saveFileName, fileSavePath);
                    if (status) {
                        flag = true;
                        log.info("文件上传成功，插入数据库");
                        fileDao.saveFile(saveFileName,fileSavePath+ File.separator + saveFileName);
                        Map<String, Object> file = fileDao.getFileResourceByName(saveFileName);
                        file.put("url",fileSavePath + File.separator + saveFileName);
                        files.add(file);
                        result[i] = "第" + i+1 + "个文件上传成功";
                    } else {
                        result[i] = "第" + i+1 + "个文件上传失败";
                    }
                }else {
                    result[i] = "第" + i+1 + "个文件上传失败,文件类型不被允许，仅支持上传jpg或者png格式的图片";
                }

            } catch (IOException e) {
                 result[i] =  "第" + i+1 + "个文件上传异常";
                log.error("第" + i+1 + "个文件上传发生异常，异常信息:{}",e);
            }
        }
        return new RestfulRecord(flag ? 200 : 500, result.toString(),files);
    }


    public RestfulRecord  getFileResourceById(int id){
        Map<String, Object> file = fileDao.getFileResourceById(id);
        if (CollectionUtil.isNotEmpty(file)){
            file.put("url",fileMappingPath + File.separator + file.get("file_name").toString());
            return new RestfulRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,file);
        }else {
            return new RestfulRecord(500,WebMessageConstants.SCE_PORTAL_MSG_420);
        }
    }

}

