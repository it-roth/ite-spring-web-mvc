package co.istad.restapi.service.impl;

import co.istad.restapi.domain.Coffee;
import co.istad.restapi.dto.CoffeeResponse;
import co.istad.restapi.dto.CreateCoffeeRequest;
import co.istad.restapi.repository.CoffeeRepository;
import co.istad.restapi.service.CoffeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CoffeeServiceImpl implements CoffeeService {
    private final List<Coffee> coffeeRepository;
//    private final List<Coffee> BeanCoffee;
    public CoffeeServiceImpl(List<Coffee> coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<CoffeeResponse> getCoffee() {
        List<Coffee> coffees = coffeeRepository;
        return coffees.stream()
                .filter((a)->a.getId() > 1 )
                .map((a)->new CoffeeResponse(a.getName(),a.getDescription(), a.getPrice())
        ).toList();
    }

    @Override
//    public CoffeeResponse getCoffeeById(Integer id) {
//        return coffeeRepository.BeanCoffee().stream().filter(a->a.getId().equals(id)).
//                map(a->new CoffeeResponse(a.getName(),a.getDescription(),a.getPrice())).
//                findFirst().orElseThrow(()->new RuntimeException("Id not found!"));
//    }
    public CoffeeResponse getCoffeeById(Integer id){
        return coffeeRepository.stream()
                .filter(coffee -> coffee.getId().equals(id))
                .map(coffee -> new CoffeeResponse(coffee.getName(),coffee.getDescription(),coffee.getPrice()))
                .findFirst()
                .orElseThrow();
    }
    @Override
    public List<CoffeeResponse> getCoffeeByName(String name , Double price) {
        return coffeeRepository
                .stream()
                .filter(a->a.getName().toLowerCase().contains(name.toLowerCase().trim())||a.getPrice().equals(price))
                .map(a->new CoffeeResponse(a.getName(),a.getDescription(),a.getPrice())).toList();
    }
    @Override
    public CoffeeResponse createCoffee(CreateCoffeeRequest CreateCoffeeRequest) {
        Coffee coffee = new Coffee();
        coffee.setId(new Random().nextInt(6));
        coffee.setName(CreateCoffeeRequest.name());
        coffee.setDescription(CreateCoffeeRequest.description());
        coffee.setPrice(CreateCoffeeRequest.price());
        coffeeRepository.add(coffee);
        return new CoffeeResponse(coffee.getName(),coffee.getDescription(),coffee.getPrice());

    }
}
