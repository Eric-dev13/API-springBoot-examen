package com.api.mushroom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Permet de gérer toutes les erreurs de validation de manière centralisée et de renvoyer des réponses cohérentes en cas d'erreur de validation dans toute l'application.
 *
 * @ControllerAdvice : Indique à Spring que cette classe est un gestionnaire d'exceptions globale qui peut gérer des exceptions à travers toute l'application.
 *
 * @RestController : indique que la classe est un contrôleur REST. Elle permet de renvoyer des réponses JSON en cas de gestion d'exceptions.
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


}
