package cn.tohuangshuai;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * 继承SpringBootServletInitializer相当于使用web.xml的形式进行部署
 */
public class WarStartApplication extends SpringBootServletInitializer {

    /**
     * 重写配置configure
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //使用wen.xml运行应用程序，指向application,最后启动springboot
        return builder.sources(CMHApplication.class);
    }
}
