package com.excilys.computerDatabase.service;


import java.util.List;

import com.excilys.computerDatabase.om.Company;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.om.ComputerDto;
import com.excilys.computerDatabase.om.ComputerWrapper;



public enum ServiceFactory {
	INSTANCE;

	private ServiceFactory(){

	}

	public static ServiceFactory getInstance(){
		return INSTANCE;
	}

	public ComputerService getComputerService(){

		return ComputerService.getInstance();
	}

	public CompanyService getCompanyService(){

		return CompanyService.getInstance();
	}

	public List<Company> getListCompany(){
	
		return getCompanyService().getListCompany();
	}
	
	public List<Computer> getListComputer(ComputerWrapper computerWrapper){
		
		return getComputerService().getListComputer(computerWrapper);
	}
	public void insereComputer(ComputerDto computer){
		 getComputerService().insereComputer(computer);
	}

	public void deleteComputer(ComputerWrapper computerWrapper){
		getComputerService().deleteComputer(computerWrapper);
	}
	public Long countComputer(ComputerWrapper computerWrapper){
		return getComputerService().countComputer(computerWrapper);
	}
	
	public void editComputer(ComputerDto computer){
		getComputerService().editComputer(computer);
	}
	public ComputerWrapper pagination(ComputerWrapper computerWrapper){
		return getComputerService().pagination(computerWrapper);
	}
}
