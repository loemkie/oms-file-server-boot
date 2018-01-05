package com.oms.controller;

import com.oms.domain.SysUser;
import com.oms.service.UserService;
import com.oms.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    @Mock
    private UserService userService;

    private UserRestController userRestController;

    @Before
    public void setUp() throws Exception {
        userRestController = new UserRestController(userService);
    }

    @Test
    public void shouldCreateUser() {
        final SysUser savedUser = stubServiceToReturnStoredUser();
        final SysUser user = UserUtil.createUser();
        SysUser returnedUser = userRestController.createUser(user);
        // verify user was passed to UserService
        verify(userService, times(1)).save(user);
        assertEquals("Returned user should come from the service", savedUser, returnedUser);
    }

    private SysUser stubServiceToReturnStoredUser() {
        final SysUser user = UserUtil.createUser();
        when(userService.save(any(SysUser.class))).thenReturn(user);
        return user;
    }

    @Test
    public void shouldListAllUsers() {
        stubServiceToReturnExistingUsers(10);
        Collection<SysUser> users = userRestController.listUsers();
        assertNotNull(users);
        assertEquals(10, users.size());
        // verify user was passed to UserService
        verify(userService, times(1)).getList();
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userService.getList()).thenReturn(UserUtil.createUserList(howMany));
    }

}
