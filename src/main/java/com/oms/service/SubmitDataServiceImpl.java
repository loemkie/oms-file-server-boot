package com.oms.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.oms.domain.SubmitData;
import com.oms.repository.SubmitDataRepository;

@Service
@Validated
public class SubmitDataServiceImpl implements SubmitDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitDataServiceImpl.class);
    private final SubmitDataRepository repository;
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Inject
    public SubmitDataServiceImpl(final SubmitDataRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public SubmitData save(SubmitData submitData) {
        LOGGER.debug("Creating {}", submitData);
        return repository.save(submitData);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubmitData> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SubmitData> getListBySql() {
        LOGGER.debug("Retrieving the list of all users");
        EntityManager em = emf.createEntityManager();
        List<SubmitData> submitDataList = (List<SubmitData>)em.createQuery("SELECT c FROM SubmitData c order by update_date desc").getResultList(); 
        return submitDataList;
    }
}
