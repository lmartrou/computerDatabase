package com.excilys.computerDatabase.om;

import org.springframework.stereotype.Component;

@Component
public class Log {

	private Long computerId;
	private String computerName;
	private String operation;
	public Long getComputerId() {
		return computerId;
	}
	public void setComputerId(Long computerId) {
		this.computerId = computerId;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String opération) {
		this.operation = opération;
	}
	
	
}
