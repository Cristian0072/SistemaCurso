
package modelo;

/**
 *
 * @author SONY VAIO
 */
public class Docente extends Persona {
    private Integer idDocente;
    private String tituloTercerNivel;
    private String tituloCuartoNivel;
    private Integer aniosExpDocente;
    private Integer aniosExpLaboral;

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getTituloTercerNivel() {
        return tituloTercerNivel;
    }

    public void setTituloTercerNivel(String tituloTercerNivel) {
        this.tituloTercerNivel = tituloTercerNivel;
    }

    public String getTituloCuartoNivel() {
        return tituloCuartoNivel;
    }

    public void setTituloCuartoNivel(String tituloCuartoNivel) {
        this.tituloCuartoNivel = tituloCuartoNivel;
    }


    public Integer getAniosExpDocente() {
        return aniosExpDocente;
    }

    public void setAniosExpDocente(Integer aniosExpDocente) {
        this.aniosExpDocente = aniosExpDocente;
    }

    public Integer getAniosExpLaboral() {
        return aniosExpLaboral;
    }

    public void setAniosExpLaboral(Integer aniosExpLaboral) {
        this.aniosExpLaboral = aniosExpLaboral;
    }
    
    
}
