package com.pabloreyes.clinic.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "turn")
public class Turn {


    @Id
    @SequenceGenerator(name = "turn_sec", sequenceName = "turn_sec",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turn_sec")
    @Column(name = "id_turn")
    private Long idTurn;
    @Column(name = "date")
    private Timestamp date;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
