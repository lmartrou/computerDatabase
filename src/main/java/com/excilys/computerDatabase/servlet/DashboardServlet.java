package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerDatabase.Dto.ComputerDto;
import com.excilys.computerDatabase.mapper.Mapper;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.wrapper.Wrapper;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/dashboard.jsp";
	private static final String FIELD_FILTER="search", FIELD_FILTERBY="filterby",FIELD_ORDER="orderby",FIELD_PAGE="page";
	private static final String FIELD_LCOMPUTER="listComputer",FIELD_WRAPPER="wrapper";
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
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
		
		int page=1;
		Long numberPerPage=new Long(20);
		Mapper mapper=new Mapper();


		if(request.getParameter(FIELD_PAGE) != null && request.getParameter(FIELD_PAGE) != ""){
			page =Integer.valueOf(request.getParameter(FIELD_PAGE));
		}

		Wrapper wrapper=Wrapper.builder()
				.filter((String)request.getParameter(FIELD_FILTER))
				.filterby((String)request.getParameter(FIELD_FILTERBY))
				.order((String)request.getParameter(FIELD_ORDER))
				.page(page)
				.offset(String.valueOf((page-1)*numberPerPage))
				.numberPerPage(Long.valueOf(numberPerPage))
				.build();
	
		wrapper=computerService.pagination(wrapper);

		List<Computer> listComputer =wrapper.getListComputer();
		List<ComputerDto> listComputerDto=new ArrayList<ComputerDto>();
		for (int i = 0; i < listComputer.size(); i++) {
			listComputerDto.add(mapper.toDto(listComputer.get(i)));
		}
		
		request.setAttribute(FIELD_LCOMPUTER,listComputerDto);
		request.setAttribute(FIELD_WRAPPER, wrapper);

		request.getRequestDispatcher(VIEW).forward(request,response);    




	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page=1;
		Long numberPerPage=new Long(20);
		Mapper mapper=new Mapper();


		if(request.getParameter(FIELD_PAGE) != null && request.getParameter(FIELD_PAGE) != ""){
			page =Integer.valueOf(request.getParameter(FIELD_PAGE));
		}

		
		Wrapper wrapper=Wrapper.builder()
				.filter((String)request.getParameter(FIELD_FILTER))
				.filterby((String)request.getParameter(FIELD_FILTERBY))
				.order((String)request.getParameter(FIELD_ORDER))
				.page(page)
				.offset(String.valueOf((page-1)*numberPerPage))
				.numberPerPage(Long.valueOf(numberPerPage))
				.build();


		wrapper=computerService.pagination(wrapper);

		List<Computer> listComputer =wrapper.getListComputer();
		List<ComputerDto> listComputerDto=new ArrayList<ComputerDto>();

		for (int i = 0; i < listComputer.size(); i++) {
			listComputerDto.add(mapper.toDto(listComputer.get(i)));
		}

	
		request.setAttribute(FIELD_LCOMPUTER,listComputerDto);
		request.setAttribute(FIELD_WRAPPER, wrapper);

		request.getRequestDispatcher(VIEW).forward(request,response);    



	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}

}
