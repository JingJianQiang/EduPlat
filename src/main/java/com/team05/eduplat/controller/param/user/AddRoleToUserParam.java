package com.team05.eduplat.controller.param.user;

import lombok.Data;

/**
 * @program: eduplat
 * @description: TODO
 * @author: chen wenliang
 * @Date 2019/12/4
 **/
@Data
public class AddRoleToUserParam {
    private long userId;
    private long roleId;
}
