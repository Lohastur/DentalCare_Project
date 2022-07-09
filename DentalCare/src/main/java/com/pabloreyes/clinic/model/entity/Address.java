package com.pabloreyes.clinic.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(name = "address_sec", sequenceName = "address_sec",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sec")
    @Column(name = "id_address")
    private Long idAddress;
    @Column(name = "street")
    private Integer street;
    @Column(name = "number")
    private Integer number;
    @Column(name = "location")
    private String location;
    @Column(name = "province")
    private String province;


    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private Set<Patient> patientList;
}
