package com.excilys.computerDataBase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerDataBase.dao.CompanyDao;
import com.excilys.computerDataBase.om.Company;


@Service
public class CompanyService {

	
	@Autowired
	private CompanyDao companyDao;
	
	private CompanyService() {

	}
	
	public List<Company> getListCompany(){


		List<Company>	companyList=null;
		

			companyList=companyDao.getListCompany();
		

		
		return companyList;

	}


}
