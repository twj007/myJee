//package com.modules.webservice.client;
//
//import com.common.model.vo.system.SysUser;
//import com.modules.webservice.ITestService;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/12/24
// **/
//public class TestWebServiceClient {
//
//    public static void main(String[] args) {
//        String address = "http://localhost:8081/services/test?wsdl";
//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//        jaxWsProxyFactoryBean.setAddress(address);
//        jaxWsProxyFactoryBean.setServiceClass(ITestService.class);
//        ITestService testService = (ITestService) jaxWsProxyFactoryBean.create();
//        SysUser result = testService.test("success");
//        System.out.println(result);
//    }
//}
