package com.excilys.computerDataBase.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Company;
import com.excilys.computerDataBase.om.QCompany;
import com.mysema.query.jpa.hibernate.HibernateQuery;



@Repository
public class CompanyDao {
	
	
	public CompanyDao()
	{}
	
	
	/** Point d'accès pour l'instance unique du singleton */
	@Autowired
	private SessionFactory session;
	

	public List<Company> getListCompany() {
		
		List<Company> listeCompany  = new ArrayList<Company>();
			HibernateQuery query=new HibernateQuery(session.getCurrentSession());
			QCompany company = QCompany.company;
			query.from(company);
		listeCompany=query.list(company);
			
			
		return listeCompany;
	}


	
}


