package com.excilys.computerDataBase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDataBase.om.Company;
import com.excilys.computerDataBase.repository.CompanyRepository;


@Service
public class CompanyService {

	
	@Autowired
	private CompanyRepository companyDao;
	
	public CompanyService() {

	}
	@Transactional(readOnly = false)
	public List<Company> getListCompany(){


		List<Company>	companyList=null;
		

			companyList=companyDao.findAll();
		

		
		return companyList;

	}


}
