package me.bitsoul.noa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lxbang
 * @create 2020/2/9 5:34 下午
 */
@SpringBootApplication
@ServletComponentScan
public class NoAApplication {
    public static void main(String[] args) {
        SpringApplication.run(NoAApplication.class,args);
    }
}
