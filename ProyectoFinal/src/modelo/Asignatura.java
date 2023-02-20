package modelo;

/**
 *
 * @author SONY VAIO
 */
public class Asignatura {

    private Integer id;
    private String nombreAsignatura;
    private Docente docente;

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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    @Override
    public String toString() {
        return nombreAsignatura + " " + docente.toString() + " " + numeroHoras;
    }

}
