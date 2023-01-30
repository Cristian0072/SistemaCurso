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
import modelo.Docente;

/**
 * fecha: 25/01/2023
 *
 * @author: CEAS
 */
public class ControladorDocente {
    
    private ListaEnlazada<Docente> lista;
    private Docente docente;
    
    public ListaEnlazada<Docente> getLista() {
        if (lista == null) {
            lista = listar();
        }
        return lista;
    }
    
    public void setLista(ListaEnlazada<Docente> lista) {
        this.lista = lista;
    }
    
    public Docente getDocente() {
        if (docente == null) {
            docente = new Docente();
        }
        return docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    public Boolean guardar(ListaEnlazada<Docente> docente) {
        FileWriter file = null;
        try {
            file = new FileWriter("Datos" + File.separatorChar + getDocente().getClass().getSimpleName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(docente));
            file.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }
    
    public ListaEnlazada<Docente> listar() {
        FileReader file = null;
        try {
            file = new FileReader("Datos" + File.separatorChar + getDocente().getClass().getSimpleName() + ".json");
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
    
    public Boolean actualizar(ListaEnlazada<Docente> lista) {
        return guardar(lista);
    }
    
    /*public static void main(String[] args) {
        ControladorDocente docente = new ControladorDocente();
        docente.getDocente().setIdDocente(1);
        docente.getDocente().setAniosExpDocente(4);
        docente.getDocente().setAniosExpLaboral(5);
        docente.getDocente().setApellidos("Torres Guillen");
        docente.getDocente().setNombres("Juan Rafael");
        docente.getDocente().setCiudad("Cuenca");
        docente.getDocente().setDireccion("Avn. Huaynacapa y Los Naranjos");
        docente.getDocente().setFechaNacimiento("01/01/1990");
        docente.getDocente().setGenero(Generos.MASCULINO);
        docente.getDocente().setIdentificacion("1120789632");
        docente.getDocente().setTelefono("0998563789");
        docente.getDocente().setTituloCuartoNivel("Doctorado");
        docente.getDocente().setTituloTercerNivel("Ingenieria");
        ListaEnlazada<Docente> listaEnlazada = new ListaEnlazada<>();
        listaEnlazada.insertar(docente.getDocente());
        docente.guardar(listaEnlazada);
        docente.listar().imprimir();
        
    }*/
}
