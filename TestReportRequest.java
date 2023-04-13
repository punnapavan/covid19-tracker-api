package com.covid.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TestReportRequest {
	
	@NotNull(message= "valid aadhar number required.")
	private String aadhar_no;
	
	private String report;
	private String discharged;

}
