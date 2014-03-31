package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.dao.ComputerDao;
import com.excilys.computerDatabase.dao.ConnectionManager;
import com.excilys.computerDatabase.dao.LogDao;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.om.Log;
import com.excilys.computerDatabase.wrapper.Wrapper;

@Service
public class ComputerService {
	
	public ComputerService() {
	}

	@Autowired
	private ConnectionManager cm;
	
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	private LogDao logDao;
	
	
	public List<Computer> getListComputer(Wrapper computerWrapper) {
		Connection cn = null;
		List<Computer> listComputer = null;
		Log log=new Log();
		log.setOperation("getListComputer");

		try {
		
			cn = cm.getConnection();
			cn.setAutoCommit(false);
			listComputer = computerDao.getListComputer(computerWrapper);
			logDao.insereLog(log);
			cn.commit();
		}  catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			cm.closeConnection();

		}
		return listComputer;
	}

	public void insereComputer(Computer computer) {
		Connection cn = null;
		Log log=new Log();
		log.setOperation("insereComputer");
		log.setComputerId(computer.getId());
	    log.setComputerName(computer.getName());
	    

		try {
			cn = cm.getConnection();
			cn.setAutoCommit(false);
			computerDao.insereComputer(computer);
			logDao.insereLog(log);
			cn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
		cm.closeConnection();
			
		}

	}

	public void deleteComputer(Long id) {
		Connection cn = null;
		Log log=new Log();
		log.setOperation("deleteComputer");
		log.setComputerId(id);
		try {
			cn = cm.getConnection();
			cn.setAutoCommit(false);
			Computer computer=computerDao.getComputer(id);
		computerDao.deleteComputer(id);
			
			log.setComputerName(computer.getName());
			logDao.insereLog(log);
			cn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			cm.closeConnection();
		}
	}


	public Computer getComputer(Long id){
		Connection cn = null;
		Computer computer = null;
		Log log=new Log();
		log.setOperation("getComputer");

		try {
		
			cn = cm.getConnection();
			cn.setAutoCommit(false);
			computer = computerDao.getComputer(id);
			log.setComputerId(id);
			log.setComputerName(computer.getName());
			logDao.insereLog(log);
			cn.commit();
		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} finally {
			cm.closeConnection();

		}
		return computer;
	
	}

	public void editComputer(Computer computer) {
		Connection cn = null;
		Log log=new Log();
		log.setOperation("editComputer");
		log.setComputerId(computer.getId());
	    log.setComputerName(computer.getName());
		try {
			cn = cm.getConnection();
			cn.setAutoCommit(false);
			computerDao.editComputer(computer);
			logDao.insereLog(log);
			cn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			
					cm.closeConnection();
	
		}
	}

	public Wrapper pagination(Wrapper computerWrapper) {
		// TODO Auto-generated method stub
		Connection cn=null;

		try {
			cn = cm.getConnection();
			cn.setAutoCommit(false);

			computerWrapper.setListComputer(computerDao.getListComputer(computerWrapper));
			computerWrapper.setCount(computerDao.countComputer(computerWrapper));
			
		
			computerWrapper.setNoOfPage((long) Math.ceil(computerWrapper.getCount() * 1.0 / computerWrapper.getNumberPerPage()));
			cn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{

			cm.closeConnection();

		}
		return computerWrapper;
	}
}
