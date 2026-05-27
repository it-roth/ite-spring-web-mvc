package co.istad.restapi.service.impl;

import co.istad.restapi.domain.Coffee;
import co.istad.restapi.dto.CoffeeResponse;
import co.istad.restapi.repository.CoffeeRepository;
import co.istad.restapi.service.CoffeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeServiceImpl implements CoffeeService {
    private final CoffeeRepository coffeeRepository;
    public CoffeeServiceImpl(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<CoffeeResponse> getCoffee() {
        List<Coffee> coffees = coffeeRepository.BeanCoffee();
        return coffees.stream()
                .filter((a)->a.getId() > 1 )
                .map((a)->new CoffeeResponse(a.getName(),a.getDescription())
        ).toList();
    }
}
