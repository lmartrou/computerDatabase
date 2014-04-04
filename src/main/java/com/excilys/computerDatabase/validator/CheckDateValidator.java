package com.excilys.computerDatabase.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class CheckDateValidator implements ConstraintValidator<CheckDate, String> {



	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

	@Override
	public void initialize(CheckDate constraintAnnotation) {

	}

	@Override
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
	
		if(object==null|| object==""){
			return true;
		}else{
			try{
				DateTime date = formatter.parseDateTime(object);
			
					if (date.getMonthOfYear()==02 && date.getDayOfMonth()>28) {
						constraintContext.buildConstraintViolationWithTemplate("{DateImpossible}").addConstraintViolation();

						return false;
					}else{
						return true;
					
				}

			}
			catch(java.lang.IllegalArgumentException e){
				return false;
			}


		}
	}
}



