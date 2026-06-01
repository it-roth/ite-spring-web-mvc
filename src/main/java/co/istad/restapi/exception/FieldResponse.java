package co.istad.restapi.exception;

public record FieldResponse(
        String field,
        String message
) {
}
