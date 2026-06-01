package co.istad.restapi.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class AppException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
        log.info("Validation exception happended");
        List<FieldResponse> errorFields = new ArrayList<>();
        e.getFieldErrors().forEach(f->errorFields.add(
                new FieldResponse(f.getField(),f.getDefaultMessage())
        ));
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("code", e.getStatusCode());
        response.put("message","Validating is errored ");
        response.put("errors",errorFields);
        return ResponseEntity.
                badRequest().
                        body(response);
    }
}
