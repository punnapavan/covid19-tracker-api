package com.covid.model;

import java.io.Serializable;
import java.util.List;

import com.covid.entity.PatientProfile;

import lombok.Data;

@Data
public class PatientProfileResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean status;
	private String token;
	private PatientProfile patientProfile;
	List<PatientProfile> patientProfiles;

}
