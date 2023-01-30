package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Docente;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ModeloTablaDocente extends AbstractTableModel {

    private ListaEnlazada<Docente> lista;

    public ModeloTablaDocente() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Docente> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Docente> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return getLista().getTamanio();

    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Nombre";
            case 2:
                return "Apellidos";
            case 3:
                return "Genero";
            case 4:
                return "Cedula";
            case 5:
                return "Ciudad";
            case 6:
                return "Direccion";
            case 7:
                return "Telefono";
            case 8:
                return "Fecha Nacimiento";
            case 9:
                return "Titulo 3er Nivel ";
            case 10:
                return "Titulo 4to Nivel ";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Docente docente = null;
        try {
            docente = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return docente != null ? docente.getIdDocente() : "";
            case 1:
                return docente != null ? docente.getNombres() : "";
            case 2:
                return docente != null ? docente.getApellidos() : "";
            case 3:
                return docente != null ? docente.getGenero() : "";
            case 4:
                return docente != null ? docente.getIdentificacion() : "";
            case 5:
                return docente != null ? docente.getCiudad() : "";
            case 6:
                return docente != null ? docente.getDireccion() : "";
            case 7:
                return docente != null ? docente.getDireccion() : "";
            case 8:
                return docente != null ? docente.getFechaNacimiento() : "";
            case 9:
                return docente != null ? docente.getTituloTercerNivel() : " ";
            case 10:
                return docente != null ? docente.getTituloCuartoNivel() : "";
            default:
                return null;
        }
    }
}
