package com.excilys.computerDatabase.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.dao.CompanyDao;

import com.excilys.computerDatabase.om.Company;

@Service
public class CompanyService {

	
	@Autowired
	private CompanyDao companyDao;
	
	private CompanyService() {

	}
	
	public List<Company> getListCompany(){


		List<Company>	companyList=null;
		
try{
			companyList=companyDao.getListCompany();
		

		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}catch(SQLException e) {
			
			}
		return companyList;

	}


}
