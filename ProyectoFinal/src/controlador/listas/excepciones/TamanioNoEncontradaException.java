/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.listas.excepciones;

/**
 *
 * @author Usuario
 */
public class TamanioNoEncontradaException extends Exception{
    public TamanioNoEncontradaException(String msg){
        super(msg);
    }

    public TamanioNoEncontradaException() {
        super("la posicion dada no vale");
    }
    
    
    
}
