package com.modules.webservice.client;

import com.common.model.vo.system.SysUser;
import com.jee.service.webservice.ISysUserService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/24
 **/
public class TestWebServiceClient {

    public static void main(String[] args) {
        String address = "http://localhost:8081/services/ISysUserService?wsdl";
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(address);
        jaxWsProxyFactoryBean.setServiceClass(ISysUserService.class);
        ISysUserService testService = (ISysUserService) jaxWsProxyFactoryBean.create();
        List<SysUser> result = testService.findUserByRole("manager");
        System.out.println(result);
    }
}
