package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.bean.User;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.intercepter.CurrentUser;
import cn.com.zfyc.service.AdminService;
import cn.com.zfyc.service.FileUploadService;
import cn.com.zfyc.service.ShopService;
import cn.com.zfyc.service.UserService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private Log log = LogFactory.get();

    @Autowired
    private AdminService adminService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;


    /**
     * @desc 管理员登录接口
     * @param userName
     * @param passWord
     * @return
     */

    @RequestMapping("/login")
    @ResponseBody
    public RestfulRecord login(@RequestParam String userName,@RequestParam String passWord){
        User user ;
        try {
            user = userService.findUserByUserNameAndPassWord(userName, DigestUtils.md5DigestAsHex(passWord.getBytes("UTF-8")));
            if (user == null){
                return new RestfulRecord(500,"用户名或密码错误,登录失败");
            }
            else if (user.getUser_type() !=0 ){
                return new RestfulRecord(500,"抱歉,你没有权限登录管理员平台");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("userId",user.getUser_id());
                map.put("token",user.getToken());
                map.put("userType",0);
                return new RestfulRecord(200,"登录成功",map);
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e);
            return new RestfulRecord(500,"编码格式转码异常",e);
        }
    }

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
        List<Map<String,Object>> shopEntityList = shopService.findAllShop(start,end);
        List<Map<String,Object>> list = new ArrayList<>();
        for (Map<String,Object> shopEntity:shopEntityList){
            Map<String, Object> map = convertShopFileIdToUrl(shopEntity);
            list.add(map);
        }
        log.info("本次查询共查询到{}条数据",shopEntityList.size());
        return new RestfulRecord(200,list);
    }

    public Map<String,Object> convertShopFileIdToUrl(Map<String,Object> shopEntity){
        if (!StringUtils.isEmpty(shopEntity.get("shop_photo"))){
            List<String> shopPhoto = new ArrayList<>();
            String[] fileId = shopEntity.get("shop_photo").toString().split(",");
            for (int i=0;i<fileId.length;i++){
                RestfulRecord record = fileUploadService.getFileResourceById(Integer.parseInt(fileId[i]));
                if (record.getCode()==200) {
                    Map<String, Object> fileMap = (Map<String, Object>) record.getData();
                    shopPhoto.add(fileMap.get("url").toString());
                }
            }
            shopEntity.put("shop_photo",shopPhoto);
        }
        if (!StringUtils.isEmpty(shopEntity.get("in_store"))){
            List<String> inStore = new ArrayList<>();
            String[] fileId = shopEntity.get("in_store").toString().split(",");
            for (int i=0;i<fileId.length;i++){
                RestfulRecord record = fileUploadService.getFileResourceById(Integer.parseInt(fileId[i]));
                if (record.getCode() == 200) {
                    Map<String, Object> fileMap = (Map<String, Object>) record.getData();
                    inStore.add(fileMap.get("url").toString());
                }
            }
            shopEntity.put("in_store",inStore);
        }
        if (!StringUtils.isEmpty(shopEntity.get("license_photo"))){
            int fileId = (int) shopEntity.get("license_photo");
            RestfulRecord record = fileUploadService.getFileResourceById(fileId);
            if (record.getCode() == 200) {
                Map<String, Object> fileMap = (Map<String, Object>) record.getData();
                shopEntity.put("license_photo", fileMap.get("url").toString());
            }
        }
        if (!StringUtils.isEmpty(shopEntity.get("permit_photo"))){
            int fileId = (int) shopEntity.get("permit_photo");
            RestfulRecord record = fileUploadService.getFileResourceById(fileId);
            if (record.getCode() == 200) {
                Map<String, Object> fileMap = (Map<String, Object>) record.getData();
                shopEntity.put("permit_photo", fileMap.get("url").toString());
            }
        }
        if (!StringUtils.isEmpty(shopEntity.get("id_photo"))){
            int fileId = (int) shopEntity.get("id_photo");
            RestfulRecord record = fileUploadService.getFileResourceById(fileId);
            if (record.getCode() == 200) {
                Map<String, Object> fileMap = (Map<String, Object>) record.getData();
                shopEntity.put("id_photo", fileMap.get("url").toString());
            }
        }
        return shopEntity;
    }

}
