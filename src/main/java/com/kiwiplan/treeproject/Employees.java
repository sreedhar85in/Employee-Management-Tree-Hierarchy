package com.kiwiplan.treeproject;

import java.util.List;

import lombok.Data;

@Data
public class Employees {

	private long employeeId;
	private String name;
	private long managerId;
	private List<Employees> reportees;

	public Employees(long managerId, long employeeId, String name) {

		this.employeeId = employeeId;
		this.managerId = managerId;
		this.name = name;

	}

}
