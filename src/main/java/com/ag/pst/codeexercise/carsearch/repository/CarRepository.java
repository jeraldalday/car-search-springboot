package com.ag.pst.codeexercise.carsearch.repository;

import com.ag.pst.codeexercise.carsearch.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Car entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}
