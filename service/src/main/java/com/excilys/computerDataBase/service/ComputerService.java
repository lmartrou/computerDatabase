package com.excilys.computerDataBase.service;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerDataBase.dao.ComputerDao;
import com.excilys.computerDataBase.dao.LogDao;
import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.om.Log;
import com.excilys.computerDataBase.wrapper.Wrapper;


@Service
public class ComputerService {
	
	public ComputerService() {
	}


	
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	private LogDao logDao;
	
	
	@Transactional(readOnly = false)
	public List<Computer> getListComputer(Wrapper computerWrapper) {

		List<Computer> listComputer = null;
		Log log=new Log();
		log.setOperation("getListComputer");

		try {
		
		
			listComputer = computerDao.getListComputer(computerWrapper);
			logDao.insereLog(log);
			
		}  catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch(SQLException e) {
			
		}
		return listComputer;
	}
	
	@Transactional(readOnly = false)
	public void insereComputer(Computer computer) {
		
		Log log=new Log();

	    

		try {
			
			
			computerDao.insereComputer(computer);		
			log.setOperation("insereComputer");
		log.setComputerId(computer.getId());
	    log.setComputerName(computer.getName());
			logDao.insereLog(log);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Transactional(readOnly = false)
	public void deleteComputer(Long id) {
		
		Log log=new Log();
		log.setOperation("deleteComputer");
		log.setComputerId(id);
		try {
			
			Computer computer=computerDao.getComputer(id);
		computerDao.deleteComputer(id);
			
			log.setComputerName(computer.getName());
			logDao.insereLog(log);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		
		}
	}

	@Transactional(readOnly = false)
	public Computer getComputer(Long id){
		
		Computer computer = null;
		Log log=new Log();
		log.setOperation("getComputer");

		try {
		
			
			computer = computerDao.getComputer(id);
			log.setComputerId(id);
			log.setComputerName(computer.getName());
			logDao.insereLog(log);
			
		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch(SQLException e) {
			

		}
		return computer;
	
	}
	@Transactional(readOnly = false)
	public void editComputer(Computer computer) {
		
		Log log=new Log();
		log.setOperation("editComputer");
		log.setComputerId(computer.getId());
	    log.setComputerName(computer.getName());
		try {
			
			computerDao.editComputer(computer);
			logDao.insereLog(log);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
	
		}
	}
	@Transactional(readOnly = false)
	public Wrapper pagination(Wrapper computerWrapper) {
		// TODO Auto-generated method stub
		
		try {
			

			computerWrapper.setListComputer(computerDao.getListComputer(computerWrapper));
			computerWrapper.setCount(computerDao.countComputer(computerWrapper));
			
		
			computerWrapper.setNoOfPage((long) Math.ceil(computerWrapper.getCount() * 1.0 / computerWrapper.getNumberPerPage()));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return computerWrapper;
	}
}
