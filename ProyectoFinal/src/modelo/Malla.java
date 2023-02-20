package modelo;

import controlador.listas.ListaEnlazada;

/**
 *
 * @author SONY VAIO
 */
public class Malla {

    private Integer id;
    private ListaEnlazada<Carrera> carreras;
    private String regimen;

    public Malla() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ListaEnlazada<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ListaEnlazada<Carrera> carreras) {
        this.carreras = carreras;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    @Override
    public String toString() {
        return carreras.getCabecera().getDato().toString() + " " + regimen;
    }

}
