package com.covid.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.covid.controller.PatientController;
import com.covid.entity.PatientProfile;
import com.covid.exception.DataValidationException;
import com.covid.model.PatientProfileRequest;
import com.covid.model.PatientProfileResponse;
import com.covid.model.SummaryResponse;
import com.covid.model.TestReportRequest;
import com.covid.repository.PatientRepository;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {

	private static final Logger log = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientRepository patientRepo;

	@Override
	public PatientProfileResponse addPatientDetails(PatientProfileRequest profileRequest)
			throws DataValidationException {

		try {

			if (StringUtils.isEmpty(profileRequest.getAadhar_no())) {
				throw new DataValidationException("Aadhar Number error message.", null, HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(profileRequest.getFirst_name())) {
				throw new DataValidationException("First Name error message.", null, HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(profileRequest.getLast_name())) {
				throw new DataValidationException("Last name error message.", null, HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(profileRequest.getGender())) {
				throw new DataValidationException("Gender error message.", null, HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(profileRequest.getMob_no())) {
				throw new DataValidationException("Mobile Number error message.", null, HttpStatus.NOT_FOUND);
			}

			if (profileRequest.getAge() == 0) {
				throw new DataValidationException("Age error message ", null, HttpStatus.NOT_FOUND);
			}

			if (profileRequest.getAdmit_date() == null) {
				throw new DataValidationException("Test date error message ", null, HttpStatus.NOT_FOUND);
			}

			PatientProfile patientProfile = new PatientProfile();

			patientProfile.setAadhar_no(profileRequest.getAadhar_no());
			patientProfile.setFirst_name(profileRequest.getFirst_name());
			patientProfile.setLast_name(profileRequest.getLast_name());
			patientProfile.setGender(profileRequest.getGender());
			patientProfile.setAge(profileRequest.getAge());
			patientProfile.setMob_no(profileRequest.getMob_no());
			patientProfile.setAdmit_date(profileRequest.getAdmit_date());

			patientRepo.save(patientProfile);

			PatientProfile patient = patientRepo.findById(profileRequest.getAadhar_no()).get();
			PatientProfileResponse response = new PatientProfileResponse();
			response.setPatientProfile(patient);
			return response;

		} catch (DataValidationException e) {
			log.error("There are some error in adding patient profile.", e);
			throw e;
		}

	}

	@Override
	public PatientProfileResponse addPatientReport(TestReportRequest reportRequest) throws DataValidationException {
		try {
			if (StringUtils.isEmpty(reportRequest.getAadhar_no())) {
				throw new DataValidationException("Aadhar Number error message.", null, HttpStatus.NOT_FOUND);
			}

			if (!patientRepo.existsById(reportRequest.getAadhar_no())) {
				throw new DataValidationException("Patient Record not found!", null, HttpStatus.NOT_FOUND);
			}

			PatientProfile patient = patientRepo.findById(reportRequest.getAadhar_no()).get();

			patient.setReport(reportRequest.getReport());
			patient.setDischarged(reportRequest.getDischarged());

			patientRepo.save(patient);

			PatientProfile updatedpatient = patientRepo.findById(reportRequest.getAadhar_no()).get();

			PatientProfileResponse response = new PatientProfileResponse();

			response.setPatientProfile(updatedpatient);
			return response;
		} catch (DataValidationException e) {
			log.error("There are some error in adding patient report.", e);
			throw e;
		}

	}

	@Override
	public PatientProfileResponse getPatientReport(String aadhar_no) throws DataValidationException {

		if (aadhar_no.isEmpty()) {
			throw new DataValidationException("Please enter your Aadhar no.", null, HttpStatus.NOT_FOUND);
		}

		else if (!patientRepo.existsById(aadhar_no)) {
			throw new DataValidationException("Patient Record not found!", null, HttpStatus.NOT_FOUND);
		}

		PatientProfile patient = patientRepo.findById(aadhar_no).get();

		PatientProfileResponse response = new PatientProfileResponse();
		response.setPatientProfile(patient);

		return response;
	}

	@Override
	public PatientProfileResponse getAllPatients() {

		List<PatientProfile> list = patientRepo.findAll();

		PatientProfileResponse response = new PatientProfileResponse();
		response.setPatientProfiles(list);
		return response;
	}

	@Override
	public SummaryResponse getAllSummary() {

		List<PatientProfile> list = patientRepo.findAll();

		int totalPatients = list.size();
		String s1 = "positive";
		String s2 = "discharged";

		int pos = 0;
		int neg = 0;
		int dis = 0;
		int deads = 0;

		for (int i = 0; i < list.size(); i++) {
			try {
				if (list.get(i).getDischarged().equalsIgnoreCase(s2)) {
					dis++;
				} else {
					deads++;
				}
			} catch (NullPointerException e) {
				System.out.println("NullPointerException thrown!");

			} finally {

			}
		}

		for (int i = 0; i < list.size(); i++) {
			try {
				if (list.get(i).getReport().equalsIgnoreCase(s1)) {
					pos++;
				} else {
					neg++;
				}
			} catch (NullPointerException e) {
				System.out.println("NullPointerException thrown!");

			} finally {

			}

		}

		/*
		 * 
		 * for(PatientProfile u :list) {
		 * 
		 * System.out.println(u.getAge());
		 * 
		 * if(u.getReport().equalsIgnoreCase(s1)) { pos++; } else { neg++; }
		 * 
		 * }
		 * 
		 * 
		 * List<PatientProfile> list1 = patientRepo.findAll(); try { for(PatientProfile
		 * u1 :list1) {
		 * 
		 * System.out.println(u1.getFirst_name()); if(u1.getDischarged().equals(s2)) {
		 * dis++; System.out.println("dis:"+dis); } else { deads++; } }
		 * }catch(NullPointerException e) {
		 * System.out.println("NullPointerException thrown!"); }
		 * 
		 */

		SummaryResponse response = new SummaryResponse();

		response.setTotal(totalPatients);
		response.setPositive(pos);
		response.setNegative(neg);
		response.setDischaged(dis);
		response.setDeads(deads);
		return response;
	}

}
