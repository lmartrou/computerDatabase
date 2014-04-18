package com.excilys.computerDataBase.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.wrapper.Wrapper;

@Repository
public class ComputerDao {


	public ComputerDao()
	{}

	@Autowired
	private SessionFactory session;

	/** Point d'accès pour l'instance unique du singleton */

	
	@SuppressWarnings("unchecked")
	public List<Computer> getListComputer(Wrapper computerWrapper) {
		System.out.println("coucou lea odsgo");


		List<Computer> listComputer = new ArrayList<Computer>();


		if(computerWrapper.getFilterby()=="" || computerWrapper.getFilterby() == null){
			computerWrapper.setFilterby("computer");
		}
		if(computerWrapper.getOrder()=="" || computerWrapper.getOrder() == null){
			computerWrapper.setOrder("computer.id");
		}

		StringBuilder sb=new StringBuilder();

		if (computerWrapper.getFilter()!="" && computerWrapper.getFilter()!=null){
			sb.append("SELECT computer FROM Computer AS computer LEFT OUTER JOIN computer.company AS company WHERE(").append(computerWrapper.getFilterby()).append(".name LIKE '%").append(computerWrapper.getFilter()).append("%') ORDER BY ").append(computerWrapper.getOrder());
			
		}
		else{
			sb.append("SELECT computer FROM Computer AS computer LEFT OUTER JOIN computer.company AS company ORDER BY ")
			.append(computerWrapper.getOrder());
			

		}
		System.out.println(sb.toString());
		Query query=session.getCurrentSession().createQuery(sb.toString()).setMaxResults(computerWrapper.getNumberPerPage().intValue()).setFirstResult(Integer.valueOf(computerWrapper.getOffset()));
				listComputer= query.list();
				System.out.println("coucou lea hikari sushi");
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
	System.out.println("coucou lea otoro");
	StringBuilder sb=new StringBuilder();
	if (computerWrapper.getFilter()!=""&& computerWrapper.getFilter()!=null){

		sb.append("SELECT count(*) FROM Computer AS cpt LEFT OUTER JOIN cpt.company AS cpn WHERE(");
		if(computerWrapper.getFilterby()=="company"){
			sb.append("cpn.name LIKE '%");
		}else{
			sb.append("cpt.name LIKE '%");
		}

		sb.append(computerWrapper.getFilter())
		.append("%')");

	}
	else{

		sb.append("SELECT count(*) FROM Computer cpt ");

	}

	Long count = (Long)(session.getCurrentSession().createQuery(sb.toString()).iterate().next());
	return count;
	
}




}


