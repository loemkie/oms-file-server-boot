package com.oms.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oms.domain.SubmitData;
import com.oms.service.SubmitDataService;
import com.oms.service.exception.UserAlreadyExistsException;
import com.oms.util.DateUtils;

@RestController
public class SubmitDataRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitDataRestController.class);
    private final SubmitDataService submitDataService;

    @Inject
    public SubmitDataRestController(final SubmitDataService submitDataService) {
        this.submitDataService = submitDataService;
    }

    @RequestMapping(value = "/sd/save",method = RequestMethod.GET)
    public SubmitData save(SubmitData submitData, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Received request to create the {}", submitData);
        submitData.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        submitData.setUpdate_date(new Date());
        if(submitData.getSpec() == null){
        	submitData.setSpec("0");
        }
        return submitDataService.save(submitData);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        LOGGER.debug("Received request to list all users");
        List<SubmitData> submitDataList =  submitDataService.getListBySql();
        String str = "";
        for (SubmitData submitData : submitDataList) {
        	if(submitData.getSpec()==null){
        		submitData.setSpec("");
        	}
        	str=str+submitData.getMobile()+"|"+submitData.getSpec()+"|"+submitData.getOffice_id()+"|"+submitData.getCard_id()+"|"+DateUtils.formatDate(submitData.getUpdate_date())+"<br>";
		}
        return str;
//    	return new ArrayList<User>();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }

}
