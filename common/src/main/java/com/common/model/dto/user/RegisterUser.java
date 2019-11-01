package com.common.model.dto.user;

import com.common.model.vo.system.SysUser;
import lombok.Getter;
import lombok.Setter;

/***
 **@project: cli
 **@description:
 **@Author: twj
 **@Date: 2019/08/28
 **/
@Getter
@Setter
public class RegisterUser extends SysUser {

    public RegisterUser() {
    }

    public RegisterUser(String username, String password) {
        super(username, password);
    }


}
