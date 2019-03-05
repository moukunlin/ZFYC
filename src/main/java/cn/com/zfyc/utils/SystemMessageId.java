package cn.com.zfyc.utils;

public interface SystemMessageId {
    String CMP0000 = "文件IO操作异常，详细信息";
    String CMP0001 = "数据库操作异常，详细信息：{}";
    String CMP0002 = "获取DataSource异常，详细信息：{}";
    String CMP0003 = "配置文件监听服务执行异常";
    String CMP0004 = "插入数据{}到数据中异常，详细信息：{}";
    String CMP0005 = "获取主机IP地址错误!";
    String CMP0006 = "获取操作系统硬件信息时出错，详细信息：{}";
    String CMP0007 = "对象{}转换为json数据异常!";
    String CMP0008 = "获取数据库连接异常，详细信息：{}";
    String CMP0009 = "获取主机名异常!";
    String CMP0010 = "线程{}正处于等待，睡眠，或者占用（阻塞）状态，而该线程被意外中断";
    String CMP0011 = "释放南向数据库连接异常，详细信息：{}";
    String CMP0012 = "Jackson处理数据{}时发生转换错误，详细信息：{}";
    String CMP0013 = "Jackson发生解析异常，详细信息";
    String CMP0014 = "Jackson发生映射异常，详细信息：{}";
    String CMP0015 = "数据 {} 不是Json格式，不做任何操作。";
    String CMP0016 = "添加数据{}到PreparedStatement失败，详细信息：{}";
    String CMP0017 = "没有读取到任务配置文件数据，请检查配置文件路径：{}";
    String CMP0018 = "文件URL：{}没有遵守RFC 2396，不能正确转换为URI。";
    String CMP0019 = "号码表备份操作中发生异常异常，详细信息：";
    String CMP0020 = "发送心跳信息到服务器{}失败，详细信息：";
    String CMP0021 = "MD5加密失败，详细信息：";
    String CMP0022 = "查询数据库表{}异常，详细信息：";
    String CMP0023 = "转换爬虫线程数错误，问题是数字格式不正确！详情：";
    String CMP0024 = "爬虫任务创建失败，详情：";

    //0300-0399 REST服务器端/客户端Warn/Error消息部分
    String CMP0300 = "异步获取服务器端数据异常，详细信息：{}";
    String CMP0301 = "服务器推送推送通道已经断开！";
    String CMP0302 = "向爬虫机{}推送待爬数据{}时出现异常，详细信息：{}";

    //0500-0600 配置文件读取相关warn/Error消息部分
    String CMP0500 = "{}的配置为空，使用默认配置：{}";
    String CMP0501 = "服务器地址设置不是一个合法IP，使用默认值：{}";
    String CMP0502 = "服务器端口设置不是一个合法数字，使用默认值：{}";
    String CMP0503 = "服务器HTTPS模式设置错误，使用默认值：{}";
    String CMP0504 = "Sigar依赖库配置了一个非法的路径{}，请检查配置！";
    String CMP0505 = "{}配置了一个非法的路径或者文件，请检查配置！";
    String CMP0506 = "Redis服务器端口设置{}不是一个合法数字，使用默认值：{}";
    String CMP0507 = "数据库批量处理大小{}不是一个合法数字，使用默认值：{}";
    String CMP0508 = "数据库配置文件的配置{}不是一个合法的路径或者文件，使用默认值：{}";
    String CMP0509 = "Redis鉴权认证开关{}设置错误，使用默认值：{}";
    String CMP0510 = "Redis服务器设置{}不是一个合法地址，使用默认值：{}";
    String CMP0511 = "保存日志到数据库开关{}设置错误，使用默认值：{}";
    String CMP0512 = "Zookeeper服务器端口设置{}不是一个合法数字，使用默认值：{}";
    String CMP0513 = "Zookeeper服务器设置{}不是一个合法地址，使用默认值：{}";
    String CMP0514 = "bootstrap.servers服务器设置{}不是一个合法地址，使用默认值：{}";
    String CMP0515 = "bootstrap.servers服务器端口设置{}不是一个合法数字，使用默认值：{}";
    String CMP0516 = "爬虫服务器设置{}不是一个合法地址，使用默认值：{}";
    String CMP0517 = "爬虫服务器端口设置{}不是一个合法数字，使用默认值：{}";
    String CMP0518 = "反欺诈手机号码文件监控路径{}配置错误，使用默认值：{}";
    String CMP0519 = "机器硬件信息监控第三方库{}不是一个目录，使用默认值：{}";

    //0700-0750 硬件信息相关消息部分
    String CMP0700 = "获取机器硬件信息异常，详细信息：{}";
    String CMP0701 = "获取内存信息异常，详细信息：{}";
    String CMP0702 = "获取硬盘信息异常，详细信息：{}";
    String CMP0703 = "获取网络信息异常，详细信息：{}";
    String CMP0704 = "获取CPU信息异常，详细信息：{}";
    String CMP0705 = "线程暂停异常，详细信息：{}";
    String CMP0706 = "sar获取机器信息格式有误,详细信息：{}";

    //0800-0850 任务相关消息部分
    String CMP0800 = "创建Scheduler对象异常，详细信息：{}";
    String CMP0801 = "获取触发器异常，详细信息：{}";
    String CMP0802 = "获取触发器状态异常，详细信息：{}";
    String CMP0803 = "添加任务异常，详细信息：{}";
    String CMP0804 = "执行任务异常，详细信息：{}";
    String CMP0805 = "暂停任务异常，详细信息：{}";
    String CMP0806 = "恢复任务异常，详细信息：{}";
    String CMP0807 = "删除任务异常，详细信息：{}";
    String CMP0808 = "获取任务列表异常，详细信息：{}";
    String CMP0809 = "修改触发器异常，详细信息：{}";
    String CMP0810 = "Scheduler启动异常,详细信息：{}";
    //0851-0900 Kafka相关警告信息
    String CMP0851 = "没有在数据库中查询到对应的Topic：{}";

    //0901-09200 验证码图像识别相关信息
    String CMP0901 = "获取图片流异常，详细信息：{}";
    String CMP0902 = "乱序滑动验证码排序异常，详细信息：{}";
    String CMP0903 = "创建屏幕全屏截图异常，详细信息：{}";
    String CMP0904 = "创建屏幕元素截图异常，详细信息：{}";
    String CMP0905 = "超级鹰查询积分异常，详细信息：{}";
    String CMP0906 = "超级鹰充值异常，详细信息：{}";
    String CMP0907 = "超级鹰报错返分异常，详细信息：{}";
    String CMP0908 = "超级鹰识别验证码异常，详细信息：{}";
    String CMP0909 = "滑动验证码识别异常，查询的公司名为：{}";


    //0950-0970 FTP相关信息
    String CMP0951 = "FTP服务器[{}:{}]连接异常，详细信息：{}";
    String CMP0952 = "FTP服务器上传文件[{}]异常，详细信息{}";
    String CMP0953 = "FTP服务器下载文件[{}]异常，详细信息{}";

}
