package cn.com.zfyc.bean;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author created by putc on 2019/3/7
 */
@Data
public class ShopEntity {
    private int shopId;
    private String shopName;
    private String name;
    private String userId;
    private int typeId;
    private String area;
    private String address;
    private String lng;
    private String lat;
    private String shopPhoto; // 门店图片
    private String inStore;  // 店内环境
    private int licensePhoto;
    private int permitPhoto;
    private String idNum;
    private int IdPhoto;
    private String bankAddress;
    private String bankName;
    private String bankUserName;
    private String bankId;
    private String bankPhone;
    private int status;
    private Timestamp createDate;
}