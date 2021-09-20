package com.stomatology.repository;

import com.stomatology.entity.MedicalService;
import com.stomatology.entity.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {

    List<MedicalService> findDistinctByDoctorsContaining(Doctor doctor);
}
