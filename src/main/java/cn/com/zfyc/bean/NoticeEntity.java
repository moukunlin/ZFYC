package cn.com.zfyc.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author created by putc on 2019/3/16
 */
@Data
public class NoticeEntity {

    private Integer id;
    private String content;
    private String createUserId;
    private Date createDate;

}
