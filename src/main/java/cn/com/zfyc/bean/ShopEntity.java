package cn.com.zfyc.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author created by putc on 2019/3/7
 */
@Data
public class ShopEntity {
    private Integer shopId;
    private String shopName;
    private String userId;
    private String typeId;
    private String name;
    private String area;
    private String address;
    private String lng;
    private String lat;
    private int shopPhoto;
    private int inStore;
    private int licensePhoto;
    private int permitPhoto;
    private String idNum;
    private int IdPhoto;
    private String bankAddress;
    private String bankName;
    private String bankUserName;
    private String bankId;
    private String bankPhone;
    private Integer status;
    private Date createDate;
}