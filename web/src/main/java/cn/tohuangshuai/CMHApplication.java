package cn.tohuangshuai;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "cn.tohuangshuai")
@MapperScan(basePackages = "cn.tohuangshuai.dao.*")
@Component
public class CMHApplication {

    public static void main(String[] args) {
        SpringApplication.run(CMHApplication.class,args);
    }
}
