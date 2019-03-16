package cn.com.zfyc.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author created by putc on 2019/3/12
 */
@Data
public class AddressEntity {
    private Integer id;
    private String consignee;
    private Integer gender;
    private String address;
    private String area;
    private String city;
    private String province;
    private String phoneNum;
    private Boolean checked;
    private Date createTime;
    private Date modifyTime;
    private String createUser;
}
