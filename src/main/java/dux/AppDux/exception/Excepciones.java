package dux.AppDux.exception;

/**
 * Manejador de exepciones genericas
 * en su interior encontraras todas las exepciones necesarias y poderas customizarlas
 */
public class Excepciones extends RuntimeException {

    private String mensaje;
    private int codigo;
    
    
	public Excepciones(String mensaje, int codigo) {
		super(mensaje);
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
	
    public static class ExcepcionAuth extends Excepciones {
        public ExcepcionAuth (String message) {
            super(message, 401);
        }
    }
    
    public static class ExcepcionNotFound extends Excepciones {
        public ExcepcionNotFound (String message) {
            super(message, 404);
        }
    }
    
    public static class ExcepcionBadRequest extends Excepciones {
        public ExcepcionBadRequest (String message) {
            super(message, 400);
        }
    }

}
