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
    private String typeId;
    private String ctyId;
    private String carefulId;
    private String name;
    private String address;
    private String degrees;
    private String shopPhoto;
    private String inStore;
    private String licensePhoto;
    private String permitPhoto;
    private String idNum;
    private String justIdPhoto;
    private String backIdPhoto;
    private String bankAddress;
    private String bankNnme;
    private String bankName;
    private String bankId;
    private String bankPhone;
    private Integer status;
    private Date createDate;
}