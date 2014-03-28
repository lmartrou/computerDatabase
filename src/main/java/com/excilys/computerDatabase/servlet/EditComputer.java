package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerDatabase.Dto.ComputerDto;
import com.excilys.computerDatabase.mapper.Mapper;
import com.excilys.computerDatabase.om.Company;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.wrapper.Wrapper;

/**
 * Servlet implementation class EditeComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FIELD_NAME="name",FIELD_INTRODUCED="introducedDate",FIELD_DISCONTINUED="discontinuedDate",FIELD_COMPANY="company",FIELD_ID="computerId";
	private static final String FIELD_FILTER="search",FIELD_FILTERBY="filterby",FIELD_ORDER="orderby", FIELD_PAGE="page";
	private static final String FIELD_LCOMPANY="listCompany",FIELD_WRAPPER="wrapper",FIELD_COMPUTER="computer";
	private static final String VIEW="DashboardServlet",VIEW_BIS="/WEB-INF/editComputer.jsp";
	
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Mapper mapper=new Mapper();
		List<Company> listCompany = companyService.getListCompany();
		
		Wrapper wrapper=Wrapper.builder()
				.filter((String)request.getParameter(FIELD_FILTER))
				.filterby((String)request.getParameter(FIELD_FILTERBY))
				.order((String)request.getParameter(FIELD_ORDER))
				.page(Integer.valueOf(request.getParameter(FIELD_PAGE)))
				.build();
		Long id =Long.valueOf(request.getParameter(FIELD_ID));

		Computer computer=computerService.getComputer(id);
		ComputerDto computerDto=mapper.toDto(computer);

		request.setAttribute(FIELD_LCOMPANY,listCompany);
		request.setAttribute(FIELD_WRAPPER,wrapper);
		request.setAttribute(FIELD_COMPUTER,computerDto);

		request.getRequestDispatcher(VIEW_BIS).forward(request,response);      



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		Mapper mapper=new Mapper();

		ComputerDto computerDto =ComputerDto.builder()
				.id(Long.valueOf(request.getParameter("id")))
				.name(request.getParameter(FIELD_NAME))
				.company(Long.valueOf(request.getParameter(FIELD_COMPANY)))
				.introduced(request.getParameter(FIELD_INTRODUCED))
				.discontinued(request.getParameter(FIELD_DISCONTINUED))
				.build();

		Computer computer=null;
		try {
			computer = mapper.fromDto(computerDto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		computerService.editComputer(computer);
		
		Wrapper wrapper=Wrapper.builder()
				.filter((String)request.getParameter(FIELD_FILTER))
				.filterby((String)request.getParameter(FIELD_FILTERBY))
				.order((String)request.getParameter(FIELD_ORDER))
				.page(Integer.valueOf(request.getParameter(FIELD_PAGE)))
				.build();


		request.setAttribute(FIELD_WRAPPER,wrapper);

		request.getRequestDispatcher(VIEW).forward(request,response);

	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}



}
