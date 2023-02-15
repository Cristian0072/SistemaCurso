package modelo;

import controlador.listas.ListaEnlazada;

/**
 *
 * @author SONY VAIO
 */
public class Ciclo {

    private Integer id;
    private String nombre;
    private ListaEnlazada<Asignatura> asignaturas;

    public Ciclo() {
        asignaturas = new ListaEnlazada<>();
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

    public ListaEnlazada<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return "ID " + id + " nombre " + nombre + " asignaturas " + asignaturas.toString();
    }

}
