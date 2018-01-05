package com.oms.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.oms.domain.SysUser;
import com.oms.repository.UserRepository;
import com.oms.service.exception.UserAlreadyExistsException;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;

    @Inject
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public SysUser save(SysUser user) {
        LOGGER.debug("Creating {}", user);
        SysUser existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUser> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }

}
