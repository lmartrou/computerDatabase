package com.excilys.computerDatabase.wrapper;

import java.sql.Date;
import java.util.List;

import com.excilys.computerDatabase.om.Computer;



public class ComputerWrapper {
	
	private String filter,filterby,order,offset,name;
	private int noOfPage;
	private Long id,company,count,numberPerPage;
	private Date introduced,discontinued;
	private List<Computer> listComputer;
	
	public int getNoOfPage() {
		return noOfPage;
	}
	public void setNoOfPage(int noOfPage) {
		this.noOfPage = noOfPage;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<Computer> getListComputer() {
		return listComputer;
	}
	public void setListComputer(List<Computer> list) {
		this.listComputer = list;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getFilterby() {
		return filterby;
	}
	public void setFilterby(String filterby) {
		this.filterby = filterby;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public Long getNumberPerPage() {
		return numberPerPage;
	}
	public void setNumberPerPage(Long numberPerPage) {
		this.numberPerPage = numberPerPage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompany() {
		return company;
	}
	public void setCompany(Long company) {
		this.company = company;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	@Override
	public String toString() {
		return "ComputerWrapper [filter=" + filter + ", filterby=" + filterby
				+ ", order=" + order + ", offset=" + offset + ", name=" + name
				+ ", noOfPage=" + noOfPage + ", id=" + id + ", company="
				+ company + ", count=" + count + ", numberPerPage="
				+ numberPerPage + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + ", listComputer="
				+ listComputer + "]";
	}

}
