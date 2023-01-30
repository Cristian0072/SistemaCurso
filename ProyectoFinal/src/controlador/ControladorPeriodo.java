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
import modelo.Periodo;

/**
 * fecha: 26/12/2022
 *
 * @author: CEAS
 */
public class ControladorPeriodo {

    private Periodo periodo;
    private ListaEnlazada<Periodo> lista;

    public ListaEnlazada<Periodo> getLista() {
        if (lista == null) {
            lista = listar();
        }
        return lista;
    }

    public void setLista(ListaEnlazada<Periodo> lista) {
        this.lista = lista;
    }

    public Periodo getPeriodo() {
        if (periodo == null) {
            periodo = new Periodo();
        }
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Boolean guardar(ListaEnlazada<Periodo> periodo) {
        FileWriter file = null;
        try {
            file = new FileWriter("Datos" + File.separatorChar + getPeriodo().getClass().getSimpleName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(periodo));
            file.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }

    public ListaEnlazada<Periodo> listar() {
        FileReader file = null;
        try {
            file = new FileReader("Datos" + File.separatorChar + getPeriodo().getClass().getSimpleName() + ".json");
            lista = new Gson().fromJson(file, ListaEnlazada.class);
        } catch (FileNotFoundException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return lista;
    }

    public Boolean borrar(Integer pos) throws IOException, ListaNullExcepction, TamanioNoEncontradaException {
        lista = listar();
        lista.eliminar(pos);
        return guardar(lista);
    }

    public Boolean actualizar(ListaEnlazada<Periodo> listaEnlazada) throws IOException {
        return guardar(lista);
    }
    /*public static void main(String[] args) {
        try {
            ControladorPeriodo controlador =new ControladorPeriodo();
            controlador.getPeriodo().setIdPeriodo(1);
            controlador.getPeriodo().setEspecificacion(EspecificacionPeriodo.TRIMESTRE);
            controlador.getPeriodo().setMesFin(Meses.ENERO);
            controlador.getPeriodo().setMesInicio(Meses.MAYO);
            ListaEnlazada<Periodo> lista = new ListaEnlazada<>();
            
            lista.insertar(controlador.getPeriodo());
            controlador.guardar(lista);
        } catch (FileNotFoundException ex) {
            System.out.println("Error "+ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ControladorPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
