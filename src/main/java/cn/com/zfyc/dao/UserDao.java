package cn.com.zfyc.dao;

import cn.com.zfyc.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserDao {

    int insertUser(User user);

    int checkPhoneNum(String phoneNum);

    int checkUserName(String name);

    int login(@Param("param") String param, @Param("password") String password);

    int updateUserToken(@Param("token") String token,@Param("phoneNum") String phoneNum);

    User findUserByPhoneNum(@Param("phoneNum")String phoneNum);

    User findUserByUserId(@Param("userId") String user_id);

    User findUserByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);
}
