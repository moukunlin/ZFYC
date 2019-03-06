package cn.com.zfyc.service;

import cn.com.zfyc.bean.User;
import cn.com.zfyc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    public int checkPhoneNum(String phoneNum) {
        return userDao.checkPhoneNum(phoneNum);
    }

    public int checkUserName(String name) {
        return userDao.checkUserName(name);
    }

    public int login(String param, String password, int userType) {
        return userDao.login(param,password,userType);
    }
}
