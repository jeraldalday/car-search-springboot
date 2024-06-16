package com.ag.pst.codeexercise.carsearch.controller;

import com.ag.pst.codeexercise.carsearch.model.Car;
import com.ag.pst.codeexercise.carsearch.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    List<Car> cars = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new CarController(carService)).build();

        Car car1 = new Car();
        car1.setId(1L);
        car1.setLength(4.5);
        car1.setWeight(1500);
        car1.setVelocity(220);
        car1.setColor("Red");

        Car car2 = new Car();
        car2.setId(2L);
        car2.setLength(4.0);
        car2.setWeight(1300);
        car2.setVelocity(200);
        car2.setColor("Blue");

        cars = Arrays.asList(car1, car2);
    }

    @Test
    public void testSearchCars() throws Exception {
        given(carService.searchCars(null, null, null, null)).willReturn(cars);

        mockMvc.perform(get("/cars/search")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].color", is("Red")))
                .andExpect(jsonPath("$[1].color", is("Blue")));
    }

    @Test
    public void testSearchCarsAsXml() throws Exception {
        given(carService.searchCars(null, null, null, null)).willReturn(cars);

        mockMvc.perform(get("/cars/search/xml")
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", containsString("attachment; filename=cars.xml")))
                .andExpect(xpath("cars/car[1]/color").string("Red"))
                .andExpect(xpath("cars/car[2]/color").string("Blue"));
    }

    @Test
    public void testSearchCars_WithNullResult() throws Exception {
        // Set up the CarService mock to return null
        given(carService.searchCars(null, null, null, null)).willReturn(null);

        // Perform the GET request to the controller endpoint
        mockMvc.perform(get("/cars/search")
                        .contentType(MediaType.APPLICATION_JSON))
                // Verify that the response status is 200 (OK)
                .andExpect(status().isOk())
                // Verify that the response body is empty
                .andExpect(content().string(isEmptyOrNullString()));
    }
}