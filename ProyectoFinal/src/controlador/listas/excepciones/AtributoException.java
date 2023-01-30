/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        super("No sse encuenntro atributo");
    }

}
