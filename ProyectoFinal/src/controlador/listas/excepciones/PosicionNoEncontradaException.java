/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.listas.excepciones;

/**
 *
 * @author SONY VAIO
 */
public class PosicionNoEncontradaException extends Exception{
    public PosicionNoEncontradaException(String msg){
        super(msg);
    }
    
    public PosicionNoEncontradaException(){
        super("La posicion esta fuera de los limites");
    }
}
