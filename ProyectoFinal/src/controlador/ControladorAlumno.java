package controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullExcepction;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import controlador.listas.excepciones.TamanioNoEncontradaException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Alumno;
import modelo.Matricula;
import modelo.enums.Generos;

/**
 * fecha: 26/12/2022
 *
 * @author: CEAS
 */
public class ControladorAlumno {

    private Alumno alumno;
    private ListaEnlazada<Alumno> lista;

    public ListaEnlazada<Alumno> getLista() {
        if (lista == null) {
            lista = listar();
        }
        return lista;
    }

    public void setLista(ListaEnlazada<Alumno> lista) {
        this.lista = lista;
    }

    public Alumno getAlumno() {
        if (alumno == null) {
            alumno = new Alumno();
        }
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Boolean guardar(ListaEnlazada<Alumno> alumno) {
        FileWriter file = null;
        try {
            file = new FileWriter("Datos" + File.separatorChar + getAlumno().getClass().getSimpleName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(alumno) + "\n");
            file.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }

    public ListaEnlazada<Alumno> listar() {
        FileReader file = null;
        try {
            file = new FileReader("Datos" + File.separatorChar + getAlumno().getClass().getSimpleName() + ".json");
            lista = new Gson().fromJson(file, ListaEnlazada.class);
        } catch (FileNotFoundException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return lista;
    }

    public Boolean borrar(Integer pos) {
        try {
            lista = listar();
            lista.eliminar(pos);
            return guardar(lista);
        } catch (ListaNullExcepction | TamanioNoEncontradaException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }

    public Boolean actualizar(ListaEnlazada<Alumno> listaEnlazada) {
        return guardar(lista);
    }

    public static void main(String[] args) {
        try {
            ControladorAlumno alumno = new ControladorAlumno();
            alumno.getAlumno().setIdAlumno(1);
            alumno.getAlumno().setApellidos("Gutierrez Suarez");
            alumno.getAlumno().setNombres("Luis Mario");
            alumno.getAlumno().setCiudad("Loja");
            alumno.getAlumno().setDireccion("Los Laureles y Santa Maria");
            alumno.getAlumno().setFechaNacimiento("05/02/2000");
            alumno.getAlumno().setGenero(Generos.MASCULINO);
            alumno.getAlumno().setIdentificacion("1150798632");
            alumno.getAlumno().setTelefono("0986523457");
            Matricula matricula = new Matricula();
            matricula.setIdMatricula(1);
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            matricula.setFechaEmision(date.format(new Date()));
            ControladorPeriodo periodo = new ControladorPeriodo();
            periodo.listar().imprimir();
            matricula.setPeriodo(periodo.getLista().obtener(1));
            alumno.getAlumno().setMatricula(matricula);
            ListaEnlazada<Alumno> lista = new ListaEnlazada<>();
            lista.insertar(alumno.getAlumno());
            alumno.guardar(lista);
            alumno.listar().imprimir();
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            Logger.getLogger(ControladorAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
