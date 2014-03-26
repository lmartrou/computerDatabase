package com.excilys.computerDatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.computerDatabase.service.ServiceFactory;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="getComputer"; 
	private static final String FIELD_FILTER="search";
	private static final String FIELD_FILTERBY="filterby";
	private static final String FIELD_ORDER="orderby";
	private static final String FIELD_PAGE="page";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
			Long id=(Long.valueOf(request.getParameter("id")));
			
			ServiceFactory.getInstance().getComputerService().deleteComputer(id);
			  
	        String filter=(String)request.getParameter(FIELD_FILTER);
			String filterby=(String)request.getParameter(FIELD_FILTERBY);
			String order=(String)request.getParameter(FIELD_ORDER);
			String page=(String)request.getParameter(FIELD_PAGE);
			
			
			request.setAttribute(FIELD_FILTER,filter);
			 request.setAttribute(FIELD_FILTERBY,filterby);
			 request.setAttribute(FIELD_FILTERBY,order);
			 request.setAttribute(FIELD_PAGE,page);
			 
			request.getRequestDispatcher(VIEW).forward(request,response);
		
	}
		
		 
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
