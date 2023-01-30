package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Matricula;

/**
 * fecha: 28/12/2022
 *
 * @author: CEAS
 */
public class ModeloTablaMatricula extends AbstractTableModel {

    private ListaEnlazada<Matricula> lista;

    public ModeloTablaMatricula() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Matricula> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Matricula> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return getLista().getTamanio();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Fecha emision";
            case 2:
                return "Periodo";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Matricula matricula = null;
        try {
            matricula = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return matricula != null ? matricula.getIdMatricula() : "";
            case 1:
                return matricula != null ? matricula.getFechaEmision() : "";
            case 2:
                return matricula != null ? matricula.getPeriodo() : "";
            default:
                return null;
        }
    }

}
