package com.excilys.computerDataBase.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Company;
import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.wrapper.Wrapper;
import com.jolbox.bonecp.BoneCPDataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class ComputerDao {

	@Autowired
	private BoneCPDataSource ds;

	public ComputerDao()
	{}

	private JdbcTemplate jt;

	/** Point d'accès pour l'instance unique du singleton */

	public List<Computer> getListComputer(Wrapper computerWrapper) {

		jt=new JdbcTemplate(ds);
		List<Computer> listComputer  = new ArrayList<Computer>();


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


		listComputer = jt.query(sb.toString(),new RowMapper<Computer>(){
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException{	
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

				return p;
			}
		});





		return listComputer;

	}


	public void insereComputer(Computer computer){

		jt=new JdbcTemplate(ds);




		Object[] params = new Object[4];
		params[0]=computer.getName();
		if(computer.getIntroduced()!=null){
			params[1]=new java.sql.Date(computer.getIntroduced().getMillis());
		}else{
			params[1]=null;
		}

		if(computer.getDiscontinued()!=null){
			params[2]=new java.sql.Date(computer.getDiscontinued().getMillis());
		}else{
			params[2]=null;
		}

		if(computer.getCompany()!=null){
			params[3]=(computer.getCompany().getId());

		}else{
			params[3]=null;
		}

		jt.update("INSERT into computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)",params);

	}


	public void deleteComputer(Long id) {

		jt=new JdbcTemplate(ds);

		StringBuilder sb=new StringBuilder();

		sb.append("DELETE FROM computer WHERE id=").append(id);

		jt.update(sb.toString());
	}

	public void editComputer(Computer computer){
		jt=new JdbcTemplate(ds);

		Object[] params = new Object[5];
		params[0]=computer.getName();
		
		if(computer.getIntroduced()!=null){
			params[1]=new java.sql.Date(computer.getIntroduced().getMillis());
		}else{
			params[1]=null;
		}

		if(computer.getDiscontinued()!=null){
			params[2]=new java.sql.Date(computer.getDiscontinued().getMillis());
		}else{
			params[2]=null;
		}

		if(computer.getCompany()!=null && computer.getCompany().getId()!=null){
			params[3]=(computer.getCompany().getId());

		}else{
			params[3]=null;
		}
		params[4]=computer.getId();

		jt.update("UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=?  WHERE id=?",params);

	}

	

	


public Computer getComputer(Long id){
	jt=new JdbcTemplate(ds);



	Computer computer=jt.queryForObject("SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name FROM computer LEFT OUTER JOIN company ON computer.company_id=company.id WHERE(computer.id=?)", 
			new Object[]{new Long(id)},new RowMapper<Computer>(){
		public Computer mapRow(ResultSet rs, int rowNum) throws SQLException{	
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

			return p;
		}
	});

	return computer;
}


public Long countComputer(Wrapper computerWrapper){

	jt=new JdbcTemplate(ds);
	StringBuilder sb=new StringBuilder();
	if (computerWrapper.getFilter()!=""&& computerWrapper.getFilter()!=null){

		sb.append("SELECT count(*) AS nombreComputer FROM computer LEFT OUTER JOIN company ON computer.company_id=company.id WHERE(")
		.append(computerWrapper.getFilterby())
		.append(".name LIKE '%")
		.append(computerWrapper.getFilter())
		.append("%')");

	}
	else{

		sb.append("SELECT count(*) AS nombreComputer FROM computer");

	}



	Long count=jt.queryForObject(sb.toString(), new RowMapper<Long>(){

		@Override
		public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getLong(1);
		}
	}
			);
	return count;

}


}



