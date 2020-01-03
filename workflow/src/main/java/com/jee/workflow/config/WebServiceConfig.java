package com.jee.workflow.config;


import com.jee.service.webservice.ISysUserService;
import com.jee.workflow.component.WebserviceClientInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
@Configuration
public class WebServiceConfig {

    @Bean
    HttpClient httpClient(){
        return HttpClients.createDefault();
    }


    @Value("${sys.webservice.sysUserService}")
    private String sysUserInterface;

    @Bean("sysUserService")
    ISysUserService sysUserService(){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(sysUserInterface);
        jaxWsProxyFactoryBean.setServiceClass(ISysUserService.class);
        jaxWsProxyFactoryBean.getOutInterceptors().add(new WebserviceClientInterceptor());
        ISysUserService sysUserService = (ISysUserService) jaxWsProxyFactoryBean.create();
        return sysUserService;
    }

}
