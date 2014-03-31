package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.dao.CompanyDao;
import com.excilys.computerDatabase.dao.ConnectionManager;
import com.excilys.computerDatabase.om.Company;

@Service
public class CompanyService {

	@Autowired
	private ConnectionManager cm;
	
	
	@Autowired
	private CompanyDao companyDao;
	
	private CompanyService() {

	}
	
	public List<Company> getListCompany(){


		List<Company>	companyList=null;
		Connection cn=null;
		try {
			cn =cm.getConnection();
			cn.setAutoCommit(false);

			companyList=companyDao.getListCompany();
			cn.commit();

		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}finally{

			cm.closeConnection();
			

		}
		return companyList;

	}


}
