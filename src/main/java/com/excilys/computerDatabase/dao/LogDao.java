package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.om.Log;

@Component
public class LogDao {
	
	
	public LogDao(){}
	
	@Autowired
	private ConnectionManager cm;
	
	public void insereLog(Log log) throws SQLException, ClassNotFoundException{
		Connection cn=cm.getConnection();
		PreparedStatement stmt = null;
		if( log.getComputerId()!=null){
		stmt = cn.prepareStatement("INSERT into log(computer_id,computer_name,operation,date) VALUES(?,?,?,NOW());");
		stmt.setLong(1,log.getComputerId());
		stmt.setString(2,log.getComputerName());
		stmt.setString(3,log.getOperation());
		}
		else{
			stmt = cn.prepareStatement("INSERT into log(operation,date) VALUES(?,NOW());");
			stmt.setString(1,log.getOperation());
		}
		stmt.executeUpdate();

			if (stmt != null){

				stmt.close();
			}
		
	}

}
