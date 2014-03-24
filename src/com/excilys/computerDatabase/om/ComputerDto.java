package com.excilys.computerDatabase.om;

public class ComputerDto {

		private Long id;
		private String name;
		private String introduced;
		private String discontinued;
		private Long company
		;
		public String getCompanyName() {
			return companyName;
		}


		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		private String companyName;


		public ComputerDto() {
			
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


		public String getIntroduced() {
			return introduced;
		}


		public void setIntroduced(String introduced) {
			this.introduced = introduced;
		}


		public String getDiscontinued() {
			return discontinued;
		}


		public void setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
		}


		public Long getCompany() {
			return company;
		}


		public void setCompany(Long company) {
			this.company = company;

		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
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

			ComputerDto computerDto;

			private Builder() {
				computerDto = new ComputerDto();
			}

			public Builder id(Long id) {
				if(id != null)
					this.computerDto.id = id;
				return this;
			}

			public Builder name(String name) {
				this.computerDto.name = name;
				return this;
			}

			public Builder introduced(String introduced) {
				this.computerDto.introduced = introduced;
				return this;
			}

			public Builder discontinued(String discontinued) {
				this.computerDto.discontinued = discontinued;
				return this;
			}

			public Builder company(Long company) {
				this.computerDto.company = company;
				return this;
			}
			public Builder company(String companyName) {
				this.computerDto.companyName = companyName;
				return this;
			}
			public ComputerDto build() {
				return this.computerDto;
			}

		}

		public static Builder builder() {
			return new Builder();
		}


		

	}

