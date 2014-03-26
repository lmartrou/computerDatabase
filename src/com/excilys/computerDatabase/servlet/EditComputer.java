package com.excilys.computerDatabase.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.Dto.ComputerDto;
import com.excilys.computerDatabase.mapper.Mapper;
import com.excilys.computerDatabase.om.Company;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.service.ServiceFactory;
import com.excilys.computerDatabase.wrapper.Wrapper;

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

	private static final String FIELD_PAGE="page";

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Mapper mapper=new Mapper();
		List<Company> listCompany = ServiceFactory.getInstance().getCompanyService().getListCompany();
		
		Wrapper wrapper=Wrapper.builder()
				.filter((String)request.getParameter(FIELD_FILTER))
				.filterby((String)request.getParameter(FIELD_FILTERBY))
				.order((String)request.getParameter(FIELD_ORDER))
				.page(Integer.valueOf(request.getParameter(FIELD_PAGE)))
				.build();
		Long id =Long.valueOf(request.getParameter(FIELD_ID));

		Computer computer=ServiceFactory.getInstance().getComputerService().getComputer(id);
		ComputerDto computerDto=mapper.toDto(computer);

		String page=(String)request.getParameter(FIELD_PAGE);

		request.setAttribute("listCompany",listCompany);
		request.setAttribute("wrapper",wrapper);
		request.setAttribute("computer",computerDto);

		request.setAttribute(FIELD_PAGE,page);
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

		ServiceFactory.getInstance().getComputerService().editComputer(computer);
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


		request.setAttribute("wrapper",wrapper);

		request.getRequestDispatcher(VIEW).forward(request,response);

	}


}
