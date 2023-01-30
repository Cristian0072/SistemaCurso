package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Carrera;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ModeloTablaCarrera extends AbstractTableModel {

    private ListaEnlazada<Carrera> lista;

    public ModeloTablaCarrera() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Carrera> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Carrera> lista) {
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
                return "Nombre";
            case 2:
                return "Seccion";
            case 3:
                return "Ciclo";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Carrera carrera = null;
        try {
            carrera = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return carrera != null ? carrera.getId() : "";
            case 1:
                return carrera != null ? carrera.getNombre() : "";
            case 2:
                return carrera != null ? carrera.getSeccion() : "";
            case 3:
                return carrera != null ? carrera.getCiclos() : "";
            default:
                return null;
        }
    }
}
