package cn.com.zfyc.controller;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.bean.User;
import cn.com.zfyc.service.UserService;
import cn.hutool.core.collection.CollectionUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * 统一认证登录接口
 */
@RestController
@RequestMapping("/register")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public RestfulRecord register(@RequestBody Map<String,Object> param){
        User user = new User();
        String userId = UUID.randomUUID().toString().replaceAll("-","");
        user.setUser_id(userId);
        if (CollectionUtil.isNotEmpty(param)){
           if (!StringUtils.isEmpty(param.get("userName"))){
               user.setUser_name(param.get("userName").toString());
           }
            if (!StringUtils.isEmpty(param.get("password"))){
                user.setPassword(param.get("password").toString());
            }
            if (!StringUtils.isEmpty(param.get("userType"))){
                user.setUser_type(Integer.parseInt(param.get("userType")+""));
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
        return new RestfulRecord(200,"注册成功");
    }

    /**
     * @Deac 检查用户名是否可用
     * @param name
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public RestfulRecord  checkUserName(String name){
        RestfulRecord record = new RestfulRecord();
        int result = userService.checkUserName(name);
        if (result > 0){
            record.setMsg("该昵称称已被占用");
            record.setCode(500);
        }else {
            record.setCode(200);
            record.setMsg("该昵称可用");
        }
        return record;
    }

    /**
     * @Desc 检查手机号是否已被注册
     */

    @RequestMapping("/checkPhoneNum")
    @ResponseBody
    public RestfulRecord checkPhoneNum(String phoneNum){
        RestfulRecord record = new RestfulRecord();
        int count = userService.checkPhoneNum(phoneNum);
        if (count > 0){
            record.setMsg("该手机号已被注册");
            record.setCode(500);
        }else {
            record.setMsg("该手机号可用");
            record.setCode(200);
        }
        return record;
    }


    /**
     * @desc 统一登录接口
     * @param param 用户昵称/手机号
     * @param password 密码
     * @param userType 用户类型 0 管理员 1 商户 2 普通用户
     * @return
     */
   @RequestMapping("/login")
   @ResponseBody
    public RestfulRecord login(String param,String password,int userType){
        RestfulRecord record = new RestfulRecord();
        userService.login(param,password,userType);


        return record;
    }
}
