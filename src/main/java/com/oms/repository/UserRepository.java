package com.oms.repository;

import com.oms.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SysUser, String> {
}
