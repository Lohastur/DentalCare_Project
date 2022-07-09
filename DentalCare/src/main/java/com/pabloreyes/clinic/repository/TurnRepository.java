package com.pabloreyes.clinic.repository;

import com.pabloreyes.clinic.model.entity.Dentist;
import com.pabloreyes.clinic.model.entity.Patient;
import com.pabloreyes.clinic.model.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

    List<Turn> findByDentist(Dentist dentist);
    List<Turn> findByPatient(Patient patient);
}
