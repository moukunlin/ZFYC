package cn.com.zfyc;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("cn.com.zfyc.date.mapper")
public class ZfycApplication {

    private final static Log log = LogFactory.get();

    public static void main(String[] args) {
        SpringApplication.run(ZfycApplication.class, args);
    }

    /**
     * Rest 配置
     */
    @Bean
    public WebMvcConfigurer restConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings( CorsRegistry registry ) {
                log.info( "可接收任意http请求。" );
                registry.addMapping( "/**/*" ).allowedOrigins( "*" ).allowedMethods( "*" ).allowCredentials( true );
            }

            @Override
            public void addInterceptors( InterceptorRegistry registry ) {
//                registry.addInterceptor( new LoginInterceptor() ).addPathPatterns( "/**/*" ).addPathPatterns( "*" );
            }

        };
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder ) {
        return builder.build();
    }

}
