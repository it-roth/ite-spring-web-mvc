package co.istad.restapi.repository;

import co.istad.restapi.domain.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CoffeeRepository {
    @Bean
    public List<Coffee> BeanCoffee(){
        Coffee coffee1 = new Coffee(1,"Hot-Latte","100%",12.02);
        Coffee coffee2 = new Coffee(2,"Ice-Latte","80%",12.02);
        Coffee coffee3 = new Coffee(3,"Matcha-Latte","80%",12.02);
        Coffee coffee4 = new Coffee(4,"Green-Tea","85%",12.02);
        return new ArrayList<>(List.of(coffee1,coffee2,coffee3,coffee4));
    }
}
