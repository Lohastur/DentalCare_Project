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
public class AddressDTO {

    private Long idAddress;
    private Integer street;
    private Integer number;
    private String location;
    private String province;
}
