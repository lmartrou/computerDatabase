package com.excilys.computerDataBase.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.om.QCompany;
import com.excilys.computerDataBase.om.QComputer;
import com.excilys.computerDataBase.wrapper.Wrapper;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class ComputerDao {


	public ComputerDao()
	{}

	@Autowired
	private SessionFactory session;

	/** Point d'accès pour l'instance unique du singleton */

	
	public List<Computer> getListComputer(Wrapper computerWrapper) {
		


		List<Computer> listComputer = new ArrayList<Computer>();


		HibernateQuery query=new HibernateQuery(session.getCurrentSession());
		QComputer computer = QComputer.computer;
		QCompany company =QCompany.company;
		
	query.from(computer).leftJoin(computer.company, company);
		
		if(computerWrapper.getFilterby()!=null && computerWrapper.getFilter() !=null && computerWrapper.getFilter() !=""){
		if (computerWrapper.getFilterby().equals("computer")){
			query.where(computer.name.contains(computerWrapper.getFilter()));
		}
			if(computerWrapper.getFilterby().equals("company")){
				
				query.where(company.name.contains(computerWrapper.getFilter()));
		}
		}	
		
		if(computerWrapper.getOrder()==null||computerWrapper.getOrder().equals("name") ||computerWrapper.getOrder().equals("")){
			query.orderBy(computer.name.asc());
		}
		else{
		if(computerWrapper.getOrder().equals("introduced")){
			query.orderBy(computer.introduced.desc());
		}
		if(computerWrapper.getOrder().equals("discontinued")){
			query.orderBy(computer.discontinued.desc());
		}
		if(computerWrapper.getOrder().equals("company")){
			query.orderBy(computer.company.name.asc());
		}
		}
	
			
			query.limit(computerWrapper.getNumberPerPage()).offset(Long.valueOf(computerWrapper.getOffset()));
		
        listComputer=query.list(computer);
		
				
				return listComputer;
	}




public void insereComputer(Computer computer){

	session.getCurrentSession().persist(computer);

}


public void deleteComputer(Long id) {


	Computer computer=getComputer(id);
	session.getCurrentSession().delete(computer);
}

public void editComputer(Computer computer){

	session.getCurrentSession().merge(computer);

}


public Computer getComputer(Long id){

	Computer computer = null;
	computer = (Computer)session.getCurrentSession().get(Computer.class,id);	
	return computer;

}


public Long countComputer(Wrapper computerWrapper){

	HibernateQuery query=new HibernateQuery(session.getCurrentSession());
	QComputer computer = QComputer.computer;
	QCompany company =QCompany.company;
	
query.from(computer).leftJoin(computer.company, company);
	
	if(computerWrapper.getFilterby()!=null && computerWrapper.getFilter() !=null && computerWrapper.getFilter() !=""){
	if (computerWrapper.getFilterby().equals("computer")){
		query.where(computer.name.contains(computerWrapper.getFilter()));
	}
		if(computerWrapper.getFilterby().equals("company")){
			
			query.where(company.name.contains(computerWrapper.getFilter()));
	}
	}	
	
	Long count =query.count();
	return count;
	
}




}


