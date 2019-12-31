//package com.config;
//
//import com.modules.webservice.impl.TestServiceImpl;
//import org.apache.cxf.Bus;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.xml.ws.Endpoint;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/12/24
// **/
//@Configuration
//public class WebServiceConfig {
//
//    @Autowired
//    private Bus bus;
//
//    @Bean
//    Endpoint endpoint(){
//        Endpoint endPoint = new EndpointImpl(bus, new TestServiceImpl());
//        endPoint.publish("/test");
//        return endPoint;
//    }
//
//    @Bean
//    HttpClient httpClient(){
//        return HttpClients.createDefault();
//    }
//}
