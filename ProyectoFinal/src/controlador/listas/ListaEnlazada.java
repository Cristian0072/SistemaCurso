/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.listas;

import controlador.listas.excepciones.ListaNullExcepction;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import controlador.listas.excepciones.TamanioNoEncontradaException;

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

    public E eliminar(Integer pos) throws ListaNullExcepction, TamanioNoEncontradaException {
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
            throw new ListaNullExcepction();
        }

    }


}
