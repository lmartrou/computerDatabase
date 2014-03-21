package com.excilys.computerDatabase.servlet;

import java.io.IOException;








import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.om.Company;
import com.excilys.computerDatabase.om.ComputerDto;
import com.excilys.computerDatabase.service.ServiceFactory;



@WebServlet("/AddComputer")
public class AddComputerServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FIELD_NAME="name",FIELD_INTRODUCED="introducedDate",FIELD_DISCONTINUED="discontinuedDate",FIELD_COMPANY="company";
	private static final String FIELD_FILTER="search";
	private static final String FIELD_FILTERBY="filterby";
	private static final String FIELD_ORDER="orderby";
	private static final String FIELD_PAGE="page";
	private static final String VIEW="getComputer";
	private static final String VIEW_BIS="/WEB-INF/addComputer.jsp";
	public AddComputerServlet() {
		super();
	}  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			ServiceFactory serviceFactory=ServiceFactory.getInstance();

			List<Company> listCompany = serviceFactory.getListCompany();


			String filter=(String)request.getParameter(FIELD_FILTER);
			String filterby=(String)request.getParameter(FIELD_FILTERBY);
			String order=(String)request.getParameter(FIELD_ORDER);
			String page=(String)request.getParameter(FIELD_PAGE);
			
			request.setAttribute("listCompany",listCompany);
			request.setAttribute(FIELD_FILTER,filter);
			request.setAttribute(FIELD_FILTERBY,filterby);
			request.setAttribute(FIELD_ORDER,order);
			request.setAttribute(FIELD_PAGE,page);
			request.getRequestDispatcher(VIEW_BIS).forward(request,response);      


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ComputerDto.Builder cb = ComputerDto.builder();
		ServiceFactory serviceFactory =ServiceFactory.getInstance();

		
		String filter=(String)request.getParameter(FIELD_FILTER);
		String filterby=(String)request.getParameter(FIELD_FILTERBY);
		String order=(String)request.getParameter(FIELD_ORDER);
		String page=(String)request.getParameter(FIELD_PAGE);
		
		
		cb.name(request.getParameter(FIELD_NAME))
		.company(Long.valueOf(request.getParameter(FIELD_COMPANY)))
		.introduced(request.getParameter(FIELD_INTRODUCED))
		.discontinued(request.getParameter(FIELD_DISCONTINUED));
		
		serviceFactory.insereComputer(cb.build()); 

		request.setAttribute(FIELD_FILTER,filter);
		request.setAttribute(FIELD_FILTERBY,filterby);
		request.setAttribute(FIELD_ORDER,order);
		request.setAttribute(FIELD_PAGE,page);


		request.getRequestDispatcher(VIEW).forward(request,response);

	}   	  	    
}