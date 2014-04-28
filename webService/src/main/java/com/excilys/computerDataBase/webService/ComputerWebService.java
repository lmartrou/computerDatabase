package com.excilys.computerDataBase.webService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.service.ComputerService;

@Component
@Path("/webServices")
public class ComputerWebService {

	@Autowired
	ComputerService computerService;

	@GET
	@Produces("application/xml")
	public List<Computer> findAll() {
		return computerService.findAll();
	}
}