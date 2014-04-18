package com.excilys.computerDataBase.dao;




import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Log;


@Repository
public class LogDao {
	
	
	public LogDao(){}
	
	
	@Autowired
	private SessionFactory session;
	
	public void insereLog(Log log){
	
		
		session.getCurrentSession().persist(log);
		
	}

}
