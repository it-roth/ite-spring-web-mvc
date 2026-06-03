package co.istad.restapi.dto;

import co.istad.restapi.domain.Coffee;

public record CoffeeResponse(
        Integer id,
        String name,
        String description
) {
}
