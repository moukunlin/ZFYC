package cn.com.zfyc.dao;

import cn.com.zfyc.bean.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {

    int insertUser(User user);


    int checkPhoneNum(String phoneNum);

    int checkUserName(String name);

   // @Select("SELECT COUNT(*) FROM user WHERE phone_num = #{param} AND password = #{password} OR user_name = #{param} AND")
    int login(String param, String password, int userType);
}
