package com.jee.service.webservice;

import com.common.model.vo.system.SysUser;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "ISysUserService", targetNamespace = "http://webservice.modules.com")
public interface ISysUserService {

    @WebMethod
    @WebResult
    List<SysUser> findUserByRole(@WebParam(name = "taskId") String taskId);
}
