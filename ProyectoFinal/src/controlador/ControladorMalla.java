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
import modelo.Malla;

/**
 * fecha: 26/12/2022
 *
 * @author: CEAS
 */
public class ControladorMalla {

    private Malla malla;
    private ListaEnlazada<Malla> lista;

    public ListaEnlazada<Malla> getLista() {
        if (lista == null) {
            lista = listar();
        }
        return lista;
    }

    public void setLista(ListaEnlazada<Malla> lista) {
        this.lista = lista;
    }

    public Malla getMalla() {
        if (malla == null) {
            malla = new Malla();
        }
        return malla;
    }

    public void setMalla(Malla malla) {
        this.malla = malla;
    }

    public Boolean guardar(ListaEnlazada<Malla> malla) {
        FileWriter file = null;
        try {
            file = new FileWriter("Datos" + File.separatorChar + getMalla().getClass().getSimpleName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(malla));
            file.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }

    public ListaEnlazada<Malla> listar() {

        try {
            FileReader file = new FileReader("Datos" + File.separatorChar + new Malla().getClass().getSimpleName() + ".json");
            lista = new Gson().fromJson(file, new TypeToken<ListaEnlazada<Malla>>() {
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

    public Boolean actualizar(ListaEnlazada<Malla> listaEnlazada) {
        return guardar(lista);
    }

   /* public static void main(String[] args) {
        Malla malla = new Malla();
        malla.setId(1);
        malla.setRegimen("Costa");
        malla.setCarreras(new ControladorCarrera().listar());
        ListaEnlazada<Malla> lista = new ListaEnlazada<>();
        lista.insertar(malla);
        ControladorMalla cm = new ControladorMalla();
        cm.guardar(lista);
        cm.listar().imprimir();
    }*/
}
