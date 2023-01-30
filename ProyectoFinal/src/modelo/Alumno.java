package modelo;

/**
 *
 * @author SONY VAIO
 */
public class Alumno extends Persona {

    private Integer idAlumno;
    private Matricula matricula;
    private Asignatura asignatura;

    public Alumno() {
        asignatura = new Asignatura();
        matricula = new Matricula();
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

}
