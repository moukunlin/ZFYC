package cn.com.zfyc.schedule;

import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.service.CommonService;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.applet.Applet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/applet")
public class AppletSchedule {

    private final static Log log = LogFactory.get();

    private  static String url = "https://api.weixin.qq.com/cgi-bin/token";

    private final static String grant_type = "client_credential";

    private final static String appid = "wxa10bae0548d22fa0";

    private final static String secret = "16b9b36bd5481233b1b0c069c4f4a149";

    @Autowired
    private CommonService commonService;

    @Autowired
    private RestTemplate restTemplate ;

    @Scheduled(cron = "0 0 0/2 * * ?")
    @RequestMapping("/flushAccessToken")
    @ResponseBody
    public void flushAccessToken(){
        url = url+"?grant_type="+grant_type+"&appid="+appid+"&secret="+secret;
        log.info("开始刷新小程序的access_token,请求地址 {}",url);
        Map result = restTemplate.getForObject(url, Map.class);
        log.info("远程API返回结果:{}",result);
        if (!StringUtils.isEmpty(result.get("access_token"))){
            String token = result.get("access_token").toString();
            commonService.insertAccessToken(token);
            log.info("远程API返回结果已插入数据库，下一次刷新时间为2小时后");
            return;
        }
        log.error("调用微信API出现错误,等待下一次刷新");
    }

    @RequestMapping("/getAccessToken")
    @ResponseBody
    public RestfulRecord getAccessToken(){
        String accessToken = commonService.findAccessToken();
        if (StringUtils.isEmpty(accessToken)){
            return new RestfulRecord(500,"获取小程序access_token失败");
        }
        return new RestfulRecord(200,"获取小程序access_token成功",accessToken);
    }

    @RequestMapping("/getwxacode")
    @ResponseBody
    public RestfulRecord getWXAcode(@RequestParam String access_token, @RequestParam String path, @RequestParam int width){
        String url = "https://api.weixin.qq.com/wxa/getwxacode";
        Map<String,Object> param = new HashMap<>();
        param.put("access_token",access_token);
        param.put("path",path);
        param.put("width",width);
        Object object = restTemplate.postForObject(url, param, Object.class);
        log.info("服务调用成功,返回结果：{}",object);
        return new RestfulRecord(200,object);
    }

    @RequestMapping("/getSessionKeyAndAppId")
    @ResponseBody
    public RestfulRecord getSessionKeyAndAppId(@RequestParam String jsCode){
         StringBuilder url = new StringBuilder();
         url.append("https://api.weixin.qq.com/sns/jscode2session").append("?appid=").append(appid)
                 .append("&serect=").append(secret).append("&js_code=").append(jsCode).append("&grant_type")
                 .append(grant_type);
        Map result = restTemplate.getForObject(url.toString(), Map.class);
        if (Integer.parseInt(result.get("errcode").toString()) == 0){
            return new RestfulRecord(200,"调用服务成功",result);
        }
        return new RestfulRecord(500,"调用服务失败");
    }


    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("admin123".getBytes()));
    }
}
