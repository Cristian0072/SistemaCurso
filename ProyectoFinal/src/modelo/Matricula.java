package modelo;

/**
 *
 * @author SONY VAIO
 */
public class Matricula {

    private Integer idMatricula;
    private String fechaEmision;
    private Periodo periodo;

    private Float promedioGeneral;

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Float getPromedioGeneral() {
        return promedioGeneral;
    }

    public void setPromedioGeneral(Float promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    @Override
    public String toString() {
        return idMatricula.toString();
    }

}
