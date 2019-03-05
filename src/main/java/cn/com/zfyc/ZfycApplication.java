package cn.com.zfyc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.zfyc.date.mapper")
public class ZfycApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZfycApplication.class, args);
    }

}
