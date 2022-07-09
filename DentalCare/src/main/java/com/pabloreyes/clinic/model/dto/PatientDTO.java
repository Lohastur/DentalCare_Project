package com.pabloreyes.clinic.model.dto;

import com.pabloreyes.clinic.model.entity.Address;
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
public class PatientDTO {


    private Long idPatient;
    private Long dni;
    private String name;
    private String surname;
    private Timestamp registrationDate;
    private Address address;

}
