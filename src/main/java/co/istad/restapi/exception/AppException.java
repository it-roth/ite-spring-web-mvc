package co.istad.restapi.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import co.istad.restapi.exception.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class AppException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse<?> handleValidationException(MethodArgumentNotValidException e){
        log.info("Validation exception happended");
        List<FieldErrorResponse> fields = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError ->
                fields.add(
                        FieldErrorResponse.builder().field(fieldError.getField())
                                .message(fieldError.getDefaultMessage()).build()
                )
        );
        return ErrorResponse.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed!")
                .errors(fields)
                .build();

    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceException(
            ResponseStatusException e
    ){
        ErrorResponse<?> errorResponse = ErrorResponse.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .message("Service exception errored")
                .errors(e.getReason())
                .build();
        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

//    private final List list;
//
//    public AppException(List list) {
//        this.list = list;
//    }

    //    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
//        log.info("Validation exception happended");
//        List<FieldResponse> errorFields = new ArrayList<>();
//        e.getFieldErrors().forEach(f->errorFields.add(
//                new FieldResponse(f.getField(),f.getDefaultMessage())
//        ));
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", false);
//        response.put("code", e.getStatusCode());
//        response.put("message","Validating is errored ");
//        response.put("errors",errorFields);
//        return ResponseEntity.
//                badRequest().
//                        body(response);
//    }

}
