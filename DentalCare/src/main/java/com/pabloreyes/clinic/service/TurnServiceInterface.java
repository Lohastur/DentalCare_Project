package com.pabloreyes.clinic.service;

import com.pabloreyes.clinic.model.dto.TurnDTO;
import com.pabloreyes.clinic.model.entity.Turn;

import java.util.List;

public interface TurnServiceInterface {


    TurnDTO createTurn(Turn turn);
    TurnDTO readTurnById(Long id);
    List<TurnDTO> readAllTurn();
    List<TurnDTO> readAllTurnByDentistId(Long id);
    List<TurnDTO> readAllTurnByPatientId(Long id);
    TurnDTO updateTurn(Turn turn);
    TurnDTO deleteTurnById(Long id);
}
