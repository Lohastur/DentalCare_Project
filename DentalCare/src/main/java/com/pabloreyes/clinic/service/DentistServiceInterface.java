package com.pabloreyes.clinic.service;

import com.pabloreyes.clinic.model.dto.DentistDTO;
import com.pabloreyes.clinic.model.entity.Dentist;

import java.util.List;

public interface DentistServiceInterface {


    DentistDTO createDentist(Dentist dentist);
    DentistDTO readDentistById(Long id);
    DentistDTO readDentistByLicensePlate(String licencePlate);
    List<DentistDTO> readAllDentist();
    DentistDTO updateDentist(Dentist dentist);
    DentistDTO deleteDentistById(Long id);
}
