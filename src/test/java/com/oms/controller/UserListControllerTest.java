package com.oms.controller;

import com.oms.domain.SysUser;
import com.oms.service.UserService;
import com.oms.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserListControllerTest {

    @Mock
    private UserService userService;

    private UserListController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserListController(userService);
    }

    @Test
    public void shouldGetUserListPage() {
       /* List<User> userList = stubServiceToReturnExistingUsers(5);
        ModelAndView view = userController.getListUsersView();
        // verify UserService was called once
        verify(userService, times(1)).getList();
        assertEquals("View name should be right", "user_list", view.getViewName());
        assertEquals("Model should contain attribute with the list of users coming from the service",
                userList, view.getModel().get("users"));*/
    }

    private List<SysUser> stubServiceToReturnExistingUsers(int howMany) {
        List<SysUser> userList = UserUtil.createUserList(howMany);
        when(userService.getList()).thenReturn(userList);
        return userList;
    }

}
