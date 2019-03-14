package cn.com.zfyc.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SendSmsUtil {

    private final static Log log = LogFactory.get();

    private final static String SignName = "中富元创";

    private final static String TemplateCode = "SMS_159772661";

    private final static String AccessKeyId = "LTAI1UytqQh20MPH";

    private final static String AccessKeySecret = "OAgrGVRU7IXe2xCGZj75rQB4Gxmi5l";


    public static Map<String,Object> sendVerifyCode(String phoneNumbers){
        Map<String,Object> result = new HashMap<>();
        int code = (int) ((Math.random()*9+1)*100000);
        DefaultProfile profile = DefaultProfile.getProfile("default", AccessKeyId, AccessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.setConnectTimeout(5000);
        request.setReadTimeout(5000);
        request.putQueryParameter("SignName",SignName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("PhoneNumbers",phoneNumbers);
        request.putQueryParameter("TemplateParam","{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            Map<String, ?> map = JacksonMapper.INSTANCE.readJsonToObject(response.getData());
            if (!StringUtils.isEmpty(map.get("Code")) && map.get("Code").toString().equalsIgnoreCase("OK")){
                log.info("向用户{}发送验证码成功",phoneNumbers);
                result.put("status","SUCCESS");
                result.put("verifyCode",code);
            }else {
                log.info("向用户{}发送验证码失败,失败信息:{}",phoneNumbers,map.get("Message"));
                result.put("status","FAIL");

            }
        } catch (ServerException e) {
            log.error("验证码发送服务异常,异常信息:{}",e.getMessage());
            result.put("status","FAIL");
        } catch (ClientException e) {
            log.error("验证码发送服务异常,异常信息:{}",e.getMessage());
            result.put("status","FAIL");
        }

        return result;
    }
}

