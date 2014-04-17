package com.excilys.computerDataBase.om;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="log")
public class Log {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	@Column(name="computer_id")
	private Long computerId;
	@Column(name="computer_name")
	private String computerName;
	@Column(name="operation")
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
