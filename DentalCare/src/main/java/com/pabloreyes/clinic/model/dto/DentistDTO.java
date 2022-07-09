package com.pabloreyes.clinic.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDTO {

    private Long idDentist;
    private String licensePlate;
    private String name;
    private String surname;
}
