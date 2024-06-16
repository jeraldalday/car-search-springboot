package com.ag.pst.codeexercise.carsearch.service;

import com.ag.pst.codeexercise.carsearch.model.Car;
import com.ag.pst.codeexercise.carsearch.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;
    private List<Car> allCars;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car(1L, 4.5, 1500, 220, "Red");
        Car car2 = new Car(2L, 4.0, 1300, 200, "Blue");

        allCars = Arrays.asList(car1, car2);

        // Mocking the behavior of carRepository.findAll()
        when(carRepository.findAll()).thenReturn(allCars);

        carService = new CarService(carRepository);
    }

    @Test
    public void testSearchCars_WithValidCriteria() {
        // Testing searchCars method with specific criteria
        List<Car> searchedCars = carService.searchCars(4.5, 1500.0, 220.0, "Red");

        assertEquals(1, searchedCars.size());
        assertEquals(allCars.get(0), searchedCars.get(0));
    }


    @Test
    public void testSearchCars_WithInvalidCriteria() {
        // Testing searchCars method with invalid criteria
        List<Car> searchedCars = carService.searchCars(5.0, 1200.0, 180.0, "Green");

        assertEquals(0, searchedCars.size());
    }

}
