package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.excilys.computerDatabase.om.*;


public enum LogDao {
	INSTANCE;
	
	private LogDao(){}
	
	public static LogDao getInstance()
	{	return INSTANCE;
	}
	public void insereLog(Log log, Connection cn) throws SQLException{
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
