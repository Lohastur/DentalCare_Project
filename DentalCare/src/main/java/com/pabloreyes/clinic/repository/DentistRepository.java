package com.pabloreyes.clinic.repository;

import com.pabloreyes.clinic.model.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    Optional<Dentist> findByLicensePlate(String licensePlate);
}
