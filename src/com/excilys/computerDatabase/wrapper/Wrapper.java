package com.excilys.computerDatabase.wrapper;


import java.util.List;


import com.excilys.computerDatabase.om.Computer;




public class Wrapper {
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	private String filter,filterby,order,offset;
	private int noOfPage,page;
	private Long count,numberPerPage;
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
	
	
	@Override
	public String toString() {
		return "ComputerWrapper [filter=" + filter + ", filterby=" + filterby
				+ ", order=" + order + ", offset=" + offset + ", noOfPage=" + noOfPage + 
				", count=" + count + ", numberPerPage="
				+ numberPerPage + ", listComputer="
				+ listComputer + "]";
	}
	public static class Builder {

		Wrapper wrapper;

		private Builder() {
			wrapper = new Wrapper();
		}

		public Builder filter(String filter) {
			if(filter != null)
				this.wrapper.filter = filter;
			return this;
		}


		public Builder filterby(String filterby) {
			this.wrapper.filterby = filterby;
			return this;
		}

		public Builder order(String order) {
			this.wrapper.order = order;
			return this;
		}

		public Builder offset(String offset) {
			this.wrapper.offset = offset;
			return this;
		}

		public Builder listComputer(List<Computer> listComputer) {
			this.wrapper.listComputer = listComputer;
			return this;
		}
		
		public Builder numberPerPage(Long numberPerPage) {
			this.wrapper.numberPerPage =numberPerPage;
			return this;
		}
		
		public Builder count(Long count) {
			this.wrapper.count = count;
			return this;
		}
		public Builder noOfPage(int noOfPage) {
			this.wrapper.noOfPage = noOfPage;
			return this;
		}
		public Builder page(int page) {
			this.wrapper.page = page;
			return this;
		}
		public Wrapper build() {
			return this.wrapper;
		}

	}
	public static Builder builder() {
		return new Builder();
	}

}
