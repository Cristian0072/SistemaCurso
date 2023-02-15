package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Asignatura;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ModeloTablaAsignatura extends AbstractTableModel {

    private ListaEnlazada<Asignatura> lista;

    public ModeloTablaAsignatura() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Asignatura> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Asignatura> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return getLista().getTamanio();

    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Docente";
            case 2:
                return "Estado";
            case 3:
                return "Nombre asignatura";
            case 4:
                return "Paralelo";
            case 5:
                return "Nro. Horas";

            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Asignatura asignatura = null;
        try {
            asignatura = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return asignatura != null ? asignatura.getId() : "";
            case 1:
                return asignatura != null ? asignatura.getDocente() : "";
            case 2:
                return asignatura != null ? asignatura.getEstadoAsignatura() : "";
            case 3:
                return asignatura != null ? asignatura.getNombreAsignatura() : "";
            case 4:
                return asignatura != null ? asignatura.getParalelo() : "";
            case 5:
                return asignatura != null ? asignatura.getNumeroHoras() : "";
            default:
                return null;
        }
    }
}
