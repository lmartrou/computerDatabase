package com.excilys.computerDataBase.dao;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Log;


@Repository
public class LogDao {
	
	
	public LogDao(){}
	
	
	@Autowired
	private JdbcTemplate jt;
	
	public void insereLog(Log log){
	
		
		if( log.getComputerId()!=null){
			Object[] params = new Object[3];
			params[0]=log.getComputerId();
			params[1]=log.getComputerName();
			params[2]=log.getOperation();
		jt.update("INSERT into log(computer_id,computer_name,operation,date) VALUES(?,?,?,NOW());",params);
		
		}
		else{
			Object[] params = new Object[1];
			params[0]=log.getOperation();
			jt.update("INSERT into log(operation,date) VALUES(?,NOW());",params);
			
		}
		
		
	}

}
