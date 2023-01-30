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
import modelo.Ciclo;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ControladorCiclo {

    private ListaEnlazada<Ciclo> lista;
    private Ciclo ciclo;

    public ListaEnlazada<Ciclo> getLista() {
        if (lista == null) {
            lista = listar();
        }
        return lista;
    }

    public void setLista(ListaEnlazada<Ciclo> lista) {
        this.lista = lista;
    }

    public Ciclo getCiclo() {
        if (ciclo == null) {
            ciclo = new Ciclo();
        }
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Boolean guardar(ListaEnlazada<Ciclo> ciclo) {
        FileWriter file = null;
        try {
            file = new FileWriter("Datos" + File.separatorChar + getCiclo().getClass().getSimpleName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(ciclo));
            file.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }

    public ListaEnlazada<Ciclo> listar() {
        FileReader file = null;
        try {
            file = new FileReader("Datos" + File.separatorChar + ciclo.getClass().getSimpleName() + ".json");
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

    public Boolean actualizar(ListaEnlazada<Ciclo> lista) {
        return guardar(lista);
    }

   /* public static void main(String[] args) {
        Ciclo ciclo = new Ciclo();
        ciclo.setNombre("Ciclo 1");
        ciclo.setId(1);

        ControladorAsignatura asignatura = new ControladorAsignatura();
        ciclo.setAsignaturas(asignatura.listar());
        ControladorCiclo cicloEnlazado = new ControladorCiclo();
        ListaEnlazada<Ciclo> lista = new ListaEnlazada<>();
        lista.insertar(ciclo);
        cicloEnlazado.guardar(lista);
    }*/
}
