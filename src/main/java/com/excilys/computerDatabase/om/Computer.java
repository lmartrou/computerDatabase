package com.excilys.computerDatabase.om;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


@Component
public class Computer {


	private Long id;
	private String name;
	private DateTime introduced;
	private DateTime discontinued;
	private Company company;


	public Computer() {
		super();
		// TODO Auto-generated constructor stub
	}


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


	public DateTime getIntroduced() {
		return introduced;
	}


	public void setIntroduced(DateTime introduced) {
		this.introduced = introduced;
	}


	public DateTime getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(DateTime discontinued) {
		this.discontinued = discontinued;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;

	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public String toString() {
		return "Computeur [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}


	/*
	 * Builder
	 */
	public static class Builder {

		Computer computer;

		private Builder() {
			computer = new Computer();
		}

		public Builder id(Long id) {
			if(id != null)
				this.computer.id = id;
			return this;
		}

		public Builder name(String name) {
			this.computer.name = name;
			return this;
		}

		public Builder introduced(DateTime introduced) {
			this.computer.introduced = introduced;
			return this;
		}

		public Builder discontinued(DateTime discontinued) {
			this.computer.discontinued = discontinued;
			return this;
		}

		public Builder company(Company company) {
			this.computer.company = company;
			return this;
		}

		public Computer build() {
			return this.computer;
		}

	}

	public static Builder builder() {
		return new Builder();
	}


	

}

