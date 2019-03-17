package cn.com.zfyc.intercepter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class FilterAutoConfiguration {


    @Configuration
    @ConditionalOnWebApplication
    @ConditionalOnClass(CurrentUser.class)
    protected static class ArgumentResolverAutoConfiguration implements WebMvcConfigurer {

        public ArgumentResolverAutoConfiguration() { }

        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolver){
            argumentResolver.add(new CurrentUserMethodArgumentResolver());
        }
    }


}
