/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Utilidades;

import controlador.ControladorAsignatura;
import controlador.ControladorCarrera;
import controlador.ControladorCiclo;
import controlador.ControladorDocente;
import controlador.ControladorMatricula;
import controlador.ControladorPeriodo;
import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import javax.swing.JComboBox;
import modelo.Asignatura;
import modelo.Carrera;
import modelo.Ciclo;
import modelo.Docente;
import modelo.Matricula;
import modelo.Periodo;
import modelo.enums.EspecificacionPeriodo;
import modelo.enums.Estado;
import modelo.enums.Generos;
import modelo.enums.Meses;
import modelo.enums.Seccion;

/**
 *
 * @author David Campoverde
 */
public class Utilidades {

    public static void cargarComboGenero(JComboBox cbx) {
        cbx.removeAllItems();
        for (Generos genero : Generos.values()) {
            cbx.addItem(genero);
        }
    }

    public static Seccion obtenerSeccion(JComboBox cbx) {
        return (Seccion) cbx.getSelectedItem();
    }

    public static void cargarComboMeses(JComboBox cbx) {
        cbx.removeAllItems();
        for (Meses mes : Meses.values()) {
            cbx.addItem(mes);
        }
    }

    public static Meses obtenerMes(JComboBox cbx) {
        return (Meses) cbx.getSelectedItem();
    }

    public static void cargarCiclos(JComboBox cbx) {
        cbx.removeAllItems();
        ControladorCiclo ciclo = new ControladorCiclo();
        ListaEnlazada<Ciclo> lista = ciclo.listar();
        for (int i = 0; i < lista.getTamanio(); i++) {
            try {
                cbx.addItem(lista.obtener(i));
            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }

    }

    public static ListaEnlazada<Ciclo> obtenerCiclos(JComboBox cbx) {
        ListaEnlazada<Ciclo> lista = new ListaEnlazada<>();
        lista.insertar((Ciclo) cbx.getSelectedItem());
        return lista;
    }

    public static void cargarAsignaturas(JComboBox cbx) {
        cbx.removeAllItems();
        try {
            ControladorAsignatura asignatura = new ControladorAsignatura();
            ListaEnlazada<Asignatura> lista = asignatura.listar();
            for (int i = 0; i < lista.getTamanio(); i++) {
                cbx.addItem(lista.obtener(i));
            }
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }

    }

    public static Asignatura obtenerAsignatura(JComboBox cbx) {
        return (Asignatura) cbx.getSelectedItem();
    }

    public static void cargarDocentes(JComboBox cbx) {
        cbx.removeAllItems();
        ControladorDocente docente = new ControladorDocente();
        ListaEnlazada<Docente> lista = docente.listar();
        for (int i = 0; i < lista.getTamanio(); i++) {
            try {
                cbx.addItem(lista.obtener(i));
            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }

    }

    public static Docente obtenerDocente(JComboBox cbx) {
        return (Docente) cbx.getSelectedItem();
    }

    public static void cargarMatriculas(JComboBox cbx) {
        cbx.removeAllItems();
        ControladorMatricula matricula = new ControladorMatricula();
        ListaEnlazada<Matricula> lista = matricula.listar();
        for (int i = 0; i < lista.getTamanio(); i++) {
            try {
                cbx.addItem(lista.obtener(i));
            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }

    }

    public static Matricula obtenerMatricula(JComboBox cbx) {
        return (Matricula) cbx.getSelectedItem();
    }

    public static void cargarCarreras(JComboBox cbx) {
        cbx.removeAllItems();
        ControladorCarrera carrera = new ControladorCarrera();
        ListaEnlazada<Carrera> lista = carrera.listar();
        for (int i = 0; i < lista.getTamanio(); i++) {
            try {
                cbx.addItem(lista.obtener(i));
            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }

    }

    public static ListaEnlazada<Carrera> obtenerCarreras(JComboBox cbx) {
        ListaEnlazada<Carrera> lista = new ListaEnlazada<>();
        lista.insertar((Carrera) cbx.getSelectedItem());
        return lista;
    }

    public static ListaEnlazada<Asignatura> obtenerAsignaturas(JComboBox cbx) {
        ListaEnlazada<Asignatura> lista = new ListaEnlazada<>();
        lista.insertar((Asignatura) cbx.getSelectedItem());
        return lista;
    }

    public static void cargarEstado(JComboBox cbx) {
        cbx.removeAllItems();
        for (Estado estado : Estado.values()) {
            cbx.addItem(estado);
        }
    }

    public static Estado obtenerEstado(JComboBox cbx) {
        return (Estado) cbx.getSelectedItem();
    }

    public static void cargarGeneros(JComboBox cbx) {
        cbx.removeAllItems();
        for (Generos genero : Generos.values()) {
            cbx.addItem(genero);
        }
    }

    public static Generos obtenerGenero(JComboBox cbx) {
        return (Generos) cbx.getSelectedItem();
    }

    public static void cargarEspecificaciones(JComboBox cbx) {
        cbx.removeAllItems();
        for (EspecificacionPeriodo especificacion : EspecificacionPeriodo.values()) {
            cbx.addItem(especificacion);
        }
    }

    public static EspecificacionPeriodo obtenerEspecificacion(JComboBox cbx) {
        return (EspecificacionPeriodo) cbx.getSelectedItem();
    }

    public static void cargarPeriodos(JComboBox cbx) {
        cbx.removeAllItems();
        ControladorPeriodo periodo = new ControladorPeriodo();
        ListaEnlazada<Periodo> lista = periodo.listar();
        for (int i = 0; i < lista.getTamanio(); i++) {
            try {
                cbx.addItem(lista.obtener(i));
            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }

    public static Periodo obtenerPeriodo(JComboBox cbx) {
        return (Periodo) cbx.getSelectedItem();
    }

    public static void cargarSeccion(JComboBox cbx) {
        cbx.removeItem(cbx);
        for (Seccion seccion : Seccion.values()) {
            cbx.addItem(seccion);
        }
    }
//Método para validar una cédula Ecuatoriana
    public static Boolean esCedulaValida(String cedula) {
        if (cedula.length() != 10) {
            return false;
        } else {
            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;
            for (int i = 0; i < 10 - 1; i++) {
                int digito = Integer.parseInt(cedula.substring(i, i + 1));
                int producto = digito * coeficientes[i];
                suma += (producto >= 10) ? (producto - 9) : producto;
            }
            int verificador = (suma % 10 == 0) ? 0 : (10 - (suma % 10));
            return verificador == Integer.parseInt(cedula.substring(9));
        }
    }

    /*    public static void main(String[] args) {
        System.out.println(esCedulaValida("1150197588"));
    }*/
}
