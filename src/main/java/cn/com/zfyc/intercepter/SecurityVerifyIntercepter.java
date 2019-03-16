package cn.com.zfyc.intercepter;

import cn.com.zfyc.bean.User;
import cn.com.zfyc.service.UserService;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class SecurityVerifyIntercepter implements HandlerInterceptor {

    private final static Log log = LogFactory.get();

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("starting security verify of user's token,request url:{}",request.getRequestURL());
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        log.info("请求方式:{}",method);
        if (method.equals("OPTIONS")|| url.contains("/auth") || url.contains("/admin/login") ){
            return true;
        }else {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                log.info("获取到header信息 ->>> key:{}, value:{}",key,value);
            }
            String userId = request.getHeader("userId");
            if (StringUtils.isEmpty(userId)){
                log.error("userId is not allowed null or empty");
                throw new Exception("this userId is not allowed null or empty ");
            }
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)){
                log.error("token is not allowed null or empty");
                throw new Exception("this token is not allowed null or empty");
            }
             if (userService.checkUserToken(token, userId)){
                 getCurrentUser(request);
                 return true;
             }
              throw new Exception("no access to this request url");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private User getCurrentUser(HttpServletRequest request){
        String userId = request.getHeader("userId");
        User user = userService.findUserByUserId(userId);
        request.setAttribute("currentUser",user);
        return user;
    }
}
