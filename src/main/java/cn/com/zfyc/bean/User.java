package cn.com.zfyc.bean;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private String user_id;
    private String user_name;
    private String password;
    private int user_type;
    private int gender;
    private String phoneNum;
    private Timestamp create_date;
    private int Integral;
    private String token;

}
