package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.mapper.Mapper;
import com.excilys.computerDatabase.om.*;

public enum ComputerDao {

	INSTANCE;

	private ComputerDao()
	{}



	/** Point d'accès pour l'instance unique du singleton */
	public static ComputerDao getInstance()
	{	return INSTANCE;
	}


	public List<Computer> getListComputer(ComputerWrapper computerWrapper,Connection cn) throws SQLException {


		ArrayList<Computer> listeComputeur  = new ArrayList<Computer>();
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		if(computerWrapper.getFilterby()=="" || computerWrapper.getFilterby() == null){
			computerWrapper.setFilterby("computer");
		}
		if(computerWrapper.getOrder()=="" || computerWrapper.getOrder() == null){
			computerWrapper.setOrder("computer.id");
		}
		StringBuffer sb=new StringBuffer();

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
		stmt=cn.prepareStatement(String.valueOf(sb));
		rs = stmt.executeQuery();

		while (rs.next()) {

			Company comp=Company.builder()
					.id(rs.getLong(5))
					.name(rs.getString(6))
					.build();

			Computer p = Computer.builder()
					.id(rs.getLong(1))
					.name(rs.getString(2))
					.introduced(rs.getDate(3))
					.discontinued(rs.getDate(4))
					.company(comp)
					.build();

			listeComputeur.add(p);
		}

		if (rs != null){

			rs.close();
		}

		if (stmt != null){

			stmt.close();
		}

		return listeComputeur;
	}


	public void insereComputer(ComputerDto cD,Connection cn) throws SQLException, ParseException {

		PreparedStatement stmt = null;
		Mapper mapper=new Mapper();
		Computer computer=mapper.fromDto(cD);

		stmt = cn.prepareStatement("INSERT into computer(name, introduced,discontinued,company_id) VALUES(?,?,?,?);");

		stmt.setString(1,computer.getName());

		if(computer.getIntroduced()!=null){
			stmt.setDate(2,new java.sql.Date(computer.getIntroduced().getTime()));
		}else{
			stmt.setString(2,"");
		}
		if(computer.getDiscontinued()!=null){
			stmt.setDate(3,new java.sql.Date(computer.getDiscontinued().getTime()));
		}else{
			stmt.setString(3,"");
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


	public void deleteComputer(ComputerWrapper computerWrapper,Connection cn) throws SQLException{


		PreparedStatement stmt = null;

		stmt = cn.prepareStatement("DELETE FROM computer WHERE id=?");

		stmt.setLong(1,computerWrapper.getId());

		stmt.executeUpdate();

		if (stmt != null){

			stmt.close();
		}
	}

	public void editComputer(ComputerDto cD,Connection cn) throws SQLException, ParseException{


		PreparedStatement stmt = null;
		Mapper mapper=new Mapper();
		Computer computer=mapper.fromDto(cD);


		stmt = cn.prepareStatement("UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=?  WHERE id=?");

		stmt.setString(1,computer.getName());

		if(computer.getIntroduced()!=null){
			stmt.setDate(2,(Date) computer.getIntroduced());
		}else{
			stmt.setString(2,"");
		}
		if(computer.getDiscontinued()!=null){
			stmt.setDate(3,(Date) computer.getDiscontinued());
		}else{
			stmt.setString(3,"");
		}

		if(computer.getCompany()!=null){
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


	public Long countComputer(ComputerWrapper computerWrapper,Connection cn) throws SQLException{
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Long count = null;

		if (computerWrapper.getFilter()!=""&& computerWrapper.getFilter()!=null){
			stmt=cn.prepareStatement(new String("SELECT count(*) AS nombreComputer FROM computer LEFT OUTER JOIN company ON computer.company_id=company.id WHERE("+computerWrapper.getFilterby()+".name LIKE '%"+computerWrapper.getFilter()+"%')" ));
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



