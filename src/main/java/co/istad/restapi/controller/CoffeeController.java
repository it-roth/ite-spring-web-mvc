package co.istad.restapi.controller;

import co.istad.restapi.domain.Coffee;
import co.istad.restapi.dto.CoffeeResponse;
import co.istad.restapi.dto.CreateCoffeeRequest;
import co.istad.restapi.dto.UpdateCoffeeRequest;
import co.istad.restapi.repository.CoffeeRepository;
import co.istad.restapi.service.CoffeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/coffees")
@Slf4j
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Integer id){
        log.info("DELTE COFFEE ID  : {}",id);
        coffeeService.deleteCoffeeById(id);
    }


    @PutMapping("/{id}")
    public CoffeeResponse updateCoffeeById(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCoffeeRequest updateCoffeeRequest
    ) {
        return coffeeService.updateCoffeeById(id, updateCoffeeRequest);
    }



    @ResponseStatus(HttpStatus.CREATED) // customize response status code here
    @PostMapping
    public CoffeeResponse createCoffee(
            @Valid @RequestBody CreateCoffeeRequest createCoffeeRequest
    ) {
        return coffeeService.createCoffee(createCoffeeRequest);
    }


    @GetMapping
    public List<CoffeeResponse> getCoffees() {
        return coffeeService.getCoffees();
    }

    @GetMapping("/{id}")
    public CoffeeResponse getCoffeeById(@PathVariable Integer id) {
        log.info("GET id: {}", id);
        return coffeeService.getCoffeeById(id);
    }

    @GetMapping("/search")
    public List<CoffeeResponse> searchCoffees(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "0") BigDecimal price
    ) {
        log.info("GET search name: {}", name);
        log.info("GET search price: {}", price);
        return coffeeService.searchCoffee(name, price);
    }

}
