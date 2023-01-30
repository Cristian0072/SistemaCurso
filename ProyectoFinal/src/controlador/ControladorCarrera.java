package controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullExcepction;
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

    public Boolean actualizar(ListaEnlazada<Carrera> listaEnlazada) {
        return guardar(lista);
    }

}
