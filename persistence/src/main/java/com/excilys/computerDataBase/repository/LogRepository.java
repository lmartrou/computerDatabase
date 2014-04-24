package com.excilys.computerDataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Log;
@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

}
