package controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.TamanioNoEncontradaException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import modelo.Carrera;

/**
 * fecha: 26/12/2022
 *
 * @author: CEAS
 */
public class ControladorCarrera {
    
    private Carrera carrera;
    private ListaEnlazada<Carrera> lista;
    
    public ListaEnlazada<Carrera> getLista() {
        if (lista == null) {
            lista = listar();
        }
        return lista;
    }
    
    public void setLista(ListaEnlazada<Carrera> lista) {
        this.lista = lista;
    }
    
    public Carrera getCarrera() {
        if (carrera == null) {
            carrera = new Carrera();
        }
        return carrera;
    }
    
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    public Boolean guardar(ListaEnlazada<Carrera> carrera) {
        FileWriter file = null;
        try {
            file = new FileWriter("Datos" + File.separatorChar + getCarrera().getClass().getSimpleName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(carrera));
            file.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }
    
    public ListaEnlazada<Carrera> listar() {
        
        try {
            FileReader file = new FileReader("Datos" + File.separatorChar + getCarrera().getClass().getSimpleName() + ".json");
            lista = new Gson().fromJson(file, new TypeToken<ListaEnlazada<Carrera>>() {
            }.getType());
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
        } catch (ListaNullException | TamanioNoEncontradaException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }
    
    public Boolean actualizar(ListaEnlazada<Carrera> listaEnlazada) {
        return guardar(lista);
    }

    /*public static void main(String[] args) {
        Carrera carrera = new Carrera();
        carrera.setId(1);
        carrera.setNombre("Computacion");
        carrera.setSeccion(Seccion.NOCTURNO);
        Ciclo ciclo = new Ciclo();
        ciclo.setId(1);
        ciclo.setNombre("Ciclo 3");
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setEstadoAsignatura(Estado.APROBADA);
        asignatura.setNombreAsignatura("Base de Datos");
        asignatura.setNumeroHoras(50);
        asignatura.setParalelo('A');
        asignatura.setUnidad("Unidad 2");
        Docente docente = new Docente();
        docente.setIdDocente(1);
        docente.setAniosExpDocente(4);
        docente.setAniosExpLaboral(8);
        docente.setApellidos("Camacho Fernandes");
        docente.setNombres("Pedro Pablo");
        docente.setCiudad("Quito");
        docente.setDireccion("Av. Los Naranjos");
        docente.setFechaNacimiento("15/05/1980");
        docente.setGenero(Generos.MASCULINO);
        docente.setIdentificacion("1120986354");
        docente.setTelefono("0985623457");
        docente.setTituloCuartoNivel("Maestria");
        docente.setTituloTercerNivel("Ingenieria");
        asignatura.setDocente(docente);
        ListaEnlazada<Asignatura> lista = new ListaEnlazada<>();
        lista.insertar(asignatura);
        ciclo.setAsignaturas(lista);
        ListaEnlazada<Ciclo> listaC = new ListaEnlazada<>();
        listaC.insertar(ciclo);
        carrera.setCiclos(listaC);
        ListaEnlazada<Carrera> listaA = new ListaEnlazada<>();
        listaA.insertar(carrera);
        ControladorCarrera carreras = new ControladorCarrera();
        carreras.guardar(listaA);
    }*/
}
