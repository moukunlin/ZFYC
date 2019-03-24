package cn.com.zfyc.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author created by putc on 2019/3/24
 */
@Data
public class CouponsEntity {

    private Integer id;
    private Integer couponNum;
    private Integer shopId;
    private Integer effend;
    private Double full;
    private Double red;
    private Date endDate;
    private Date startDate;
}
