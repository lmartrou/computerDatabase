package com.excilys.computerDatabase.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.om.*;
import com.excilys.computerDatabase.service.ServiceFactory;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/getComputer")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/dashboard.jsp";
	private static final String FIELD_FILTER="search";
	private static final String FIELD_FILTERBY="filterby";
	private static final String FIELD_ORDER="orderby";
	private static final String FIELD_PAGE="page";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComputerWrapper computerWrapper=new ComputerWrapper();
		Long page=new Long(1);
		Long numberPerPage=new Long(20);

		ServiceFactory serviceFactory=ServiceFactory.getInstance();


		if(request.getParameter(FIELD_PAGE) != null && request.getParameter(FIELD_PAGE) != ""){
			page =Long.valueOf(request.getParameter(FIELD_PAGE));
		}

		computerWrapper.setFilter((String)request.getParameter(FIELD_FILTER));
		computerWrapper.setFilterby((String)request.getParameter(FIELD_FILTERBY));
		computerWrapper.setOrder((String)request.getParameter(FIELD_ORDER));
		computerWrapper.setOffset(String.valueOf((page-1)*numberPerPage));
		computerWrapper.setNumberPerPage(Long.valueOf(numberPerPage));


	computerWrapper=serviceFactory.pagination(computerWrapper);


		request.setAttribute("noOfPage",computerWrapper.getNoOfPage());
		request.setAttribute("currentPage", page);
		request.setAttribute(FIELD_PAGE, page);
		request.setAttribute("listComputer",computerWrapper.getListComputer());
		request.setAttribute("name",computerWrapper.getFilter());
		request.setAttribute("filtrerpar",computerWrapper.getFilterby());
		request.setAttribute("rangerpar",computerWrapper.getOrder());
		request.setAttribute("count",computerWrapper.getCount()+" computers found");

		request.getRequestDispatcher(VIEW).forward(request,response);    




	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ComputerWrapper computerWrapper=new ComputerWrapper();
		Long page=new Long(1);
		Long numberPerPage=new Long(20);

		ServiceFactory serviceFactory=ServiceFactory.getInstance();


		if(request.getParameter(FIELD_PAGE) != null && request.getParameter(FIELD_PAGE) != ""){
			page =Long.valueOf(request.getParameter(FIELD_PAGE));
		}

		computerWrapper.setFilter((String)request.getParameter(FIELD_FILTER));
		computerWrapper.setFilterby((String)request.getParameter(FIELD_FILTERBY));
		computerWrapper.setOrder((String)request.getParameter(FIELD_ORDER));
		computerWrapper.setOffset(String.valueOf((page-1)*numberPerPage));
		computerWrapper.setNumberPerPage(Long.valueOf(numberPerPage));


	computerWrapper=serviceFactory.pagination(computerWrapper);


		request.setAttribute("noOfPage",computerWrapper.getNoOfPage());
		request.setAttribute("currentPage", page);
		request.setAttribute(FIELD_PAGE, page);
		request.setAttribute("listComputer",computerWrapper.getListComputer());
		request.setAttribute("name",computerWrapper.getFilter());
		request.setAttribute("filtrerpar",computerWrapper.getFilterby());
		request.setAttribute("rangerpar",computerWrapper.getOrder());
		request.setAttribute("count",computerWrapper.getCount()+" computers found");

		request.getRequestDispatcher(VIEW).forward(request,response);    



	}

}
