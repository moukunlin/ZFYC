package cn.com.zfyc.date.dao;

import cn.com.zfyc.bean.User;
import cn.com.zfyc.date.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private UserMapper mapper;
    public int insertUser(User user) {
        return mapper.insertUser(user);
    }

    public int checkPhoneNum(String phoneNum) {
        return mapper.checkPhoneNum(phoneNum);
    }

    public int checkUserName(String name) {
        return mapper.checkUserName(name);
    }

    public int login(String param, String password, int userType) {
        return mapper.login(param,password,userType);
    }
}
