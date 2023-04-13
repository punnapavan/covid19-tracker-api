package com.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.covid.entity.PatientProfile;

public interface PatientRepository extends JpaRepository<PatientProfile,String>{

}
