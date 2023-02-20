package vista.modelo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.table.AbstractTableModel;
import modelo.Alumno;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ModeloTablaAlumno extends AbstractTableModel {

    private ListaEnlazada<Alumno> lista;

    public ModeloTablaAlumno() {
        this.lista = new ListaEnlazada<>();
    }

    public ListaEnlazada<Alumno> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Alumno> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return getLista().getTamanio();

    }

    @Override
    public int getColumnCount() {
        return 14;
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
                return "Matricula";
            case 10:
                return "Asignatura";
            case 11:
                return "Paralelo";
            case 12:
                return "Estado";
            case 13:
                return "Correo";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Alumno alumno = null;
        try {
            alumno = getLista().obtener(fila);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        switch (columna) {
            case 0:
                return alumno != null ? alumno.getIdAlumno() : "";
            case 1:
                return alumno != null ? alumno.getNombres() : "";
            case 2:
                return alumno != null ? alumno.getApellidos() : "";
            case 3:
                return alumno != null ? alumno.getGenero() : "";
            case 4:
                return alumno != null ? alumno.getIdentificacion() : "";
            case 5:
                return alumno != null ? alumno.getCiudad() : "";
            case 6:
                return alumno != null ? alumno.getDireccion() : "";
            case 7:
                return alumno != null ? alumno.getTelefono() : "";
            case 8:
                return alumno != null ? alumno.getFechaNacimiento() : "";
            case 9:
                return alumno != null ? alumno.getMatricula() : "";
            case 10:
                return alumno != null ? alumno.getAsignatura().getNombreAsignatura() : "";
            case 11:
                return alumno != null ? alumno.getParalelo() : "";
            case 12:
                return alumno != null ? alumno.getEstado(): "";
            case 13:
                return alumno != null ? alumno.getCorreo(): "";
            default:
                return null;
        }
    }
}
