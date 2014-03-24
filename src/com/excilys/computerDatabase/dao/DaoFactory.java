package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.excilys.computerDatabase.om.Company;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.om.ComputerDto;
import com.excilys.computerDatabase.om.ComputerWrapper;
import com.excilys.computerDatabase.om.Log;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DaoFactory {

	
	private final static String url="jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
private final static String DriverClass ="com.mysql.jdbc.Driver";
private static BoneCP boneCP;
private static DaoFactory INSTANCE;
private static ThreadLocal<Connection> connectionThread = new ThreadLocal<Connection>();

private DaoFactory(){


}
public static DaoFactory getInstance() throws ClassNotFoundException, SQLException{	
	if(INSTANCE==null){
		Class.forName(DriverClass);
		// setup the connection pool
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl( url); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
		config.setUsername("root");
		config.setPassword("root");
		config.setMinConnectionsPerPartition(1);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(1);
		boneCP= new BoneCP(config);
		INSTANCE = new DaoFactory();
	}
	return INSTANCE;
}





public BoneCP getBoneCP() {
return boneCP;
}

public CompanyDao getCompanyDao() {
return CompanyDao.getInstance();
}

public ComputerDao getComputerDao() {
return ComputerDao.getInstance();
}

public LogDao getLogDao(){
	return LogDao.getInstance();
}


public Connection getConnection() throws SQLException, ClassNotFoundException{
// load the database driver (make sure this is in your classpath!)
// setup the connection pool
connectionThread.set(INSTANCE.getBoneCP().getConnection());
return connectionThread.get();

}
public List<Company> getListCompany(Connection cn) throws SQLException{
return getCompanyDao().getListCompany(cn);
}
public List<ComputerDto> getListComputer(ComputerWrapper computerWrapper,Connection cn) throws SQLException {
return getComputerDao().getListComputer(computerWrapper,cn);
}

public void insereComputer(ComputerDto computer,Connection cn) throws SQLException, ParseException {
getComputerDao().insereComputer(computer, cn);
}


public void deleteComputer(ComputerWrapper computerWrapper,Connection cn) throws SQLException{
getComputerDao().deleteComputer(computerWrapper, cn);	
}

public void editComputer(ComputerDto computer,Connection cn) throws SQLException, ParseException{
getComputerDao().editComputer(computer, cn);
}
public Long countComputer(ComputerWrapper computerWrapper,Connection cn) throws SQLException{
return getComputerDao().countComputer(computerWrapper, cn);
}

public void insereLog(Log log,Connection cn) throws SQLException{
	getLogDao().insereLog(log,cn);
}

}


