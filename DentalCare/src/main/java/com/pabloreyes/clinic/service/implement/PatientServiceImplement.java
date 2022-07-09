package com.pabloreyes.clinic.service.implement;

import com.pabloreyes.clinic.model.dto.PatientDTO;
import com.pabloreyes.clinic.model.entity.Patient;
import com.pabloreyes.clinic.repository.PatientRepository;
import com.pabloreyes.clinic.service.PatientServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplement implements PatientServiceInterface {

    final static Logger LOGGER = Logger.getLogger(PatientServiceImplement.class);
    private PatientRepository patientRepository;
    private ObjectMapper mapper;

    @Autowired
    public PatientServiceImplement(PatientRepository patientRepository, ObjectMapper mapper) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    //----------------------BEGIN. IMPLEMENTED METHODS---------------------------
    @Override
    public PatientDTO createPatient(Patient patient) {
//        Patient patient = mapper.convertValue(patientDTO, Patient.class);
        LOGGER.info("Start: -createPatient- in <<PatientServiceImplement>>");
        PatientDTO createdPatientDTO = new PatientDTO();
        PatientDTO patientDTOCheckDni = new PatientDTO();

        if (patient.getDni() != null){
            patientDTOCheckDni = readPatientByDni(patient.getDni());

            if (patient.getIdPatient() != null || patientDTOCheckDni.getIdPatient() == null){
                createdPatientDTO = mapper.convertValue(patientRepository.save(patient), PatientDTO.class);
                LOGGER.info("Created successfully");
            }else {

                LOGGER.error("Something went wrong when executing -createPatient- DNI already exist");
            }
        }else {

            LOGGER.error("Something went wrong when executing -createPatient- NULL DNI");
        }

        LOGGER.info("End: -createPatient- in <<PatientServiceImplement>>");
        return createdPatientDTO;
    }

    @Override
    public PatientDTO readPatientById(Long id) {
        LOGGER.info("Start: -readPatientById- in <<PatientServiceImplement>>");
        Optional<Patient> patient = Optional.of(new Patient());
        PatientDTO readPatientDTO = new PatientDTO();

        if (id != null){
            patient = patientRepository.findById(id);

            if (patient.isPresent()){
                readPatientDTO = mapper.convertValue(patient, PatientDTO.class);
            }
        }else {

            LOGGER.error("Something went wrong when executing -readPatientById- NULL ID");
        }

        LOGGER.info("End: -readPatientById- in <<PatientServiceImplement>>");
        return readPatientDTO;
    }

    @Override
    public PatientDTO readPatientByDni(Long dni) {
        LOGGER.info("Start: -readPatientByDni- in <<PatientServiceImplement>>");
        Optional<Patient> patient = Optional.of(new Patient());
        PatientDTO readPatientDTO = new PatientDTO();

        if (dni != null){
            patient = patientRepository.findByDni(dni);

            if (patient.isPresent()){
                readPatientDTO = mapper.convertValue(patient, PatientDTO.class);
            }
        }else {

            LOGGER.error("Something went wrong when executing -readPatientByDni- NULL DNI");
        }


        LOGGER.info("End: -readPatientByDni- in <<PatientServiceImplement>>");
        return readPatientDTO;
    }

    @Override
    public List<PatientDTO> readAllPatient() {
        LOGGER.info("Start: -readAllPatient- in <<PatientServiceImplement>>");
        List<Patient> patientList = patientRepository.findAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();

        for (Patient patient : patientList){
            patientDTOList.add(mapper.convertValue(patient, PatientDTO.class));
        }

        LOGGER.info("End: -readAllPatient- in <<PatientServiceImplement>>");
        return patientDTOList;
    }

    @Override
    public PatientDTO updatePatient(Patient patient) {
        LOGGER.info("Start: -updatePatient- in <<PatientServiceImplement>>");
        PatientDTO updatedPatientDTO = new PatientDTO();
        PatientDTO patientDTOCheckId = readPatientById(patient.getIdPatient());
        PatientDTO patientDTOCheckDni = readPatientByDni(patient.getDni());

        if(patientDTOCheckId.getIdPatient() != null){
            updatedPatientDTO.setIdPatient(patient.getIdPatient());

            if (patient.getDni() != null){

                if (patientDTOCheckId.getIdPatient() == patientDTOCheckDni.getIdPatient()
                        || patientDTOCheckDni.getIdPatient() == null){

                    updatedPatientDTO = createPatient(patient);
                }else {

                    LOGGER.error("Something went wrong when executing -updatePatient- Already exist DNI");
                }
            }else {

                LOGGER.error("Something went wrong when executing -updatePatient- NULL DNI");
            }
        }else {

            LOGGER.error("Something went wrong when executing -updatePatient- Not found ID");
        }

        LOGGER.info("End: -updatePatient- in <<PatientServiceImplement>>");
        return updatedPatientDTO;
    }

    @Override
    public PatientDTO deletePatientById(Long id) {
        LOGGER.info("Start: -deletePatientById- in <<PatientServiceImplement>>");
        PatientDTO deletedPatientDTO = new PatientDTO();
        PatientDTO patientDTOCheckId = readPatientById(id);

        if (patientDTOCheckId.getIdPatient() != null){
            deletedPatientDTO = patientDTOCheckId;
            patientRepository.deleteById(id);
        }

        LOGGER.info("End: -deletePatientById- in <<PatientServiceImplement>>");
        return deletedPatientDTO;
    }
    //----------------------END. IMPLEMENTED METHODS---------------------------

}
