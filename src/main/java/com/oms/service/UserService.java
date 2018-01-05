package com.oms.service;

import com.oms.domain.SysUser;

import java.util.List;

public interface UserService {

    SysUser save(SysUser user);

    List<SysUser> getList();

}
