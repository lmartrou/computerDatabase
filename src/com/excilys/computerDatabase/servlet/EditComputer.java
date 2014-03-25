package com.excilys.computerDatabase.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.om.Company;
import com.excilys.computerDatabase.om.ComputerDto;

import com.excilys.computerDatabase.service.ServiceFactory;

/**
 * Servlet implementation class EditeComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FIELD_NAME="name",FIELD_INTRODUCED="introducedDate",FIELD_DISCONTINUED="discontinuedDate",FIELD_COMPANY="company";
	private static final String VIEW="getComputer";
	private static final String VIEW_BIS="/WEB-INF/editComputer.jsp";
	private static final String FIELD_FILTER="search";
	private static final String FIELD_FILTERBY="filterby";
	private static final String FIELD_ORDER="orderby";
	private static final String FIELD_ID="computerId";
	private static final String FIELD_IN="computerIntroduced";
	private static final String FIELD_DI="computerDiscontinued";
	private static final String FIELD_CO="computerCompany";
	private static final String FIELD_CON="companyName";
	private static final String FIELD_NA="computerName";
	

	
	private static final String FIELD_PAGE="page";

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			ServiceFactory serviceFactory=ServiceFactory.getInstance();

			List<Company> listCompany = serviceFactory.getListCompany();


			String filter=(String)request.getParameter(FIELD_FILTER);
			String filterby=(String)request.getParameter(FIELD_FILTERBY);
			String order=(String)request.getParameter(FIELD_ORDER);
			ComputerDto p = ComputerDto.builder()
					.id(Long.valueOf(request.getParameter(FIELD_ID)))
					.name(request.getParameter(FIELD_NA))
					.introduced(request.getParameter(FIELD_IN))
					.discontinued(request.getParameter(FIELD_DI))
					.companyName(request.getParameter(FIELD_CON))
					.company(Long.valueOf(request.getParameter(FIELD_CO)))
					.build();

			
			String page=(String)request.getParameter(FIELD_PAGE);
			
			request.setAttribute("listCompany",listCompany);
			request.setAttribute(FIELD_FILTER,filter);
			request.setAttribute(FIELD_FILTERBY,filterby);
			request.setAttribute(FIELD_ORDER,order);
			request.setAttribute("computer",p);
			
			request.setAttribute(FIELD_PAGE,page);
			request.getRequestDispatcher(VIEW_BIS).forward(request,response);      



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


			ServiceFactory serviceFactory=ServiceFactory.getInstance();

			ComputerDto.Builder cb = ComputerDto.builder();
			
			cb.id(Long.valueOf(request.getParameter("id")))
			.name(request.getParameter(FIELD_NAME))
			.company(Long.valueOf(request.getParameter(FIELD_COMPANY)))
			.introduced(request.getParameter(FIELD_INTRODUCED))
			.discontinued(request.getParameter(FIELD_DISCONTINUED));
				
			serviceFactory.editComputer(cb.build());
			
			String filter=(String)request.getParameter(FIELD_FILTER);
			String filterby=(String)request.getParameter(FIELD_FILTERBY);
			String order=(String)request.getParameter(FIELD_ORDER);
			String page=(String)request.getParameter(FIELD_PAGE);
			
		
			request.setAttribute(FIELD_FILTER,filter);
			request.setAttribute(FIELD_FILTERBY,filterby);
			request.setAttribute(FIELD_ORDER,order);
			request.setAttribute(FIELD_PAGE,page);

			request.getRequestDispatcher(VIEW).forward(request,response);
		


	}


}
