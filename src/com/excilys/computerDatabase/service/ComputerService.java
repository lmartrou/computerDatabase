package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.excilys.computerDatabase.dao.DaoFactory;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.om.ComputerDto;
import com.excilys.computerDatabase.om.ComputerWrapper;

public enum ComputerService {
	INSTANCE;

	private ComputerService(){}

	public static ComputerService getInstance(){
		return INSTANCE;
	}

	@SuppressWarnings("finally")
	public List<Computer> getListComputer(ComputerWrapper computerWrapper){
		Connection cn=null;
		List<Computer> listComputer=null;

		try {
			cn=DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			listComputer=DaoFactory.getInstance().getListComputer(computerWrapper,cn);
			cn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cn.rollback();
		}finally{
			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return listComputer;
		}





	}
	public void insereComputer(ComputerDto computer){
		Connection cn=null;

		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().insereComputer(computer,cn);
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
		}finally{
			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	public void deleteComputer(ComputerWrapper computerWrapper){
		Connection cn=null;
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().deleteComputer(computerWrapper,cn);
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
			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("finally")
	public Long countComputer(ComputerWrapper computerWrapper){
		Connection cn=null;
		Long count=null;
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			count=DaoFactory.getInstance().countComputer(computerWrapper,cn);
			cn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cn.rollback();
		}finally{
			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return count;
		}
	}
	public void editComputer(ComputerDto computer){
		Connection cn=null;
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().editComputer(computer, cn);
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
		}finally{
			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings({ "finally", "null" })
	public ComputerWrapper pagination(ComputerWrapper computerWrapper){
		Connection cn=null;

		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			computerWrapper.setListComputer(getListComputer(computerWrapper));
			computerWrapper.setCount(countComputer(computerWrapper));
			computerWrapper.setNoOfPage((int) Math.ceil(computerWrapper.getCount() * 1.0 / computerWrapper.getNumberPerPage()));
			cn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
				cn.rollback();
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


			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return computerWrapper;
		}
	}
}
