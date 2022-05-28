package com.comparator.comparator.Repository;

import com.comparator.comparator.Entity.CalculationResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<CalculationResult, Integer> {

}