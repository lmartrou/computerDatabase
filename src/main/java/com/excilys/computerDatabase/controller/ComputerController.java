package com.excilys.computerDatabase.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDatabase.Dto.ComputerDto;
import com.excilys.computerDatabase.mapper.Mapper;
import com.excilys.computerDatabase.om.Company;
import com.excilys.computerDatabase.om.Computer;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.wrapper.Wrapper;

@Controller
public class ComputerController {
	private static final String FIELD_NAME = "name",
			FIELD_INTRODUCED = "introducedDate",
			FIELD_DISCONTINUED = "discontinuedDate", FIELD_COMPANY = "company",
			FIELD_ID = "id";
	private static final String FIELD_FILTER = "search",
			FIELD_FILTERBY = "filterby", FIELD_ORDER = "orderby",
			FIELD_PAGE = "page";
	private static final String FIELD_LCOMPANY = "listCompany",
			FIELD_LCOMPUTER = "listComputer", FIELD_WRAPPER = "wrapper",
			FIELD_COMPUTER = "computer";
	private ComputerService computerService;
	private CompanyService companyService;

	@Autowired
	public ComputerController(ComputerService computerService,
			CompanyService companyService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashbord(
			Model model,

			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order) {

		Long numberPerPage = new Long(20);
		Mapper mapper = new Mapper();
		if (page == null || page == 0l) {
			page = 1l;
		}

		Wrapper wrapper = Wrapper.builder().filter(filtre).filterby(filterpar)
				.order(order).page(page)
				.offset(String.valueOf((page - 1) * numberPerPage))
				.numberPerPage(Long.valueOf(numberPerPage)).build();

		wrapper = computerService.pagination(wrapper);

		List<Computer> listComputer = wrapper.getListComputer();
		List<ComputerDto> listComputerDto = new ArrayList<ComputerDto>();
		for (int i = 0; i < listComputer.size(); i++) {
			listComputerDto.add(mapper.toDto(listComputer.get(i)));
		}

		model.addAttribute(FIELD_LCOMPUTER, listComputerDto);
		model.addAttribute(FIELD_WRAPPER, wrapper);

		return "dashboard";

	}

	@RequestMapping(value = "/getCompany", method = RequestMethod.GET)
	public String getCompany(
			Model model,

			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order,
			@RequestParam(value = FIELD_ID, required = false) Long id) {

		Long numberPerPage = new Long(20);
		if (page == null || page == 0l) {
			page = 1l;
		}
		Mapper mapper = new Mapper();
		Wrapper wrapper = Wrapper.builder().filter(filtre).filterby(filterpar)
				.order(order).page(page)
				.offset(String.valueOf((page - 1) * numberPerPage))
				.numberPerPage(Long.valueOf(numberPerPage)).build();

		List<Company> listCompany = companyService.getListCompany();
		String VIEW = null;
		ComputerDto computerDto = null;
		if (id != null) {
			Computer computer = computerService.getComputer(id);
			computerDto = mapper.toDto(computer);
			VIEW = "editComputer";
		} else {
			VIEW = "addComputer";
		}

		model.addAttribute(FIELD_COMPUTER, computerDto);
		model.addAttribute(FIELD_LCOMPANY, listCompany);
		model.addAttribute(FIELD_WRAPPER, wrapper);

		return VIEW;

	}

	@RequestMapping(value = "/addComputer", method = RequestMethod.POST)
	public String addComputer(
			Model model,

			@RequestParam(value = FIELD_NAME, required = false) String pComputerName,
			@RequestParam(value = FIELD_INTRODUCED, required = false) String pComputerIntroduced,
			@RequestParam(value = FIELD_DISCONTINUED, required = false) String pComputerDiscontinued,
			@RequestParam(value = FIELD_COMPANY, required = false) Long pCompanyId,
			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order) {

		Mapper mapper = new Mapper();

		ComputerDto computerDto = ComputerDto.builder().name(pComputerName)
				.company(pCompanyId).introduced(pComputerIntroduced)
				.discontinued(pComputerDiscontinued).build();

		Computer computer = null;
		try {
			computer = mapper.fromDto(computerDto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		computerService.insereComputer(computer);

		model.addAttribute(FIELD_PAGE, page);
		model.addAttribute(FIELD_FILTER, filtre);
		model.addAttribute(FIELD_FILTERBY, filterpar);
		model.addAttribute(FIELD_ORDER,order);


		return "redirect:/dashboard";

	}

	@RequestMapping(value = "/deleteComputer", method = RequestMethod.GET)
	public String deleteComputer(
			Model model,

			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order,
			@RequestParam(value = FIELD_ID, required = false) Long id) {

		computerService.deleteComputer(id);

		
		model.addAttribute(FIELD_PAGE, page);
		model.addAttribute(FIELD_FILTER, filtre);
		model.addAttribute(FIELD_FILTERBY, filterpar);
		model.addAttribute(FIELD_ORDER,order);

		return "redirect:/dashboard";

	}

	@RequestMapping(value = "/editComputer", method = RequestMethod.POST)
	protected ModelAndView editComputer(
		
			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order,
			@RequestParam(value = FIELD_ID, required = false) Long id,
			@RequestParam(value = FIELD_NAME, required = false) String pComputerName,
			@RequestParam(value = FIELD_INTRODUCED, required = false) String pComputerIntroduced,
			@RequestParam(value = FIELD_DISCONTINUED, required = false) String pComputerDiscontinued,
			@RequestParam(value = FIELD_COMPANY, required = false) Long pCompanyId) {

		Mapper mapper = new Mapper();

		ComputerDto computerDto = ComputerDto.builder().id(id)
				.name(pComputerName).company(pCompanyId)
				.introduced(pComputerIntroduced)
				.discontinued(pComputerDiscontinued).build();

		Computer computer = null;
		try {
			computer = mapper.fromDto(computerDto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		computerService.editComputer(computer);
ModelAndView model= new ModelAndView();
model.setViewName("redirect:dashboard");
		model.addObject(FIELD_PAGE, page);
		model.addObject(FIELD_FILTER, filtre);
		model.addObject(FIELD_FILTERBY, filterpar);
		model.addObject(FIELD_ORDER,order);

		return model;

	}

}
