package cn.com.zfyc.bean;

import lombok.Data;

/**
 * @author created by putc on 2019/3/7
 */
@Data
public class ShopEntity {
    private String shopName;
    private String shopAddress;
    private String legalPerson;
    private String businessLicense;
    private String idCardNum;
    private String idCardPhoto;
    private String shopPhoto;
    private Integer category;
    private Integer status;
}