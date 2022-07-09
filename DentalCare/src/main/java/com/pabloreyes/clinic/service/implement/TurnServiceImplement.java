package com.pabloreyes.clinic.service.implement;

import com.pabloreyes.clinic.model.dto.TurnDTO;
import com.pabloreyes.clinic.model.entity.Dentist;
import com.pabloreyes.clinic.model.entity.Patient;
import com.pabloreyes.clinic.model.entity.Turn;
import com.pabloreyes.clinic.repository.DentistRepository;
import com.pabloreyes.clinic.repository.PatientRepository;
import com.pabloreyes.clinic.repository.TurnRepository;
import com.pabloreyes.clinic.service.TurnServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnServiceImplement implements TurnServiceInterface {

    final static Logger LOGGER = Logger.getLogger(TurnServiceImplement.class);
    private TurnRepository turnRepository;
    private PatientRepository patientRepository;
    private DentistRepository dentistRepository;
    private ObjectMapper mapper;

    @Autowired
    public TurnServiceImplement(TurnRepository turnRepository, PatientRepository patientRepository, DentistRepository dentistRepository, ObjectMapper mapper) {
        this.turnRepository = turnRepository;
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
        this.mapper = mapper;
    }



    //----------------------BEGIN. IMPLEMENTED METHODS---------------------------
    @Override
    public TurnDTO createTurn(Turn turn) {
//        Turn turn = mapper.convertValue(turnDTO, Turn.class);
        LOGGER.info("Start: -createTurn- in <<TurnServiceImplement>>");
        TurnDTO createdTurnDTO = new TurnDTO();
        Optional<Dentist> turnDTOCheckDentist = Optional.of(new Dentist());
        Optional<Patient> turnDTOCheckPatient = Optional.of(new Patient());

        if (turn.getDentist().getIdDentist() != null && turn.getPatient().getIdPatient() != null){
            turnDTOCheckDentist = dentistRepository.findById(turn.getDentist().getIdDentist());
            turnDTOCheckPatient = patientRepository.findById(turn.getPatient().getIdPatient());

            if (turnDTOCheckDentist.isPresent() && turnDTOCheckPatient.isPresent()){
                createdTurnDTO = mapper.convertValue(turnRepository.save(turn), TurnDTO.class);
                LOGGER.info("Created successfully");
            }else {

                LOGGER.error("Something went wrong when executing -createTurn- Dentist/Patient do NOT exist");
            }
        }else {

            LOGGER.error("Something went wrong when executing -createTurn- NULL Dentist/Patient");
        }

        LOGGER.info("End: -createTurn- in <<TurnServiceImplement>>");
        return createdTurnDTO;
    }

    @Override
    public TurnDTO readTurnById(Long id) {
        LOGGER.info("Start: -readTurnById- in <<TurnServiceImplement>>");
        Optional<Turn> turn = Optional.of(new Turn());
        TurnDTO readTurnDTO = new TurnDTO();

        if (id != null){
            turn = turnRepository.findById(id);

            if (turn.isPresent()){
                readTurnDTO = mapper.convertValue(turn, TurnDTO.class);
            }
        }else {

            LOGGER.error("Something went wrong when executing -readTurnById- NULL id");
        }

        LOGGER.info("End: -readTurnById- in <<TurnServiceImplement>>");
        return readTurnDTO;
    }

    @Override
    public List<TurnDTO> readAllTurn() {
        LOGGER.info("Start: -readAllTurn- in <<TurnServiceImplement>>");
        List<Turn> turnList = turnRepository.findAll();
        List<TurnDTO> turnDTOList = new ArrayList<>();

        for (Turn turn : turnList){
            turnDTOList.add(mapper.convertValue(turn, TurnDTO.class));
        }

        LOGGER.info("End: -readAllTurn- in <<TurnServiceImplement>>");
        return turnDTOList;
    }

    @Override
    public List<TurnDTO> readAllTurnByDentistId(Long id) {
        LOGGER.info("Start: -readAllTurnByDentistId- in <<TurnServiceImplement>>");
        Optional<Dentist> readDentist = Optional.of(new Dentist());
        Dentist dentist = new Dentist();

        List<Turn> turnList = new ArrayList<>();
        List<TurnDTO> turnDTOList = new ArrayList<>();


        if (id != null){
            readDentist = dentistRepository.findById(id);

            if (readDentist.isPresent()){
                dentist.setIdDentist(readDentist.get().getIdDentist());
                dentist.setLicensePlate(readDentist.get().getLicensePlate());
                dentist.setName(readDentist.get().getName());
                dentist.setSurname(readDentist.get().getSurname());

                turnList = turnRepository.findByDentist(dentist);

                for (Turn turn : turnList){
                    turnDTOList.add(mapper.convertValue(turn, TurnDTO.class));
                }
            }
        }else {

            LOGGER.error("Something went wrong when executing -readAllTurnByDentistId- NULL Dentist ID");
        }

        LOGGER.info("End: -readAllTurnByDentistId- in <<TurnServiceImplement>>");
        return turnDTOList;
    }

    @Override
    public List<TurnDTO> readAllTurnByPatientId(Long id) {
        LOGGER.info("Start: -readAllTurnByPatientId- in <<TurnServiceImplement>>");
        Optional<Patient> readPatient = Optional.of(new Patient());
        Patient patient = new Patient();

        List<Turn> turnList = new ArrayList<>();
        List<TurnDTO> turnDTOList = new ArrayList<>();

        if (id != null){
            readPatient = patientRepository.findById(id);

            if (readPatient.isPresent()){
                patient.setIdPatient(readPatient.get().getIdPatient());
                patient.setDni(readPatient.get().getDni());
                patient.setName(readPatient.get().getName());
                patient.setSurname(readPatient.get().getSurname());
                patient.setRegistrationDate(readPatient.get().getRegistrationDate());

                turnList = turnRepository.findByPatient(patient);

                for (Turn turn : turnList){
                    turnDTOList.add(mapper.convertValue(turn, TurnDTO.class));
                }
            }
        }else {

            LOGGER.error("Something went wrong when executing -readAllTurnByPatientId- NULL Patient ID");
        }

        LOGGER.info("End: -readAllTurnByPatientId- in <<TurnServiceImplement>>");
        return turnDTOList;
    }

    @Override
    public TurnDTO updateTurn(Turn turn) {
        LOGGER.info("Start: -updateTurn- in <<TurnServiceImplement>>");
        TurnDTO updatedTurnDTO = new TurnDTO();
        TurnDTO turnDTOCheckId = new TurnDTO();
        TurnDTO turnDTOCheckCreate = new TurnDTO();

        if (turn.getIdTurn() != null){
            updatedTurnDTO.setIdTurn(turn.getIdTurn());
            turnDTOCheckId = readTurnById(turn.getIdTurn());

            if (turnDTOCheckId.getIdTurn() != null){
                turnDTOCheckCreate = createTurn(turn);

                if (turnDTOCheckCreate.getIdTurn() != null){

                    updatedTurnDTO = turnDTOCheckCreate;
                }
            }else {

                LOGGER.error("Something went wrong when executing -updateTurn- TURN ID Not Found");
            }
        }else {

            LOGGER.error("Something went wrong when executing -updateTurn- NULL TURN ID");
        }


        LOGGER.info("End: -updateTurn- in <<TurnServiceImplement>>");
        return updatedTurnDTO;
    }

    @Override
    public TurnDTO deleteTurnById(Long id) {
        LOGGER.info("Start: -deleteTurnById- in <<TurnServiceImplement>>");
        TurnDTO deletedTurnDTO = new TurnDTO();
        TurnDTO turnDTOCheckId = readTurnById(id);

        if (turnDTOCheckId.getIdTurn() != null){
            deletedTurnDTO = turnDTOCheckId;
            turnRepository.deleteById(id);
        }

        LOGGER.info("End: -deleteTurnById- in <<TurnServiceImplement>>");
        return deletedTurnDTO;
    }
    //----------------------END. IMPLEMENTED METHODS---------------------------

}
