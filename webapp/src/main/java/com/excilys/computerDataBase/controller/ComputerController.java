package com.excilys.computerDataBase.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerDataBase.Dto.ComputerDto;
import com.excilys.computerDataBase.mapper.Mapper;
import com.excilys.computerDataBase.om.Company;
import com.excilys.computerDataBase.om.Computer;
import com.excilys.computerDataBase.service.CompanyService;
import com.excilys.computerDataBase.service.ComputerService;
import com.excilys.computerDataBase.wrapper.Wrapper;


@Controller
public class ComputerController {



	private static final String FIELD_ID = "id";
	private static final String FIELD_LCOMPANY = "listCompany",
			FIELD_LCOMPUTER = "listComputer", FIELD_WRAPPER = "wrapper",
			FIELD_COMPUTER = "computerDto";
	private static final String FIELD_FILTER = "search",
			FIELD_FILTERBY = "filterby", FIELD_ORDER = "orderby",
			FIELD_PAGE = "page";
	private ComputerService computerService;
	private CompanyService companyService;
	private List<Company> listCompany;

	@Autowired
	public ComputerController(ComputerService computerService,
			CompanyService companyService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}



	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashbord(
			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order){

		System.out.println("viens ce soir");
		Long numberPerPage = new Long(20);
		Mapper mapper = new Mapper();
		if (page == null || page== 0l) {
			page=1l;
		}

		Wrapper  wrapper = Wrapper.builder()
				.page(page)
				.filterby(filterpar)
				.filter(filtre)
				.order(order)
				.offset(String.valueOf((page - 1) * numberPerPage))
				.numberPerPage(Long.valueOf(numberPerPage)).build();

		wrapper = computerService.pagination(wrapper);

		List<Computer> listComputer = wrapper.getListComputer();
		List<ComputerDto> listComputerDto = new ArrayList<ComputerDto>();
		for (int i = 0; i < listComputer.size(); i++) {
			listComputerDto.add(mapper.toDto(listComputer.get(i)));
		}

		ModelAndView model= new ModelAndView();
		model.addObject(FIELD_LCOMPUTER, listComputerDto);
		model.addObject(FIELD_PAGE, page);
		model.addObject(FIELD_FILTER, filtre);
		model.addObject(FIELD_FILTERBY, filterpar);
		model.addObject(FIELD_ORDER,order);
		model.addObject(FIELD_WRAPPER,wrapper);

		model.setViewName("dashboard");
		return model ;


	}

	@RequestMapping(value = "/getCompany", method = RequestMethod.GET)
	public ModelAndView getCompany(
			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order,
			@RequestParam(value = FIELD_ID, required = false) Long id) {

		Mapper mapper = new Mapper();

		if (page == null || page == 0l) {
			page = 1l;
		}

		Wrapper wrapper = Wrapper.builder()
				.filter(filtre)
				.filterby(filterpar)
				.order(order).page(page)
				.build();

		listCompany = companyService.getListCompany();
		String VIEW = null;
		ComputerDto computerDto = new ComputerDto();
		if (id != null) {
			Computer computer = computerService.getComputer(id);
			computerDto = mapper.toDto(computer);
			VIEW = "editComputer";
		} else {
			VIEW = "addComputer";
		}



		ModelAndView model= new ModelAndView();

		model.addObject(FIELD_COMPUTER, computerDto);
		model.addObject(FIELD_LCOMPANY, listCompany);
		model.addObject(FIELD_WRAPPER, wrapper);
		model.setViewName(VIEW);
		return model ;



	}

	@RequestMapping(value = "/addComputer", method = RequestMethod.POST)
	public ModelAndView addComputer(
			@ModelAttribute(FIELD_COMPUTER) @Valid ComputerDto computerDto,
			BindingResult result) {


		ModelAndView model= new ModelAndView();
		model.setViewName("addComputer");

		if (!result.hasErrors()) {
		Mapper mapper = new Mapper();

		Computer computer = null;
		try {
			computer = mapper.fromDto(computerDto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			computerService.insereComputer(computer);
			model.setViewName("redirect:/dashboard");
		}

		model.addObject(FIELD_COMPUTER, computerDto);
		model.addObject(FIELD_LCOMPANY,listCompany);

		return model ;


	}

	@RequestMapping(value = "/deleteComputer", method = RequestMethod.GET)
	public ModelAndView deleteComputer(
			@RequestParam(value = FIELD_PAGE, required = false) Long page,
			@RequestParam(value = FIELD_FILTER, required = false) String filtre,
			@RequestParam(value = FIELD_FILTERBY, required = false) String filterpar,
			@RequestParam(value = FIELD_ORDER, required = false) String order,

			@RequestParam(value = FIELD_ID, required = false) Long id) {

		computerService.deleteComputer(id);

		ModelAndView model= new ModelAndView();
		model.setViewName("redirect:/dashboard");
		model.addObject(FIELD_PAGE, page);
		model.addObject(FIELD_FILTER, filtre);
		model.addObject(FIELD_FILTERBY, filterpar);
		model.addObject(FIELD_ORDER,order);
		return model;

	}

	@RequestMapping(value = "/editComputer", method = RequestMethod.POST)
	protected ModelAndView editComputer(@ModelAttribute("computerDto") @Valid ComputerDto computerDto,	
			BindingResult result) {

		Mapper mapper = new Mapper();


		ModelAndView model= new ModelAndView();

		if (!result.hasErrors()) {

			Computer computer = null;
			try {
				computer = mapper.fromDto(computerDto);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			computerService.editComputer(computer);
			model.setViewName("redirect:/dashboard");
		
		}
		model.addObject(FIELD_COMPUTER, computerDto);
		model.addObject(FIELD_LCOMPANY,listCompany);

		return model;



	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	protected ModelAndView error(){
		ModelAndView model= new ModelAndView();
		model.setViewName("error");
		return model;	
	
			
	}
}
