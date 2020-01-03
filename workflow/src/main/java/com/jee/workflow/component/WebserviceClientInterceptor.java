package com.jee.workflow.component;

import com.common.constant.CommonConstants;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
public class WebserviceClientInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    public WebserviceClientInterceptor(){
        super(Phase.PREPARE_SEND);
    }


    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        List<Header> headers = soapMessage.getHeaders();
        Document doc = DOMUtils.createDocument();
        Element auth = doc.createElement("authrity");
        Element username = doc.createElement("username");
        Element password = doc.createElement("password");
        username.setTextContent(CommonConstants.WEBSERVICE_USERNAME);
        password.setTextContent(CommonConstants.WEBSERVICE_PASSWORD);
        auth.appendChild(username);
        auth.appendChild(password);
        headers.add(0, new Header(new QName("timamaes"), auth));

    }
}
