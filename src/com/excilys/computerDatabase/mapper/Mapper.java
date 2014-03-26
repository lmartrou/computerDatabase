package com.excilys.computerDatabase.mapper;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.excilys.computerDatabase.Dto.ComputerDto;
import com.excilys.computerDatabase.om.*;


public class Mapper {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public Mapper(){}

	public Computer fromDto(ComputerDto dto) throws ParseException {
		if(dto == null)
			return null;

		Computer.Builder cb = Computer.builder();

		cb.id(dto.getId()).name(dto.getName());

		if(dto.getCompany() != null && !dto.getCompany().equals(0L)){
			Company company=Company.builder()
					.id(dto.getCompany())
					.build();

			cb.company(company);
		}

		if(dto.getIntroduced() != null && !dto.getIntroduced().isEmpty()){
			cb.introduced(dateFormat.parse(dto.getIntroduced()));
		}

		if(dto.getDiscontinued() != null && !dto.getDiscontinued().isEmpty()){
			cb.discontinued(dateFormat.parse(dto.getDiscontinued()));
		}
		return cb.build();
	}

	public ComputerDto toDto(Computer obj) {

		if(obj == null)
			return null;

		ComputerDto.Builder cdtob = ComputerDto.builder();

		cdtob.id(obj.getId()).name(obj.getName());

		if(obj.getIntroduced() != null)
			cdtob.introduced(obj.getIntroduced().toString());

		if(obj.getDiscontinued() != null)
			cdtob.discontinued(obj.getDiscontinued().toString());

		if(obj.getCompany() != null)
			cdtob.company(obj.getCompany().getId());
			cdtob.companyName(obj.getCompany().getName());

		return cdtob.build();

	}
}



