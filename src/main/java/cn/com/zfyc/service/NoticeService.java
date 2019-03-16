package cn.com.zfyc.service;

import cn.com.zfyc.bean.NoticeEntity;
import cn.com.zfyc.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author created by putc on 2019/3/8
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    public Integer save(NoticeEntity noticeEntity) {
        return noticeDao.save(noticeEntity);
    }


    public NoticeEntity getNewNotice(){
        return noticeDao.getNewNotice();
    }


}
