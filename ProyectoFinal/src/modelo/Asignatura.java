package modelo;

import modelo.enums.Estado;

/**
 *
 * @author SONY VAIO
 */
public class Asignatura {

    private Integer id;
    private String nombreAsignatura;
    private Character paralelo;
    private Docente docente;
    private Estado estadoAsignatura;
    private Integer numeroHoras;

    public Asignatura() {
        docente = new Docente();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Character getParalelo() {
        return paralelo;
    }

    public void setParalelo(Character paralelo) {
        this.paralelo = paralelo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Estado getEstadoAsignatura() {
        return estadoAsignatura;
    }

    public void setEstadoAsignatura(Estado estadoAsignatura) {
        this.estadoAsignatura = estadoAsignatura;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    @Override
    public String toString() {
        return "ID " + id + " nombreAsignatura " + nombreAsignatura + " paralelo " + paralelo + " docente " + docente + " estadoAsignatura " + estadoAsignatura + " numeroHoras " + numeroHoras;
    }

}
