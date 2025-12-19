package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao.ErroDuploTipoUsuario;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao.ErroUsuarioExistente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFound(UsernameNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralErrors(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("erro", "Erro interno no servidor: " + ex.getMessage()));
    }

    @ExceptionHandler(ErroDuploTipoUsuario.class)
    public ResponseEntity<?> handlerErroDuploTipoUsuario(ErroDuploTipoUsuario ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(ErroUsuarioExistente.class)
    public ResponseEntity<?> handleErroUsuarioExistente(ErroUsuarioExistente ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("erro", ex.getMessage()));
    }
}
