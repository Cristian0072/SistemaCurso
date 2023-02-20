package controlador.listas.excepciones;

/**
 *
 * @author Usuario
 */
public class AtributoException extends Exception{
    public AtributoException(String message) {
        super(message);
    }
    
    public AtributoException() {
        super("No se encontro el atributo");
    }

}
