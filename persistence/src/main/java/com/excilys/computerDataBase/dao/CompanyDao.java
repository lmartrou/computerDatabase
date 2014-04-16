package com.excilys.computerDataBase.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Company;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class CompanyDao {
	
	
	public CompanyDao()
	{}
	
	@Autowired
	private BoneCPDataSource ds;
	/** Point d'accès pour l'instance unique du singleton */
	
	private JdbcTemplate jt;
	
	
	public List<Company> getListCompany() {
		
		jt= new JdbcTemplate(ds);
		
		List<Company> listeCompany  = new ArrayList<Company>();
		listeCompany=jt.query("SELECT id, name FROM company", new RowMapper<Company>(){
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException{
				Company company=new Company();
				company.setId(rs.getLong("id"));
				company.setName(rs.getString("name"));
				return company;
			}
		});
			
		
			
			
		return listeCompany;
	}


	
}


