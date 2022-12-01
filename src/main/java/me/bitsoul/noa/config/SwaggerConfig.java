package me.bitsoul.noa.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    /**
     * 创建api实例
     *
     * @return
     */
    @Bean
    public Docket createRestAoi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                //设置哪些接口暴露给Swagger展示
                .select()
                //扫描所有有注解的api，用这种方式更灵活，指定为ApiOperation.class
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //构建
                .build();
    }


    /**
     * 添加摘要信息
     *
     * @return
     */
    private ApiInfo apiInfo() {

        //用ApiInfoBuilder进行定制，可以设置不同的属性，比较方便
        return new ApiInfoBuilder()
                //设置标题
                .title("标题：springboot集成swagger测试")
                //描述
                .description("描述：用于测试集成swagger接口")
                //作者信息
                .contact(new Contact("zq", null, null))
                //版本
                .version("版本号：1.0")
                //构建
                .build();


    }

}
