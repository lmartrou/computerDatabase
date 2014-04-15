package com.excilys.computerDataBase.om;

import org.springframework.stereotype.Component;


@Component
public class Company {
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}




	public static class Builder {

		Company company;

		private Builder() {
			company = new Company();
		}

		public Builder id(Long id) {
			if(id != null)
				this.company.id = id;
			return this;
		}

		public Builder name(String name) {
			this.company.name = name;
			return this;
		}


		public Company build() {
			return this.company;
		}

	}

		public static Builder builder() {
			return new Builder();
		}


	

}