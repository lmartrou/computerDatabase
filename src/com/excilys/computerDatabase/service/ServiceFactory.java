package com.excilys.computerDatabase.service;


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

	
	
}
