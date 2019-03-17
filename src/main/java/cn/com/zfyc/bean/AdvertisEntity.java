package cn.com.zfyc.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author created by putc on 2019/3/16
 */
@Data
public class AdvertisEntity {

    private Integer id;
    private String adname;
    private Integer adType;
    private Integer shopId;
    private String shopName;
    private String cover;
    private Boolean display;

}
