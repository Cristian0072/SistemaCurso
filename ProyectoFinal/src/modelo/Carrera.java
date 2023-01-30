package modelo;

import controlador.listas.ListaEnlazada;
import modelo.enums.Seccion;

/**
 *
 * @author SONY VAIO
 */
public class Carrera {

    private Integer id;
    private String nombre;
    private Seccion seccion;
    private ListaEnlazada<Ciclo> ciclos;

    public Carrera() {
        ciclos = new ListaEnlazada<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public ListaEnlazada<Ciclo> getCiclos() {
        return ciclos;
    }

    public void setCiclos(ListaEnlazada<Ciclo> ciclos) {
        this.ciclos = ciclos;
    }

}
