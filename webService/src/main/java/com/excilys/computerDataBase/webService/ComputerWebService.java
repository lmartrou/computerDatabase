package com.excilys.computerDataBase.webService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.service.ComputerService;

@WebService
public class ComputerWebService {

	@Autowired
	ComputerService computerService;

	@WebMethod
	public List<Computer> findAll() {
		return computerService.findAll();
	}
}