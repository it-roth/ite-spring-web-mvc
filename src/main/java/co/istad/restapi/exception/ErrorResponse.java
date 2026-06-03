package co.istad.restapi.exception;
import co.istad.restapi.exception.ErrorResponse;

import lombok.Builder;

@Builder
public record ErrorResponse<T>(
        Boolean status,
        Integer code,
        String message,
        T errors
) {
}

