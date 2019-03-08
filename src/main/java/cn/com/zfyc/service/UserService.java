package cn.com.zfyc.service;

import cn.com.zfyc.bean.User;
import cn.com.zfyc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public int login(String param, String password) {
        return userDao.login(param,password);
    }

    public int updateUserToken(String token,String phoneNum){
        return userDao.updateUserToken(token,phoneNum);
    }

    public User findUserByPhoneNum(String phoneNum){
        return userDao.findUserByPhoneNum(phoneNum);
    }

    /**
     * @desc 用户token验证接口
     * @param token
     * @param user_id
     * @return
     */
    public boolean checkUserToken(String token,String user_id){
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(user_id)){
            return false;
        }
        User user = findUserByUserId(user_id);
        if (token.equals(user.getToken())){
            return true;
        }
           return false;
    }

    public User findUserByUserId(String user_id){
        return userDao.findUserByUserId(user_id);
    }
}
