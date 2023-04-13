package com.covid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.exception.DataValidationException;
import com.covid.model.BaseResponse;
import com.covid.model.PatientProfileRequest;
import com.covid.model.PatientProfileResponse;
import com.covid.model.SummaryResponse;
import com.covid.model.TestReportRequest;
import com.covid.service.PatientProfileService;
import com.covid.utils.CommonUtils;

@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	private PatientProfileService patientProfileService;

	private static final Logger log = LoggerFactory.getLogger(PatientController.class);

	@PostMapping("/addPatientDetails")
	public ResponseEntity<?> addDetails(@RequestBody PatientProfileRequest profileRequest) {

		try {
			PatientProfileResponse response = patientProfileService.addPatientDetails(profileRequest);
			response.setSuccess(true);
			return ResponseEntity.ok(response);
		} catch (DataValidationException e) {
			BaseResponse response = CommonUtils.createBaseResponse(false, e.getMessage(), e.getErrorCode());
			return ResponseEntity.status(e.getStatusCode()).body(response);
		}

	}

	// For hospital use

	@PostMapping("/addPatientReport")
	public ResponseEntity<?> testReport(@RequestBody TestReportRequest reportRequest) {
		try {
			PatientProfileResponse response = patientProfileService.addPatientReport(reportRequest);
			response.setSuccess(true);
			return ResponseEntity.ok(response);
		} catch (DataValidationException e) {
			BaseResponse response = CommonUtils.createBaseResponse(false, e.getMessage(), e.getErrorCode());
			return ResponseEntity.status(e.getStatusCode()).body(response);
		}
	}

	// For Patient to see reports

	@GetMapping("getPatientReport")
	public ResponseEntity<?> getMyReport(@RequestParam(required = false) String aadharNo) {
		try {
			PatientProfileResponse response = patientProfileService.getPatientReport(aadharNo);
			response.setStatus(true);
			return ResponseEntity.ok(response);
		} catch (DataValidationException e) {
			BaseResponse response = CommonUtils.createBaseResponse(false, e.getMessage(), e.getErrorCode());
			return ResponseEntity.status(e.getStatusCode()).body(response);
		}

	}

	@GetMapping("getAllReports")
	public ResponseEntity<?> getAllPatientHistory() {
		try {
			PatientProfileResponse response = patientProfileService.getAllPatients();
			response.setStatus(true);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@GetMapping("/getSummary")
	public ResponseEntity<?> getSummaryReport() {

		try {
			SummaryResponse response = patientProfileService.getAllSummary();
			response.setStatus(true);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

}
