package com.excilys.computerDatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.wrapper.Wrapper;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="DashboardServlet"; 
	private static final String FIELD_FILTER="search",FIELD_FILTERBY="filterby",FIELD_ORDER="orderby",FIELD_PAGE="page";
	private static final String FIELD_WRAPPER="wrapper",FIELD_ID="id";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	public DeleteComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		Long id=(Long.valueOf(request.getParameter(FIELD_ID)));

		computerService.deleteComputer(id);
		int page=1;
		if(request.getParameter(FIELD_PAGE) != null && request.getParameter(FIELD_PAGE) != ""){
			page =Integer.valueOf(request.getParameter(FIELD_PAGE));
		}
		Wrapper wrapper=Wrapper.builder()
				.filter((String)request.getParameter(FIELD_FILTER))
				.filterby((String)request.getParameter(FIELD_FILTERBY))
				.order((String)request.getParameter(FIELD_ORDER))
				.page(page)
				.build();

		request.setAttribute(FIELD_WRAPPER,wrapper);


		request.getRequestDispatcher(VIEW).forward(request,response);

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}


}
