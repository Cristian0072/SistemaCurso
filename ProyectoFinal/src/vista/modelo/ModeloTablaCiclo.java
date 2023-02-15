package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Ciclo;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ModeloTablaCiclo extends AbstractTableModel {

    private ListaEnlazada<Ciclo> lista;

    public ModeloTablaCiclo() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Ciclo> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Ciclo> lista) {
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
                return "Nombre";
            case 2:
                return "Asignaturas";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Ciclo ciclo = null;
        try {
            ciclo = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return ciclo != null ? ciclo.getId() : "";
            case 1:
                return ciclo != null ? ciclo.getNombre() : "";
            case 2:
                return ciclo != null ? ciclo.getAsignaturas().getCabecera().getDato().getNombreAsignatura() : "";
            default:
                return null;
        }
    }
}
