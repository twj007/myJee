package com.config;

import com.component.RepeatScanInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    private static final String SUBFIX = ".html";

    @Bean("templates")
    Set<String> templates() throws IOException {
        Set<String> templates = new HashSet<>();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources( "classpath*:templates/**");
        Arrays.stream(resources).forEach(r ->
                {
                    if(r.isFile() && r.getFilename().indexOf(SUBFIX) != -1){
                        templates.add(r.getFilename());
                    }
                }
        );
        return templates;
    }


    /***
     * 乱码解决
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    /***
     * 静态资源配置
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("static/**")
                .addResourceLocations("classpath:/resources/static/")
                .addResourceLocations("classpath:/META-INF/resources/static/")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Autowired
    private RepeatScanInterceptor repeatScanInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatScanInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
