/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.listas.excepciones;

/**
 *
 * @author SONY VAIO
 */
public class ListaNullException extends Exception {
    public ListaNullException(String msg){
        super(msg);
    }
    
    public ListaNullException(){
        super("La lista esta vacia");
    }
}
