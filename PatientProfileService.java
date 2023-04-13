package com.covid.service;

import com.covid.exception.DataValidationException;
import com.covid.model.PatientProfileRequest;
import com.covid.model.PatientProfileResponse;
import com.covid.model.SummaryResponse;
import com.covid.model.TestReportRequest;


public interface PatientProfileService {

	PatientProfileResponse addPatientDetails(PatientProfileRequest profileRequest) throws DataValidationException;

	PatientProfileResponse addPatientReport(TestReportRequest reportRequest) throws DataValidationException;

	PatientProfileResponse getPatientReport(String aadhar_no) throws DataValidationException;

	PatientProfileResponse getAllPatients();

	SummaryResponse getAllSummary();

}
