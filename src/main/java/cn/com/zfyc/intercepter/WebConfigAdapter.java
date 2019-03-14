package cn.com.zfyc.intercepter;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebConfigAdapter implements WebMvcConfigurer {

    @Autowired
    private SecurityVerifyIntercepter securityVerifyIntercepter;
    private final static Log log = LogFactory.get();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(securityVerifyIntercepter).addPathPatterns("/**");
    }
}
