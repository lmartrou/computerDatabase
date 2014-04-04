package com.excilys.computerDatabase.mapper;


import java.text.ParseException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.Dto.ComputerDto;
import com.excilys.computerDatabase.om.*;

@Component
public class Mapper {

	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	
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
			cb.introduced(formatter.parseDateTime(dto.getIntroduced()));
		}

		if(dto.getDiscontinued() != null && !dto.getDiscontinued().isEmpty()){
			cb.discontinued(formatter.parseDateTime(dto.getDiscontinued()));
		}
		return cb.build();
	}

	public ComputerDto toDto(Computer obj) {

		if(obj == null)
			return null;

		ComputerDto.Builder cdtob = ComputerDto.builder();

		cdtob.id(obj.getId()).name(obj.getName());

		if(obj.getIntroduced() != null)
			cdtob.introduced(obj.getIntroduced().toString().substring(0,10));

		if(obj.getDiscontinued() != null)
			cdtob.discontinued(obj.getDiscontinued().toString().substring(0, 10));

		if(obj.getCompany() != null)
			cdtob.company(obj.getCompany().getId());
			cdtob.companyName(obj.getCompany().getName());

		return cdtob.build();

	}
}



