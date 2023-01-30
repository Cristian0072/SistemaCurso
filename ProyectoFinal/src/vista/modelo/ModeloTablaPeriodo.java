package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Periodo;

/**
 * fecha: 28/12/2022
 *
 * @author: CEAS
 */
public class ModeloTablaPeriodo extends AbstractTableModel {

    private ListaEnlazada<Periodo> lista;

    public ModeloTablaPeriodo() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Periodo> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Periodo> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return getLista().getTamanio();

    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Especificacion";
            case 2:
                return "Mes fin";
            case 3:
                return "Mes inicio";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Periodo periodo = null;
        try {
            periodo = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return periodo != null ? periodo.getIdPeriodo() : "";
            case 1:
                return periodo != null ? periodo.getEspecificacion() : "";
            case 2:
                return periodo != null ? periodo.getMesFin() : "";
            case 3:
                return periodo != null ? periodo.getMesInicio() : "";
            default:
                return null;
        }
    }

}
