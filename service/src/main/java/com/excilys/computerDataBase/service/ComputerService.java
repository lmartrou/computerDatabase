package com.excilys.computerDataBase.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.om.Log;
import com.excilys.computerDataBase.repository.ComputerRepository;
import com.excilys.computerDataBase.repository.LogRepository;
import com.excilys.computerDataBase.wrapper.Wrapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@Service
public class ComputerService {

	public ComputerService() {
	}



	@Autowired
	private ComputerRepository computerDao;

	@Autowired
	private LogRepository logDao;



	@Transactional(readOnly = false)
	public void insereComputer(Computer computer) {

		Log log=new Log();

		computerDao.save(computer);		
		log.setOperation("insereComputer");
		log.setComputerId(computer.getId());
		log.setComputerName(computer.getName());
		logDao.save(log);


	}
	@Transactional(readOnly = false)
	public void deleteComputer(Long id) {

		Log log=new Log();
		log.setOperation("deleteComputer");
		log.setComputerId(id);

		Computer computer=computerDao.findOne(id);
		computerDao.delete(id);

		log.setComputerName(computer.getName());
		logDao.save(log);


	}

	@Transactional(readOnly = false)
	public Computer getComputer(Long id){

		Computer computer = null;
		Log log=new Log();
		log.setOperation("getComputer");

		computer = computerDao.findOne(id);
		log.setComputerId(id);
		log.setComputerName(computer.getName());
		logDao.save(log);


		return computer;

	}
	@Transactional(readOnly = false)
	public void editComputer(Computer computer) {

		Log log=new Log();
		log.setOperation("editComputer");
		log.setComputerId(computer.getId());
		log.setComputerName(computer.getName());

		computerDao.save(computer);
		logDao.save(log);

	}
	
	@Transactional(readOnly = false)
	public Wrapper pagination(Wrapper computerWrapper) {
		// TODO Auto-generated method stub
		List<Computer> listComputer = null;
		long count=0l;
		PageRequest page;

		if(computerWrapper.getOrder()!=null && computerWrapper.getOrder()!=""){
			page=new PageRequest(Integer.valueOf(computerWrapper.getOffset()),computerWrapper.getNumberPerPage().intValue(),Direction.ASC,computerWrapper.getOrder());

		}else{
			page=new PageRequest(Integer.valueOf(computerWrapper.getOffset()),computerWrapper.getNumberPerPage().intValue());
		}
		
		if(computerWrapper.getFilterby()==null ||computerWrapper.getFilterby()==""||computerWrapper.getFilter()==null ||computerWrapper.getFilter()==""){
			listComputer =computerDao.findAll(page).getContent();
			count=computerDao.count();
		}
		else{
			if(computerWrapper.getFilterby().equals("computer")){
				listComputer = computerDao.findByNameContaining(computerWrapper.getFilter(), page);
				count=computerDao.countByNameContaining(computerWrapper.getFilter());

			}

			else{
				listComputer = computerDao.findByCompanyNameContaining(computerWrapper.getFilter(), page);
				count=computerDao.countByCompanyNameContaining(computerWrapper.getFilter());
			}

		}

		computerWrapper.setCount(count);
		computerWrapper.setListComputer(listComputer);

		computerWrapper.setNoOfPage((long) Math.ceil(count * 1.0 / computerWrapper.getNumberPerPage()));

		return computerWrapper;
	}
}
