package com.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oms.domain.SubmitData;

public interface SubmitDataRepository extends JpaRepository<SubmitData, String> {
}
