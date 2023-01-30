package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Malla;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ModeloTablaMalla extends AbstractTableModel {

    private ListaEnlazada<Malla> lista;

    public ModeloTablaMalla() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Malla> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Malla> lista) {
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
                return "Carreras";
            case 2:
                return "Regimen";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Malla malla = null;
        try {
            malla = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return malla != null ? malla.getId() : "";
            case 1:
                return malla != null ? malla.getCarreras() : "";
            case 2:
                return malla != null ? malla.getRegimen() : "";
            default:
                return null;
        }
    }
}
