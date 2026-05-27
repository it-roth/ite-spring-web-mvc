package co.istad.restapi.service;

import co.istad.restapi.dto.CoffeeResponse;
import java.util.List;

public interface CoffeeService {
    List<CoffeeResponse> getCoffee();
}
