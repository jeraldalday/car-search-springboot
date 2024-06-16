package com.ag.pst.codeexercise.carsearch.service;

import com.ag.pst.codeexercise.carsearch.model.Car;
import com.ag.pst.codeexercise.carsearch.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling car-related operations.
 */
@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    /**
     * Searches for cars based on the provided criteria.
     *
     * @param length   The length of the car to search for (optional).
     * @param weight   The weight of the car to search for (optional).
     * @param velocity The velocity of the car to search for (optional).
     * @param color    The color of the car to search for (optional).
     * @return A list of cars matching the specified criteria.
     */
    public List<Car> searchCars(Double length, Double weight, Double velocity, String color) {
        return carRepository.findAll().stream()
                .filter(car -> (length == null || car.getLength() == length) &&
                        (weight == null || car.getWeight() == weight) &&
                        (velocity == null || car.getVelocity() == velocity) &&
                        (color == null || car.getColor().equalsIgnoreCase(color)))
                .toList();
    }
}
