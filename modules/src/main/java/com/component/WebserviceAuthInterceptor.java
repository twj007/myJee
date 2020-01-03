package com.component;

import com.common.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
@Slf4j
public class WebserviceAuthInterceptor  extends AbstractPhaseInterceptor<SoapMessage> {

    private SAAJInInterceptor saa = new SAAJInInterceptor();

    public WebserviceAuthInterceptor() {
        super(Phase.PRE_PROTOCOL);
        getAfter().add(SAAJInInterceptor.class.getName());
    }
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        SOAPMessage mess = message.getContent(SOAPMessage.class);
        if(mess==null){
            saa.handleMessage(message);
            mess=message.getContent(SOAPMessage.class);
        }
        SOAPHeader header =null;
        try {
            header = mess.getSOAPHeader();
        } catch (SOAPException e) {
            log.error("get SOAP header error:{}",e.getMessage(),e);
            e.printStackTrace();
        }
        if(header==null){
            throw new Fault(new IllegalAccessException("找不到Header,无法验证用户信息"));
        }
        NodeList username = header.getElementsByTagName("username");
        NodeList password = header.getElementsByTagName("password");
        if(username.getLength()<1){
            throw new Fault(new IllegalAccessException("找不到Header,无法验证用户信息"));
        }
        if(password.getLength()<1){
            throw new Fault(new IllegalAccessException("找不到Header,无法验证用户信息"));
        }
        String userName = username.item(0).getTextContent().trim();
        String passWord = password.item(0).getTextContent().trim();
        if(CommonConstants.WEBSERVICE_USERNAME.equals(userName)&&CommonConstants.WEBSERVICE_PASSWORD.equals(passWord)){
            log.debug("admin auth success");
        }else {
            SOAPException soapException = new SOAPException("认证错误");
            log.debug("admin auth failed");
            throw new Fault(soapException);
        }
    }
}