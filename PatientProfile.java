package com.covid.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Patient-Profile")
public class PatientProfile {

	@Id
	@Column(name = "Aadhar_No")
	private String aadhar_no;
	
	@Column(name= "First_Name")
	private String first_name;
	
	@Column(name= "Last_Name")
	private String last_name;
	
	@Column(name= "Gender")
	private String gender;
	
	@Column(name= "Age")
	private int age;
	
	@Column(name= "Mob_No")
	private String mob_no;
	
	@Column(name= "Admitted_Date")
	private Date admit_date;
	
	@Column(name= "Patient_Report")	
	private String report;
	
	@Column(name="Discharged")
	private String discharged;	
	
	@Column(name="Vaccination")
	private String vaccinated;
	
	
//	LocalDate ld = admit_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	
//	LocalDate tws = ld.plusMonths(2);
	
}
