package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.om.Company;

@Repository
public class CompanyDao {
	
	
	public CompanyDao()
	{}
	
	@Autowired
	private ConnectionManager cm;
	/** Point d'accès pour l'instance unique du singleton */
	
	public List<Company> getListCompany() throws SQLException, ClassNotFoundException {
		Connection cn=cm.getConnection();
		
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
		
		Connection cn=cm.getConnection();
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