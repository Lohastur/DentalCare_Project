package com.pabloreyes.clinic.service.implement;

import com.pabloreyes.clinic.model.dto.DentistDTO;
import com.pabloreyes.clinic.model.entity.Dentist;
import com.pabloreyes.clinic.repository.DentistRepository;
import com.pabloreyes.clinic.service.DentistServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImplement implements DentistServiceInterface {

    final static Logger LOGGER = Logger.getLogger(DentistServiceImplement.class);
    private DentistRepository dentistRepository;
    private ObjectMapper mapper;

    @Autowired
    public DentistServiceImplement(DentistRepository dentistRepository, ObjectMapper mapper) {
        this.dentistRepository = dentistRepository;
        this.mapper = mapper;
    }


    //----------------------BEGIN. IMPLEMENTED METHODS---------------------------

    @Override
    public DentistDTO createDentist(Dentist dentist) {
//        Dentist dentist = mapper.convertValue(dentistDTO, Dentist.class);
        LOGGER.info("Start: -createDentist- in <<DentistServiceImplement>>");
        DentistDTO createdDentistDTO = new DentistDTO();
        DentistDTO dentistDTOCheckLicense = new DentistDTO();

        if (dentist.getLicensePlate() != null){
            dentistDTOCheckLicense = readDentistByLicensePlate(dentist.getLicensePlate());

            if (dentist.getIdDentist() != null || dentistDTOCheckLicense.getIdDentist() == null){
                createdDentistDTO = mapper.convertValue(dentistRepository.save(dentist), DentistDTO.class);
                LOGGER.info("Created successfully");
            }else {

                LOGGER.error("Something went wrong when executing -createDentist- License already exist");
            }
        }else {

            LOGGER.error("Something went wrong when executing -createDentist- License can not be Null");
        }

        LOGGER.info("End: -createDentist- in <<DentistServiceImplement>>");
        return createdDentistDTO;
    }

    @Override
    public DentistDTO readDentistById(Long id) {
        LOGGER.info("Start: -readDentistById- in <<DentistServiceImplement>>");
        Optional<Dentist> dentist;
        DentistDTO readDentistDTO = new DentistDTO();

        if (id != null){
            dentist = dentistRepository.findById(id);

            if (dentist.isPresent()){

                readDentistDTO = mapper.convertValue(dentist, DentistDTO.class);
            }
        }else {
            LOGGER.error("NULL ID -readDentistById- in <<DentistServiceImplement>>");
//            throw new IllegalArgumentException("NULL ID");
        }

        LOGGER.info("End: -readDentistById- in <<DentistServiceImplement>>");
        return readDentistDTO;
    }

    @Override
    public DentistDTO readDentistByLicensePlate(String licencePlate) {
        LOGGER.info("Start: -readDentistByLicensePlate- in <<DentistServiceImplement>>");
        Optional<Dentist> dentist = Optional.of(new Dentist());
        DentistDTO readDentistDTO = new DentistDTO();

        if (licencePlate != null){
            dentist = dentistRepository.findByLicensePlate(licencePlate);

            if (dentist.isPresent()){

                readDentistDTO = mapper.convertValue(dentist, DentistDTO.class);
            }
        }

        LOGGER.info("End: -readDentistByLicensePlate- in <<DentistServiceImplement>>");
        return readDentistDTO;
    }

    @Override
    public List<DentistDTO> readAllDentist() {
        LOGGER.info("Start: -readAllDentist- in <<DentistServiceImplement>>");
        List<Dentist> dentistList = dentistRepository.findAll();
        List<DentistDTO> dentistDTOList = new ArrayList<>();

        for (Dentist dentist : dentistList){

            dentistDTOList.add(mapper.convertValue(dentist, DentistDTO.class));
        }

        LOGGER.info("End: -readAllDentist- in <<DentistServiceImplement>>");
        return dentistDTOList;
    }

    @Override
    public DentistDTO updateDentist(Dentist dentist) {
        LOGGER.info("Start: -updateDentist- in <<DentistServiceImplement>>");
        DentistDTO updatedDentistDTO = new DentistDTO();
        DentistDTO dentistDTOCheckId = readDentistById(dentist.getIdDentist());
        DentistDTO dentistDTOCheckLicense = readDentistByLicensePlate(dentist.getLicensePlate());


        if (dentistDTOCheckId.getIdDentist() != null){
            updatedDentistDTO.setIdDentist(dentistDTOCheckId.getIdDentist());

            if (dentist.getLicensePlate() != null){

                if (dentistDTOCheckId.getIdDentist() == dentistDTOCheckLicense.getIdDentist()
                        || dentistDTOCheckLicense.getIdDentist() == null){

                    updatedDentistDTO = createDentist(dentist);
                }else {

                    LOGGER.error("Something went wrong when executing -updateDentist- Already exist License");
                }
            }else {

                LOGGER.error("Something went wrong when executing -updateDentist- NULL License");
            }
        }else {

            LOGGER.error("Something went wrong when executing -updateDentist- Not found ID");
        }

        LOGGER.info("End: -updateDentist- in <<DentistServiceImplement>>");
        return updatedDentistDTO;
    }

    @Override
    public DentistDTO deleteDentistById(Long id) {
        LOGGER.info("Start: -deleteDentistById- in <<DentistServiceImplement>>");
        DentistDTO deletedDentistDTO = new DentistDTO();
        DentistDTO dentistDTOCheckId = readDentistById(id);

        if (dentistDTOCheckId.getIdDentist() != null){
            deletedDentistDTO = dentistDTOCheckId;
            dentistRepository.deleteById(id);
        }

        LOGGER.info("End: -deleteDentistById- in <<DentistServiceImplement>>");
        return deletedDentistDTO;
    }

    //----------------------END. IMPLEMENTED METHODS---------------------------


}
