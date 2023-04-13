package com.covid.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PatientProfileRequest {

	@NotNull(message = "valid aadhar number required.")
	private String aadhar_no;

	@NotNull(message = "First name of patient required.")
	private String first_name;

	@NotNull(message = "Last name of patient required.")
	private String last_name;

	@NotNull(message = "Add gender of patient.")
	private String gender;

	@NotEmpty(message = "Age of patient is required.")
	private int age;

	@NotNull(message = "Mobile number is required.")
	private String mob_no;

	@NotNull(message = "Date of patient admission is required.")
	private Date admit_date;

	@NotNull(message = "Vaccination report is required.")
	private String vaccinated;

	private String report;

	private String discharged;

}
