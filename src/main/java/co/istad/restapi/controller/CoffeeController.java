package co.istad.restapi.controller;

import co.istad.restapi.domain.Coffee;
import co.istad.restapi.dto.CoffeeResponse;
import co.istad.restapi.repository.CoffeeRepository;
import co.istad.restapi.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coffees")
@Slf4j
public class CoffeeController {
    private final CoffeeService coffeeService;
    public CoffeeController(CoffeeService coffeeService){
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<CoffeeResponse> getCoffee(){
        return coffeeService.getCoffee();
    }
    @GetMapping("/{id}")
    public CoffeeResponse getCoffeeById(@PathVariable Integer id){
        log.info("Get coffee by id {}", id);
        return coffeeService.getCoffeeById(id);
    }
    @GetMapping ("/search")
    public List<CoffeeResponse> getCoffeeByName(
           @RequestParam (required = false, defaultValue = " ") String name,
           @RequestParam (required = false, defaultValue = "0.0") Double price
    ){
        log.info("Get coffee by name {}", name);
        return coffeeService.getCoffeeByName(name);
    }
}
