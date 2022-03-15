package cn.xxxx.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.xxxx.user.mapper")
@SpringBootApplication
public class User02Application {
    public static void main(String[] args) {
        SpringApplication.run(User02Application.class, args);
    }
}
