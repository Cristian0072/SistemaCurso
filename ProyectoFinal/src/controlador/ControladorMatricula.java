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
import modelo.Matricula;

/**
 * fecha: 26/12/2022
 *
 * @author: CEAS
 */
public class ControladorMatricula {

    private Matricula matricula;
    private ListaEnlazada<Matricula> lista;

    public ListaEnlazada<Matricula> getLista() {
        if (lista == null) {
            lista = listar();
        }
        return lista;
    }

    public void setLista(ListaEnlazada<Matricula> lista) {
        this.lista = lista;
    }

    public Matricula getMatricula() {
        if (matricula == null) {
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Boolean guardar(ListaEnlazada<Matricula> matricula) {
        try {
            FileWriter file = new FileWriter("Datos" + File.separatorChar + getMatricula().getClass().getSimpleName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(matricula) + "\n");
            file.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }

    public ListaEnlazada<Matricula> listar() {
        FileReader file = null;
        try {
            file = new FileReader("Datos" + File.separatorChar + getMatricula().getClass().getSimpleName() + ".json");
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

    public Boolean actualizar(ListaEnlazada<Matricula> listaEnlazada) {
        return guardar(lista);
    }

 /*   public static void main(String[] args) {
        try {
            Matricula matricula = new Matricula();
            matricula.setIdMatricula(1);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            matricula.setFechaEmision(format.format(new Date()));
            ControladorPeriodo periodo = new ControladorPeriodo();
            Periodo periodo1 = new Periodo();
            periodo1=periodo.listar().obtener(0);
            matricula.setPeriodo(periodo1);
            ControladorMatricula matricula1 = new ControladorMatricula();
            matricula1.getLista().insertar(matricula);
            matricula1.guardar(matricula1.getLista());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());

        }
    }*/
}
