package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerDatabase.dao.DaoFactory;
import com.excilys.computerDatabase.om.Company;


public enum CompanyService {

	INSTANCE;


	private CompanyService() {

	}
	public static CompanyService getInstance(){

		return INSTANCE;
	}

	@SuppressWarnings("finally")
	public List<Company> getListCompany(){


		List<Company>	companyList=null;
		Connection cn=null;
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			companyList=DaoFactory.getInstance().getListCompany(cn);
			cn.commit();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cn.rollback();
		}finally{


			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return companyList;

		}

	}


}
