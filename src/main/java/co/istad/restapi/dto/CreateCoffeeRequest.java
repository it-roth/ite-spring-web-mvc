package co.istad.restapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateCoffeeRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 255)
        String name,

        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 1000)
        String description,

        @Positive(message = "Price must be positive!")
        @NotNull
        Double price
) {
}
