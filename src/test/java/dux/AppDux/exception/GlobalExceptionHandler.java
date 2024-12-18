package dux.AppDux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Excepciones.ExcepcionAuth.class)
    public ResponseEntity<Object> handleUnauthorizedException(Excepciones.ExcepcionAuth ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new ErrorResponse(ex.getMessage(), ex.getCodigo()));
    }
    
    @ExceptionHandler(Excepciones.ExcepcionNotFound.class)
    public ResponseEntity<Object> handleNotFoundException(Excepciones.ExcepcionNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorResponse(ex.getMensaje(), ex.getCodigo()));
    }
    
    @ExceptionHandler(Excepciones.ExcepcionBadRequest.class)
    public ResponseEntity<Object> handleBadRequest(Excepciones.ExcepcionBadRequest ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new ErrorResponse(ex.getMensaje(), ex.getCodigo()));
    }

    public static class ErrorResponse {
        private String mensaje;
        private int codigo;

        public ErrorResponse(String mensaje, int codigo) {
            this.mensaje = mensaje;
            this.codigo = codigo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }
    }
}
