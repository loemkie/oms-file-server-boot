package com.oms.service;

import com.oms.domain.SysUser;
import com.oms.repository.UserRepository;
import com.oms.service.exception.UserAlreadyExistsException;
import com.oms.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldSaveNewUser_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedUserShouldBeReturned() throws Exception {
        final SysUser savedUser = stubRepositoryToReturnUserOnSave();
        final SysUser user = UserUtil.createUser();
        final SysUser returnedUser = userService.save(user);
        // verify repository was called with user
        verify(userRepository, times(1)).save(user);
        assertEquals("Returned user should come from the repository", savedUser, returnedUser);
    }

    private SysUser stubRepositoryToReturnUserOnSave() {
    	SysUser user = UserUtil.createUser();
        when(userRepository.save(any(SysUser.class))).thenReturn(user);
        return user;
    }

    @Test
    public void shouldSaveNewUser_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingUser();
        try {
            userService.save(UserUtil.createUser());
            fail("Expected exception");
        } catch (UserAlreadyExistsException ignored) {
        }
        verify(userRepository, never()).save(any(SysUser.class));
    }

    private void stubRepositoryToReturnExistingUser() {
        final SysUser user = UserUtil.createUser();
        when(userRepository.findOne(user.getId())).thenReturn(user);
    }

    @Test
    public void shouldListAllUsers_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingUsers(10);
        Collection<SysUser> list = userService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(userRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingUsers(int howMany) {
        when(userRepository.findAll()).thenReturn(UserUtil.createUserList(howMany));
    }

    @Test
    public void shouldListAllUsers_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingUsers(0);
        Collection<SysUser> list = userService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

}
