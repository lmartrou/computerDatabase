package com.excilys.computerDataBase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Company;
import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.wrapper.Wrapper;
import com.jolbox.bonecp.BoneCPDataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
@Repository
public class ComputerDao {
	
@Autowired
	private BoneCPDataSource ds;

	public ComputerDao()
	{}



	/** Point d'accès pour l'instance unique du singleton */



	public List<Computer> getListComputer(Wrapper computerWrapper) throws SQLException, ClassNotFoundException {
		 

		   Connection cn = DataSourceUtils.getConnection(ds);
		ArrayList<Computer> listComputer  = new ArrayList<Computer>();
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		if(computerWrapper.getFilterby()=="" || computerWrapper.getFilterby() == null){
			computerWrapper.setFilterby("computer");
		}
		if(computerWrapper.getOrder()=="" || computerWrapper.getOrder() == null){
			computerWrapper.setOrder("computer.id");
		}
		StringBuilder sb=new StringBuilder();

		if (computerWrapper.getFilter()!="" && computerWrapper.getFilter()!=null){
			sb.append("SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name FROM computer LEFT OUTER JOIN company ON computer.company_id=company.id WHERE(").append(computerWrapper.getFilterby()).append(".name LIKE '%").append(computerWrapper.getFilter()).append("%') ORDER BY ").append(computerWrapper.getOrder())
			.append(" LIMIT ").append(computerWrapper.getOffset())
			.append(",").append(computerWrapper.getNumberPerPage());

		}
		else{
			sb.append("SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name FROM computer LEFT OUTER JOIN company ON computer.company_id=company.id ORDER BY ")
			.append(computerWrapper.getOrder())
			.append(" LIMIT ")
			.append(computerWrapper.getOffset())
			.append(",")
			.append(computerWrapper.getNumberPerPage());

		}
		stmt=cn.prepareStatement(sb.toString());
		rs = stmt.executeQuery();

		while (rs.next()) {

			Company company=Company.builder()
					.id(rs.getLong(5))
					.name(rs.getString(6))
					.build();

			Computer p = Computer.builder()
					.id(rs.getLong(1))
					.name(rs.getString(2))
					.company(company)
					.build();
			if(rs.getDate(3)!=null){
				p.setIntroduced(new DateTime(rs.getDate(3)));
			}
			if(rs.getDate(4)!=null){
				p.setDiscontinued(new DateTime(rs.getDate(4)));
			}


			listComputer.add(p);	

		}

		if (rs != null){

			rs.close();
		}

		if (stmt != null){

			stmt.close();
		}

		return listComputer;
	}


	public void insereComputer(Computer computer) throws SQLException, ParseException, ClassNotFoundException {
		Connection cn=DataSourceUtils.getConnection(ds);


		PreparedStatement stmt = null;

		stmt = cn.prepareStatement("INSERT into computer(name, introduced,discontinued,company_id) VALUES(?,?,?,?);");

		stmt.setString(1,computer.getName());

		if(computer.getIntroduced()!=null){
			stmt.setDate(2,new java.sql.Date(computer.getIntroduced().getMillis()));
		}else{
			stmt.setString(2,null);
		}
		if(computer.getDiscontinued()!=null){
			stmt.setDate(3,new java.sql.Date(computer.getDiscontinued().getMillis()));
		}else{
			stmt.setString(3,null);
		}
		if(computer.getCompany()!=null){
			stmt.setLong(4,computer.getCompany().getId());
		}else{
			stmt.setString(4,null);
		} 
		stmt.executeUpdate();		
		if (stmt != null){
			stmt.close();
		}
	}


	public void deleteComputer(Long id) throws SQLException, ClassNotFoundException{

		Connection cn=DataSourceUtils.getConnection(ds);

		PreparedStatement stmt = null;

		stmt = cn.prepareStatement("DELETE FROM computer WHERE id=?");

		stmt.setLong(1,id);

		stmt.executeUpdate();

		if (stmt != null){

			stmt.close();
		}
	}

	public void editComputer(Computer computer) throws SQLException, ParseException, ClassNotFoundException{

		Connection cn=DataSourceUtils.getConnection(ds);
		PreparedStatement stmt = null;

		stmt = cn.prepareStatement("UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=?  WHERE id=?");

		stmt.setString(1,computer.getName());

		if(computer.getIntroduced()!=null){
			stmt.setDate(2,new java.sql.Date(computer.getIntroduced().getMillis()));
		}else{
			stmt.setString(2,null);
		}
		if(computer.getDiscontinued()!=null){
			stmt.setDate(3,new java.sql.Date(computer.getDiscontinued().getMillis()));
		}else{
			stmt.setString(3,null);
		}

		if(computer.getCompany()!=null && computer.getCompany().getId()!=null){
			stmt.setLong(4,computer.getCompany().getId());
		}else{
			stmt.setString(4,null);
		}


		stmt.setLong(5,computer.getId());
		stmt.executeUpdate();


		if (stmt != null){

			stmt.close();


		}
	}

	public Computer getComputer(Long id) throws SQLException, ClassNotFoundException{
		Connection cn=DataSourceUtils.getConnection(ds);


		PreparedStatement stmt = null;
		ResultSet rs = null ;
		Computer p=new Computer();

		stmt = cn.prepareStatement("SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name FROM computer LEFT OUTER JOIN company ON computer.company_id=company.id WHERE(computer.id=?)");
		stmt.setLong(1,id);

		rs = stmt.executeQuery();

		while (rs.next()) {
			Company company=Company.builder()
					.id(rs.getLong(5))
					.name(rs.getString(6))
					.build();

			p = Computer.builder()
					.id(rs.getLong(1))
					.name(rs.getString(2))
					.company(company)
					.build();
			if(rs.getDate(3)!=null){
				p.setIntroduced(new DateTime(rs.getDate(3)));
			}
			if(rs.getDate(4)!=null){
				p.setDiscontinued(new DateTime(rs.getDate(4)));
			}
		}

		if (rs != null){

			rs.close();
		}

		if (stmt != null){

			stmt.close();
		}

		return p;
	}


	public Long countComputer(Wrapper computerWrapper) throws SQLException, ClassNotFoundException{
		Connection cn=DataSourceUtils.getConnection(ds);
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Long count = null;

		if (computerWrapper.getFilter()!=""&& computerWrapper.getFilter()!=null){
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT count(*) AS nombreComputer FROM computer LEFT OUTER JOIN company ON computer.company_id=company.id WHERE(")
			.append(computerWrapper.getFilterby())
			.append(".name LIKE '%")
			.append(computerWrapper.getFilter())
			.append("%')");
			stmt=cn.prepareStatement(sb.toString());
		}
		else{

			stmt=cn.prepareStatement("SELECT count(*) AS nombreComputer FROM computer");

		}

		rs = stmt.executeQuery();
		rs.next();

		count=rs.getLong(1);

		if (rs != null){

			rs.close();
		}

		if (stmt != null){

			stmt.close();
		}
		return count;
	}


}



