package com.ag.pst.codeexercise.carsearch.controller;

import com.ag.pst.codeexercise.carsearch.model.Car;
import com.ag.pst.codeexercise.carsearch.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.List;

/**
 * Controller class for handling car-related HTTP requests.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    /**
     * Endpoint for searching cars based on optional query parameters.
     *
     * @param length   The length of the car (optional).
     * @param weight   The weight of the car (optional).
     * @param velocity The velocity of the car (optional).
     * @param color    The color of the car (optional).
     * @return ResponseEntity containing a list of cars and an HTTP status code.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(@RequestParam(required = false) Double length,
                                                @RequestParam(required = false) Double weight,
                                                @RequestParam(required = false) Double velocity,
                                                @RequestParam(required = false) String color) {
        List<Car> cars = carService.searchCars(length, weight, velocity, color);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * Endpoint for searching cars and returning the result in XML format.
     *
     * @param length   The length of the car (optional).
     * @param weight   The weight of the car (optional).
     * @param velocity The velocity of the car (optional).
     * @param color    The color of the car (optional).
     * @return ResponseEntity containing the XML representation of cars and HTTP headers.
     */
    @GetMapping("/search/xml")
    public ResponseEntity<String> searchCarsAsXml(@RequestParam(required = false) Double length,
                                                  @RequestParam(required = false) Double weight,
                                                  @RequestParam(required = false) Double velocity,
                                                  @RequestParam(required = false) String color) {
        List<Car> cars = carService.searchCars(length, weight, velocity, color);

        try {
            JAXBContext context = JAXBContext.newInstance(CarListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            CarListWrapper carListWrapper = new CarListWrapper();
            carListWrapper.setCars(cars);

            StringWriter sw = new StringWriter();
            marshaller.marshal(carListWrapper, sw);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=cars.xml");

            return new ResponseEntity<>(sw.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Wrapper class for marshalling cars into XML format.
     */
    @XmlRootElement(name = "cars")
    public static class CarListWrapper {

        private List<Car> cars;

        @XmlElement(name = "car")
        public List<Car> getCars() {
            return cars;
        }

        public void setCars(List<Car> cars) {
            this.cars = cars;
        }
    }
}
