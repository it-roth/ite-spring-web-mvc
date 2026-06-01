package co.istad.restapi.service;

import co.istad.restapi.dto.CoffeeResponse;
import co.istad.restapi.dto.CreateCoffeeRequest;

import java.util.List;

public interface CoffeeService {

//    1. Expected result (return type: void, abstract,)
    List<CoffeeResponse> getCoffee();
    CoffeeResponse getCoffeeById(Integer id);
    List<CoffeeResponse> getCoffeeByName(String name, Double price);
    CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest);
}
