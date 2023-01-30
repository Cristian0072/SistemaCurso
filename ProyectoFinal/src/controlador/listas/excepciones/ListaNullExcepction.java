/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.listas.excepciones;

/**
 *
 * @author Usuario
 */
public class ListaNullExcepction extends Exception{

    
    public ListaNullExcepction(String message) {
        super(message);
    }
    
    public ListaNullExcepction() {
        super("Lista vacia pa");
    }

 
    
}
