package com.pabloreyes.clinic.model.dto;

import com.pabloreyes.clinic.model.entity.Dentist;
import com.pabloreyes.clinic.model.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnDTO {

    private Long idTurn;
    private Timestamp date;
    private Dentist dentist;
    private Patient patient;
}
