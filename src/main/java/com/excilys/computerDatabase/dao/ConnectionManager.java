package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
@Component
public class ConnectionManager {

	public ConnectionManager() {

	}

	private final static String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private final static String DriverClass = "com.mysql.jdbc.Driver";
	
	@Autowired
	private static BoneCP boneCP;

	private static ThreadLocal<Connection> connectionThread = new ThreadLocal<Connection>();

	

	public Connection getConnection() throws SQLException,
			ClassNotFoundException {
		// load the database driver (make sure this is in your classpath!)
		// setup the connection pool
		if (boneCP == null) {
			Class.forName(DriverClass);
			// setup the connection pool
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url); // jdbc url specific to your database, eg
			// jdbc:mysql://127.0.0.1/yourdb
			config.setUsername("root");
			config.setPassword("root");
			config.setMinConnectionsPerPartition(1);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			boneCP = new BoneCP(config);
		}

		if (connectionThread.get() == null) {
			connectionThread.set(boneCP.getConnection());
		}

		return connectionThread.get();
	}

	public void closeConnection() {
		Connection cn = connectionThread.get();
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (connectionThread != null) {
			connectionThread.remove();
		}

	}

}