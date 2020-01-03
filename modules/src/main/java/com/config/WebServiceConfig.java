package com.config;

import com.component.WebserviceAuthInterceptor;
import com.jee.service.webservice.ISysUserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/24
 **/
@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private ISysUserService userService;


    @Bean
    Endpoint endpoint(){
        Endpoint endPoint = new EndpointImpl(bus, userService);
        ((EndpointImpl) endPoint).getInInterceptors().add(new WebserviceAuthInterceptor());
        endPoint.publish("/ISysUserService");
        return endPoint;
    }

    @Bean
    HttpClient httpClient(){
        return HttpClients.createDefault();
    }
}
