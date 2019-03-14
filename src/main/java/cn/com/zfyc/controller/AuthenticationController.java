package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.bean.User;
import cn.com.zfyc.service.UserService;
import cn.com.zfyc.service.ShopService;
import cn.com.zfyc.utils.SendSmsUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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

    private Map<String,Object>  verifyCodeMap = new ConcurrentHashMap<>();

    private final static Log log = LogFactory.get();

    /**
     * @desc 商户注册接口（先注册商户号才能申请开店)
     * @param param
     * @return
     */
    @PostMapping("/shopRegister")
    @ResponseBody
    public RestfulRecord register(@RequestBody Map<String,Object> param){
        if (CollectionUtil.isNotEmpty(param)){
           if (StringUtils.isEmpty(param.get("phoneNum")) || StringUtils.isEmpty(param.get("verifyCode"))){
               return new RestfulRecord(500,"手机号或验证码不允许为空");
           }
           if (userService.checkPhoneNum(param.get("phoneNum").toString()) > 0 ){
               return new RestfulRecord(500,"该手机号已被注册,不能再次注册!");
            }
            RestfulRecord record = checkVerifyCode(param.get("phoneNum").toString(), (int) param.get("verifyCode"));
           if (record.getCode()!=200){
               return new RestfulRecord(500,"验证码错误,请重新输入或尝试重新获取");
           }
            User user = new User();
            String userId = UUID.randomUUID().toString().replaceAll("-","");
            user.setUser_id(userId);
            user.setUser_type(1); // 0 管理员 1 商家  2 普通用户
           if (!StringUtils.isEmpty(param.get("password"))){
                String password = DigestUtils.md5DigestAsHex(param.get("password").toString().getBytes());
                user.setPassword(password);
            }
            user.setPhoneNum(param.get("phoneNum").toString());
            String token = UUID.randomUUID().toString().replaceAll("-","")+System.currentTimeMillis();
            user.setToken(token);
            userService.insertUser(user);
            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("token",token);
            map.put("phoneNum",param.get("phoneNum").toString());
            return new RestfulRecord(200,"商家帐号注册成功",map);
        }else {
            return new RestfulRecord(500,"参数不允许为空!");
        }

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
     * @param phoneNum   手机号
     * @param password 密码
     * @return
     */
   @RequestMapping("/shopLoginByPassword")
   @ResponseBody
    public RestfulRecord login(@RequestParam String phoneNum,@RequestParam String password){
       int login = userService.login(phoneNum, DigestUtils.md5DigestAsHex(password.getBytes()));
       if (login !=1){
          return new RestfulRecord(500,"登录失败,请重试");
       }else {
           // 登录成功之后更新用户的Token并返回用户信息
           String token = UUID.randomUUID().toString().replaceAll("-","")+System.currentTimeMillis();
           userService.updateUserToken(token,phoneNum);
           User user = userService.findUserByPhoneNum(phoneNum);
           log.info("根据手机号查询到用户信息{}",user.toString());
           ShopEntity shopEntity = shopService.findShopByUserId(user.getUser_id());
           log.info("根据用户ID查询到关联商户{}",shopEntity);
           Map<String,Object> map = new HashMap<>();
           // 如果已经注册店铺就返回店铺信息
           if (shopEntity != null && !StringUtils.isEmpty(shopEntity.getShopId())){
               map.put("shop_id",shopEntity.getShopId());
               map.put("shopStatus",shopEntity.getStatus());
           }
           map.put("user_id",user.getUser_id());
           map.put("token",token);
          return new RestfulRecord(200,"登录成功",map);
       }
    }

    /**
     * @desc  通用短信登录方式
     * @param phoneNum 手机号
     * @param code 验证码
     */
    @RequestMapping("/loginByVerifyCode")
    @ResponseBody
    public RestfulRecord loginByPhoneVerifyCode(@RequestParam String phoneNum,@RequestParam int code){
       RestfulRecord record = checkVerifyCode(phoneNum,code);
       if (record.getCode()!=200){
           return new RestfulRecord(500,"登录失败,请重试");
       }
       log.info("用户{}登录成功,登录时间{}",phoneNum,new Date());
        String token = UUID.randomUUID().toString().replaceAll("-","")+System.currentTimeMillis();
        userService.updateUserToken(token,phoneNum);
        User user = userService.findUserByPhoneNum(phoneNum);
        Map<String,Object> map = new HashMap<>();
        if (user.getUser_type()==1){
            ShopEntity shopEntity = shopService.findShopByUserId(user.getUser_id());
            // 如果是商户号并且已经注册店铺就返回店铺信息
            if (shopEntity != null && !StringUtils.isEmpty(shopEntity.getShopId())){
                map.put("shop_id",shopEntity.getShopId());
                map.put("shopStatus",shopEntity.getStatus());
            }
        }
        map.put("user_id",user.getUser_id());
        map.put("token",token);
        map.put("phoneNum",phoneNum);
        return new RestfulRecord(200,"登录成功",map);
    }


    /**
     * @desc 获取短信验证码
     * @param phoneNum
     * @return
     */
    @RequestMapping("/getVerifyCode")
    @ResponseBody
    public RestfulRecord getVerifyCode(@RequestParam String phoneNum){
        Map<String, Object> map = SendSmsUtil.sendVerifyCode(phoneNum);
        String status  =  map.get("status").toString();
        if (status.equals("SUCCESS")){
            Map<String,Object> currentUserVerifyInfo = new HashMap<>();
            currentUserVerifyInfo.put("verifyCode",map.get("verifyCode"));
            currentUserVerifyInfo.put("date",System.currentTimeMillis());
            verifyCodeMap.put(phoneNum,currentUserVerifyInfo);
            return new RestfulRecord(200,"验证码发送成功","SUCCESS");
        }else {
            return new RestfulRecord(500,"验证码发送失败","FAIL");
        }
    }


    /**
     * @desc 短信验证码校验
     * @param phoneNum
     * @param code
     * @return
     */
    @RequestMapping("/checkVerifyCode")
    @ResponseBody
    public RestfulRecord checkVerifyCode(@RequestParam String phoneNum,@RequestParam int code ){
        Map<String,Object> currentUserVerifyInfo = (Map<String, Object>) verifyCodeMap.get(phoneNum);
            if (StringUtils.isEmpty(phoneNum) || StringUtils.isEmpty(code)){
                return new RestfulRecord(500,"请输入手机号或者验证码");
            }
            else if (CollectionUtil.isEmpty(currentUserVerifyInfo)){
                return new RestfulRecord(500,"请先获取验证码");
            }
            /*else if ((System.currentTimeMillis()-(long) currentUserVerifyInfo.get("date"))/1000/60 > 3){
                return new RestfulRecord(500,"验证码已失效,请重新获取");
            }*/
            else if (code == (int)currentUserVerifyInfo.get("verifyCode")){
                return new RestfulRecord(200,"验证成功");
            }
           else {
                return new RestfulRecord(500,"验证失败");
            }

    }

}
