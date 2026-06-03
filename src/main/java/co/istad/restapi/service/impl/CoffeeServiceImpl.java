package co.istad.restapi.service.impl;

import co.istad.restapi.domain.Coffee;
import co.istad.restapi.dto.CoffeeResponse;
import co.istad.restapi.dto.CreateCoffeeRequest;
import co.istad.restapi.dto.UpdateCoffeeRequest;
import co.istad.restapi.repository.CoffeeRepository;
import co.istad.restapi.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeServiceImpl(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public void deleteCoffeeById(Integer id){
        Coffee coffee = coffeeRepository.getCoffees()
                .stream()
                .filter(d->d.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Coffee not found")));
        coffeeRepository.getCoffees().remove(coffee);
    }
    @Override
    public CoffeeResponse updateCoffeeById(Integer id, UpdateCoffeeRequest updateCoffeeRequest) {
        // Validate coffee ID exist or not
        return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst()
                .map(oldCoffee -> {
                    oldCoffee.setName(updateCoffeeRequest.name());
                    oldCoffee.setDescription(updateCoffeeRequest.description());
                    oldCoffee.setPrice(updateCoffeeRequest.price());

                    return oldCoffee;
                })
                .map(newCoffee -> new CoffeeResponse(newCoffee.getId(), newCoffee.getName(), newCoffee.getDescription()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Coffee ID = %d doesn't exist in database", id)));
    }

    @Override
    public CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest) {

        Coffee coffee = new Coffee();
        coffee.setId(new Random().nextInt(999999)); // System Generated Data
        coffee.setName(createCoffeeRequest.name());
        coffee.setDescription(createCoffeeRequest.description());
        coffee.setPrice(createCoffeeRequest.price());

        boolean isExisting = coffeeRepository.getCoffees()
                .stream()
                .anyMatch(c -> c.getId().equals(coffee.getId()));

        if (isExisting) {
            throw new RuntimeException("Coffee ID already exists");
        }

        coffeeRepository.getCoffees().add(coffee);
        return new CoffeeResponse(coffee.getId(), coffee.getName(), coffee.getDescription());
    }


    @Override
    public List<CoffeeResponse> searchCoffee(String name, BigDecimal price) {
        // TODO: Write your logic here...
        return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee ->
                        coffee.getName().toLowerCase().contains(name.toLowerCase()) ||
                                coffee.getPrice().equals(price)
                )
                .map(coffee -> new CoffeeResponse(coffee.getId(), coffee.getName(), coffee.getDescription()))
                .toList();
    }


    @Override
    public CoffeeResponse getCoffeeById(Integer id) {
        return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .map(coffee -> new CoffeeResponse(coffee.getId(), coffee.getName(), coffee.getDescription()))
                .findFirst()
                .orElseThrow();
    }


    @Override
    public List<CoffeeResponse> getCoffees() {
        // Retrieve domain from repository
        // List<Coffee> coffees = coffeeRepository.beanCoffee();
        // Return DTO - CoffeeResponse
        return coffeeRepository.getCoffees().stream()
                //.filter(coffee -> coffee.getId() > 3)
                .map(coffee -> new CoffeeResponse(coffee.getId(), coffee.getName(), coffee.getDescription()))
                .toList();
    }
}
