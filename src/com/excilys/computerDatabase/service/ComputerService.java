package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import com.excilys.computerDatabase.dao.DaoFactory;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.om.Log;
import com.excilys.computerDatabase.wrapper.ComputerWrapper;

public enum ComputerService {
	INSTANCE;

	private ComputerService() {
	}

	public static ComputerService getInstance() {
		return INSTANCE;
	}


	public List<Computer> getListComputer(ComputerWrapper computerWrapper) {
		Connection cn = null;
		List<Computer> listComputer = null;
		Log log=new Log();
		log.setOperation("getListComputer");

		try {
		
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			listComputer = DaoFactory.getInstance().getComputerDao().getListComputer(computerWrapper);
			DaoFactory.getInstance().getLogDao().insereLog(log);
			cn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} finally {
			DaoFactory.getInstance().closeConnection();

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
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().getComputerDao().insereComputer(computer);
			DaoFactory.getInstance().getLogDao().insereLog(log);
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
			DaoFactory.getInstance().closeConnection();
			
		}

	}

	public void deleteComputer(Long id) {
		Connection cn = null;
		Log log=new Log();
		log.setOperation("deleteComputer");
		log.setComputerId(id);
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().getComputerDao().deleteComputer(id);
			DaoFactory.getInstance().getLogDao().insereLog(log);
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
			DaoFactory.getInstance().closeConnection();
		}
	}


	public Computer getComputer(Long id){
		Connection cn = null;
		Computer computer = null;
		Log log=new Log();
		log.setOperation("getComputer");

		try {
		
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			computer = DaoFactory.getInstance().getComputerDao().getComputer(id);
			log.setComputerId(id);
			log.setComputerName(computer.getName());
			DaoFactory.getInstance().getLogDao().insereLog(log);
			cn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} finally {
			DaoFactory.getInstance().closeConnection();

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
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().getComputerDao().editComputer(computer);
			DaoFactory.getInstance().getLogDao().insereLog(log);
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
			
					DaoFactory.getInstance().closeConnection();
	
		}
	}

	public ComputerWrapper pagination(ComputerWrapper computerWrapper) {
		// TODO Auto-generated method stub
		Connection cn=null;

		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);

			computerWrapper.setListComputer(DaoFactory.getInstance().getComputerDao().getListComputer(computerWrapper));
			computerWrapper.setCount(DaoFactory.getInstance().getComputerDao().countComputer(computerWrapper));
			computerWrapper.setNoOfPage((int) Math.ceil(computerWrapper.getCount() * 1.0 / computerWrapper.getNumberPerPage()));
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


			DaoFactory.getInstance().closeConnection();

		}
		return computerWrapper;
	}
}
