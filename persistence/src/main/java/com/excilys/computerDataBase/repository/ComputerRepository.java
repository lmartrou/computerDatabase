package com.excilys.computerDataBase.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerDataBase.om.Computer;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
List<Computer> findByNameContaining(String name, Pageable page);
Long countByNameContaining(String name);
List<Computer> findByCompanyNameContaining(String name, Pageable page);
Long countByCompanyNameContaining(String name);
}