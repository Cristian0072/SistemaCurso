package modelo;

import modelo.enums.EspecificacionPeriodo;
import modelo.enums.Meses;

/**
 *
 * @author SONY VAIO
 */
public class Periodo {

    private Integer idPeriodo;
    private Meses mesInicio;
    private Meses mesFin;
    private EspecificacionPeriodo especificacion;

    public Periodo() {

    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Meses getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(Meses mesInicio) {
        this.mesInicio = mesInicio;
    }

    public Meses getMesFin() {
        return mesFin;
    }

    public void setMesFin(Meses mesFin) {
        this.mesFin = mesFin;
    }

    public EspecificacionPeriodo getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(EspecificacionPeriodo especificacion) {
        this.especificacion = especificacion;
    }

}
