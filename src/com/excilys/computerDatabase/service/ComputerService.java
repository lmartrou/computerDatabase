package com.excilys.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.excilys.computerDatabase.dao.DaoFactory;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.om.ComputerDto;
import com.excilys.computerDatabase.om.ComputerWrapper;
import com.excilys.computerDatabase.om.Log;

public enum ComputerService {
	INSTANCE;

	private ComputerService() {
	}

	public static ComputerService getInstance() {
		return INSTANCE;
	}


	public List<ComputerDto> getListComputer(ComputerWrapper computerWrapper) {
		Connection cn = null;
		List<ComputerDto> listComputer = null;
		Log log=new Log();
		log.setOperation("getListComputer");

		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			listComputer = DaoFactory.getInstance().getListComputer(
					computerWrapper, cn);
			DaoFactory.getInstance().insereLog(log, cn);
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
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return listComputer;
	}

	public void insereComputer(ComputerDto computer) {
		Connection cn = null;
		Log log=new Log();
		log.setOperation("insereComputer");
		log.setComputerId(computer.getId());
	    log.setComputerName(computer.getName());
	    

		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().insereComputer(computer, cn);
			DaoFactory.getInstance().insereLog(log, cn);
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
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void deleteComputer(ComputerWrapper computerWrapper) {
		Connection cn = null;
		Log log=new Log();
		log.setOperation("deleteComputer");
		log.setComputerId(computerWrapper.getId());
	    log.setComputerName(computerWrapper.getName());
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().deleteComputer(computerWrapper, cn);
			DaoFactory.getInstance().insereLog(log, cn);
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
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	public Long countComputer(ComputerWrapper computerWrapper) {
		Connection cn = null;
		Long count = null;
		Log log=new Log();
		log.setOperation("countComputer");
	
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			count = DaoFactory.getInstance().countComputer(computerWrapper, cn);
			DaoFactory.getInstance().insereLog(log, cn);
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
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return count;
	}

	public void editComputer(ComputerDto computer) {
		Connection cn = null;
		Log log=new Log();
		log.setOperation("editComputer");
		log.setComputerId(computer.getId());
	    log.setComputerName(computer.getName());
		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);
			DaoFactory.getInstance().editComputer(computer, cn);
			DaoFactory.getInstance().insereLog(log, cn);
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
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public ComputerWrapper pagination(ComputerWrapper computerWrapper) {
		// TODO Auto-generated method stub
		Connection cn=null;

		try {
			cn = DaoFactory.getInstance().getConnection();
			cn.setAutoCommit(false);

			computerWrapper.setListComputer(DaoFactory.getInstance().getListComputer(computerWrapper,cn));
			computerWrapper.setCount(DaoFactory.getInstance().countComputer(computerWrapper,cn));
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


			if(cn!=null){
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return computerWrapper;
	}
}
