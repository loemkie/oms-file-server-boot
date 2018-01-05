package com.oms.service;

import com.oms.domain.SubmitData;

import java.util.List;

public interface SubmitDataService {

	SubmitData save(SubmitData submitData);

    List<SubmitData> getList();

	List<SubmitData> getListBySql();

}
