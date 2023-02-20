package controlador.listas;

import controlador.listas.excepciones.AtributoException;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import controlador.listas.excepciones.TamanioNoEncontradaException;
import controlador.utilidades.Utilidades;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author SONY VAIO
 */
public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer tamanio;

    public ListaEnlazada() {
        cabecera = null;
        tamanio = 0;
    }

    public Boolean estaVacia() {
        return cabecera == null;
    }

    public void insertar(E dato) {
        NodoLista<E> nodo = new NodoLista<>(dato, null);
        if (estaVacia()) {
            this.cabecera = nodo;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodo);
        }
        tamanio++;
    }

    public void insertarCabecera(E dato) {
        if (estaVacia()) {
            insertar(dato);
        } else {
            NodoLista<E> nodo = new NodoLista<>(dato, null);
            nodo.setSiguiente(cabecera);
            cabecera = nodo;
            tamanio++;
        }
    }

    public void modificar(E dato, Integer pos) throws PosicionNoEncontradaException {
        if (!estaVacia()) {
            insertar(dato);
        } else if (pos >= 0 && pos < tamanio) {
            if (pos == 0) {
                cabecera.setDato(dato);
            } else {
                NodoLista<E> aux = cabecera;
                for (int i = 0; i < pos; i++) {
                    aux = aux.getSiguiente();
                }
                aux.setDato(dato);
            }
        } else {
            throw new PosicionNoEncontradaException();
        }
    }

    public void imprimir() {
        System.out.println("--------------LISTA ENLAZADA------------------");
        NodoLista<E> aux = cabecera;
        while (aux != null) {
            System.out.println(aux.getDato().toString() + "\t");
            aux = aux.getSiguiente();
        }
        System.out.println("\n--------------------------------------");
    }

    public E obtener(Integer pos) throws PosicionNoEncontradaException, ListaNullException {

        if (!estaVacia()) {
            E dato = null;
            if (pos >= 0 && pos < tamanio) {
                if (pos == 0) {
                    dato = cabecera.getDato();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                }
            } else {
                throw new PosicionNoEncontradaException();
            }
            return dato;
        } else {
            throw new ListaNullException();
        }
    }

    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public E eliminar(Integer pos) throws ListaNullException, TamanioNoEncontradaException {
        if (!estaVacia()) {
            E dato = null;
            if (pos >= 0 && pos < tamanio) {
                if (pos == 0) {
                    dato = cabecera.getDato();
                    cabecera = cabecera.getSiguiente();
                    tamanio--;

                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    tamanio--;
                }
            } else {
                throw new TamanioNoEncontradaException();
            }
            return dato;
        } else {
            throw new ListaNullException();
        }

    }
    //método para transformar lista a un arreglo

    public E[] toArray() {
        E[] arreglo = null;
        if (tamanio > 0) {
            arreglo = (E[]) Array.newInstance(cabecera.getDato().getClass(), tamanio);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < tamanio; i++) {
                arreglo[i] = aux.getDato();
                aux = aux.getSiguiente();
            }
        }
        return arreglo;
    }

    //método para convertir un arreglo en una lista enlazada
    public ListaEnlazada<E> toLista(E[] arreglo) {
        vaciarLista();
        for (int i = 0; i < arreglo.length; i++) {
            insertar(arreglo[i]);
        }
        return this;
    }

    public void vaciarLista() {
        cabecera = null;
        tamanio = 0;
    }

    public ListaEnlazada<E> quickSort(ListaEnlazada<E> lista, String atributo) throws PosicionNoEncontradaException, IllegalArgumentException, IllegalAccessException, AtributoException, NoSuchMethodException, InvocationTargetException {
        if (lista.getTamanio() > 1) {
            E[] arreglo = lista.toArray();
            quickSort(arreglo, 0, arreglo.length - 1, atributo);
            return toLista(arreglo);
        }
        return lista;
    }

    private void quickSort(E[] arreglo, int inicio, int fin, String atributo) throws AtributoException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (inicio < fin) {
            E pivote = arreglo[(inicio + fin) / 2];
            int i = inicio;
            int j = fin;
            Field field = Utilidades.obtenerAtributo(pivote.getClass(), atributo);
            if (field == null) {
                throw new AtributoException();
            }
            field.setAccessible(true);
            Object elemento = field.get(pivote);
            while (i <= j) {
                while (compararDatos(field.get(arreglo[i]), elemento)) {
                    i++;
                }
                while (compararDatos(elemento, field.get(arreglo[j]))) {
                    j--;
                }
                if (i <= j) {
                    E aux = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = aux;
                    i++;
                    j--;
                }
            }

            if (inicio < j) {
                quickSort(arreglo, inicio, j, atributo);
            }
            if (i < fin) {
                quickSort(arreglo, i, fin, atributo);
            }
        }
    }

    private Boolean compararDatos(Object dato, Object dato1) throws AtributoException, IllegalArgumentException, IllegalAccessException {
        if (Utilidades.isNumber(dato.getClass())) {
            if (((Number) dato).floatValue() < ((Number) dato1).floatValue()) {
                return true;
            }
        } else if (Utilidades.isString(dato.getClass())) {
            if (dato.toString().compareToIgnoreCase(dato1.toString()) < 0) {
                return true;
            }
        } else if (dato.getClass().isEnum()) {
            if (dato.toString().compareToIgnoreCase(dato1.toString()) < 0) {
                return true;
            }
        }
        return false;
    }

    public ListaEnlazada<E> secuencial(String atributo, Object dato) throws Exception {
        Class<E> clazz = null;
        ListaEnlazada<E> lista = new ListaEnlazada<>();
        if (tamanio > 0) {
            clazz = (Class<E>) cabecera.getDato().getClass();
            Boolean esObjeto = Utilidades.isObject(clazz);
            E[] arreglo = toArray();
            for (int i = 0; i < arreglo.length; i++) {
                if (esObjeto) {
                    Boolean bandera = buscarPosicionObjeto(arreglo[i], dato, clazz, atributo);
                    if (bandera) {
                        lista.insertar(arreglo[i]);
                    }
                } else {
                    Boolean bandera = buscarPosicionPrimitivo(arreglo[i], dato);
                    if (bandera) {
                        lista.insertar(arreglo[i]);
                    }
                }
            }
        }
        return lista;
    }

    private Boolean buscarPosicionPrimitivo(Object datoArreglo, Object buscar) {
        if (Utilidades.isNumber(buscar.getClass())) {
            if (((Number) datoArreglo).floatValue() == ((Number) buscar).floatValue()) {
                return true;
            }
        } else if (Utilidades.isString(buscar.getClass())) {
            if (datoArreglo.toString().toLowerCase().startsWith(buscar.toString().toLowerCase())
                    || datoArreglo.toString().toLowerCase().endsWith(buscar.toString().toLowerCase())
                    || datoArreglo.toString().toLowerCase().contains(buscar.toString().toLowerCase())
                    || datoArreglo.toString().equalsIgnoreCase(buscar.toString().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private Boolean buscarPosicionObjeto(E objeto, Object buscar, Class clazz, String atributo) throws Exception {
        Field field = Utilidades.obtenerAtributo(clazz, atributo);
        if (field == null) {
            throw new AtributoException();
        } else {
            field.setAccessible(true);
            Object aux = field.get(objeto);
            return buscarPosicionPrimitivo(aux, buscar);
        }
    }

}
