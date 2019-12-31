package com.jee.workflow.component;

import com.common.utils.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.activiti.runtime.api.identity.UserGroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/05
 * 用于覆盖activiti中的ActivityUserGroupManagerImpl,
 * 因为里面使用了spring security的方式去获取组。
 **/
@Component
@Primary
@Slf4j
public class NonSecurityGroupManager implements UserGroupManager {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sys.rest.login}")
    private String loginApi;

    @Override
    public List<String> getUserGroups(String s) {
        return null;
    }

    @Override
    public List<String> getUserRoles(String s) {
        List<String> roles = null;
        ResponseEntity<ResultBody> result = restTemplate.postForEntity(loginApi, s, ResultBody.class);
        if(result.getStatusCode() == HttpStatus.OK){
            ResultBody resultBody = result.getBody();
            if(resultBody.getCode() == HttpStatus.OK.value()){
                roles = (List<String>) resultBody.getBody();
            }else{
                log.warn("[nonSecurityGroupManager]: error in auth: {}", resultBody.getMsg());
            }
        }else{
            log.warn("[nonSecurityGroupManager]: error when request for auth: {}", result.getBody());
        }

        return roles;
    }
}
