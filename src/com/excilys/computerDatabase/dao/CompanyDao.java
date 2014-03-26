package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.om.Company;

public enum CompanyDao {
	INSTANCE ;
	
	private CompanyDao()
	{}
	 
	/** Point d'accès pour l'instance unique du singleton */
	public static CompanyDao getInstance()
	{
		return INSTANCE;
	}

	
	
	public List<Company> getListCompany() throws SQLException, ClassNotFoundException {
		Connection cn=DaoFactory.getInstance().getConnection();
		
		ArrayList<Company> listeCompany  = new ArrayList<Company>();
		ResultSet rs = null ;
		Statement stmt = null;

			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT id, name FROM company");
			
			while (rs.next()) {
				Company c =  new Company();
				c.setId(rs.getLong(1));
				c.setName(rs.getString(2));
				
				listeCompany.add(c);
			}
				if (rs != null){

					rs.close();
				}

				if (stmt != null){

					stmt.close();
				}
			
		return listeCompany;
	}


	public void insereCompany(Company company) throws SQLException, ClassNotFoundException {
		
		Connection cn=DaoFactory.getInstance().getConnection();
		PreparedStatement stmt = null;

			stmt = cn.prepareStatement("INSERT into Company(id, name) VALUES(?,?,?,?,?);");

			stmt.setLong(1,company.getId());
			stmt.setString(2,company.getName());
			stmt.executeUpdate();

				if (stmt != null){

					stmt.close();
				}
	}
}
