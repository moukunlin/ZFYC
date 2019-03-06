package cn.com.zfyc.date.mapper;

import cn.com.zfyc.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserMapper {

    int insertUser(User user);


    int checkPhoneNum(String phoneNum);

    int checkUserName(String name);

   // @Select("SELECT COUNT(*) FROM user WHERE phone_num = #{param} AND password = #{password} OR user_name = #{param} AND")
    int login(String param, String password, int userType);
}
