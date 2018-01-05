package com.oms.util;

import com.oms.domain.SysUser;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    private static final String ID = "id";
    private static final String PASSWORD = "password";

    private UserUtil() {
    }

    public static SysUser createUser() {
        return new SysUser(ID, PASSWORD);
    }

    public static List<SysUser> createUserList(int howMany) {
        List<SysUser> userList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            userList.add(new SysUser(ID + "#" + i, PASSWORD));
        }
        return userList;
    }

}
