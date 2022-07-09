package com.pabloreyes.clinic.service;

import com.pabloreyes.clinic.model.dto.PatientDTO;
import com.pabloreyes.clinic.model.entity.Patient;

import java.util.List;

public interface PatientServiceInterface {


    PatientDTO createPatient(Patient patient);
    PatientDTO readPatientById(Long id);
    PatientDTO readPatientByDni(Long dni);
    List<PatientDTO> readAllPatient();
    PatientDTO updatePatient(Patient patient);
    PatientDTO deletePatientById(Long id);
}
