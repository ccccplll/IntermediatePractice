package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProductionRepository extends JpaRepository<Production, Long> {
    @Query("SELECT p FROM Production p WHERE p.startTime BETWEEN :startTime AND :finishTime")
    List<Production> findProductionsStartingInNextThirtyMinutes(@Param("startTime") Date startTime, @Param("finishTime") Date finishTime);
}
