package co.istad.restapi.service;

import co.istad.restapi.dto.CoffeeResponse;
import co.istad.restapi.dto.CreateCoffeeRequest;
import co.istad.restapi.dto.UpdateCoffeeRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CoffeeService {

    // 1. My logic is update coffee information by coffee id
    // 2. Expected result is CoffeeResponse
    // 3. Parameter is UpdateCoffeeRequest
    CoffeeResponse updateCoffeeById(Integer id, UpdateCoffeeRequest updateCoffeeRequest);


    // 1. Expected result (return type: void, object, collection, int, ...)
    // 2. Your logic: add single coffee
    // 3. Parameters are used for client submission
    CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest);
    List<CoffeeResponse> searchCoffee(String name, BigDecimal price);
    CoffeeResponse getCoffeeById(Integer id);
    List<CoffeeResponse> getCoffees();
    void deleteCoffeeById(Integer id);
}
