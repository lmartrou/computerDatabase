
package com.excilys.computerDataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Company;
 
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

}


