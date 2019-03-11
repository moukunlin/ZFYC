package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.bean.User;
import cn.com.zfyc.service.UserService;
import cn.com.zfyc.service.ShopService;
import cn.hutool.core.collection.CollectionUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 统一认证登录接口
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    /**
     * @desc 商户注册接口（先注册商户号才能申请开店)
     * @param param
     * @return
     */
    @PostMapping("/shopRegister")
    @ResponseBody
    public RestfulRecord register(@RequestBody Map<String,Object> param){
        User user = new User();
        String userId = UUID.randomUUID().toString().replaceAll("-","");
        user.setUser_id(userId);
        user.setUser_type(1); // 0 管理员 1 商家  2 普通用户
        if (CollectionUtil.isNotEmpty(param)){
            if (!StringUtils.isEmpty(param.get("password"))){
                String password = MD5Encoder.encode(param.get("password").toString().getBytes());
                user.setPassword(password);
            }
            if (!StringUtils.isEmpty(param.get("gender"))){
                user.setGender(Integer.parseInt(param.get("gender")+""));
            }
            if (!StringUtils.isEmpty(param.get("phoneNum"))){
                user.setPhoneNum(param.get("phoneNum").toString());
            }
            user.setCreate_time(System.currentTimeMillis());
        }
         userService.insertUser(user);
         return new RestfulRecord(200,"商户号注册成功");
    }

    /**
     * @Deac 检查用户名是否可用
     * @param name
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public RestfulRecord  checkUserName(@RequestParam  String name){
        RestfulRecord record = new RestfulRecord();
        int result = userService.checkUserName(name);
        if (result > 0){
            record.setData("该昵称称已被占用");
        }else {
            record.setData("该昵称可用");
        }
        return record;
    }

    /**
     * @Desc 检查手机号是否已被注册
     */

    @RequestMapping("/checkPhoneNum")
    @ResponseBody
    public RestfulRecord checkPhoneNum(@RequestParam  String phoneNum){
        RestfulRecord record = new RestfulRecord();
        int count = userService.checkPhoneNum(phoneNum);
        if (count > 0){
            record.setData("该手机号已被注册");
        }else {
            record.setData("该手机号可用");
        }
        return record;
    }


    /**
     * @desc 商户登录接口 (手机号、密码登录方式）
     * @param param   手机号
     * @param password 密码
     * @return
     */
   @RequestMapping("/shopLoginByPassword")
   @ResponseBody
    public RestfulRecord login(@RequestParam String param,@RequestParam String password){
        RestfulRecord record = new RestfulRecord(200);
       int login = userService.login(param, password);
       if (login ==1){
           record.setMsg("登录失败");
       }else {
           record.setMsg("登录成功");
           // 登录成功之后更新用户的Token并返回用户信息
           String token = UUID.randomUUID().toString().replaceAll("-","")+System.currentTimeMillis();
           userService.updateUserToken(token,param);
           User user = userService.findUserByPhoneNum(param);
           ShopEntity shopEntity = shopService.findShopByUserId(user.getUser_id());
           Map<String,Object> map = new HashMap<>();
           // 如果已经注册店铺就返回店铺信息
           if (shopEntity != null && !StringUtils.isEmpty(shopEntity.getShopId())){
               map.put("shop_id",shopEntity.getShopId());
           }
           map.put("user_id",user.getUser_id());
           map.put("token",token);
           record.setData(map);
       }
       return record;
    }

    @RequestMapping("/getVerifyCode")
    @ResponseBody
    public RestfulRecord getVerifyCode(@RequestParam String phoneNum){

       return new RestfulRecord(200);
    }
}
