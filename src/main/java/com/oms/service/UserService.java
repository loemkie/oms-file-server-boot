package com.oms.service;

import com.oms.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getList();

}
