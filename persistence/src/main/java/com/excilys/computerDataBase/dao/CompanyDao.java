package com.excilys.computerDataBase.dao;



import java.util.ArrayList;
import java.util.List;









import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Company;


@Repository
public class CompanyDao {
	
	
	public CompanyDao()
	{}
	
	
	/** Point d'accès pour l'instance unique du singleton */
	@Autowired
	private SessionFactory session;
	
	
	@SuppressWarnings("unchecked")
	public List<Company> getListCompany() {
		
		List<Company> listeCompany  = new ArrayList<Company>();
		 Query query = session.getCurrentSession().createQuery("FROM Company cpn");
			
		listeCompany=query.list();
			
			
		return listeCompany;
	}


	
}


