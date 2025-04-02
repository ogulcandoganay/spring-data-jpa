package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Adress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdressRepository extends JpaRepository<Adress, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Adress a WHERE a.country = :country")
    void deleteByCountry(@Param("country") String country);



}
