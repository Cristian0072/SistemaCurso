package modelo;

import modelo.enums.Estado;

/**
 *
 * @author SONY VAIO
 */
public class Alumno extends Persona {

    private Integer idAlumno;
    private Matricula matricula;
    private Asignatura asignatura;
    private Character paralelo;
    private Estado estado;

    public Alumno() {
        asignatura = new Asignatura();
        matricula = new Matricula();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Character getParalelo() {
        return paralelo;
    }

    public void setParalelo(Character paralelo) {
        this.paralelo = paralelo;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return getNombres() + " " + getApellidos() + " " + matricula.toString() + " " + asignatura.toString() + " " + paralelo+" "+estado;
    }

}
