/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logico;

import EstructurasAuxiliares.ListaArista;
import EstructurasAuxiliares.ListaProducto;

/**
 *
 * @author Alessandro
 */
public class NodoAlmacen {
    private static int idAlmacen;
    private String nombre;
    private boolean visitado;
    private double minDistancia = Double.MAX_VALUE;
    private ListaArista aristas;
    private ListaProducto productos;
    private NodoAlmacen nodoAnterior;
    
    public NodoAlmacen(String nombre) {
        this.idAlmacen += 1;
        this.nombre = nombre;
        this.aristas = new ListaArista();
        this.productos = new ListaProducto();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public double getMinDistancia() {
        return minDistancia;
    }

    public void setMinDistancia(double minDistancia) {
        this.minDistancia = minDistancia;
    }

    public ListaArista getListaArista() {
        return aristas;
    }

    public void setListaArista(ListaArista aristas) {
        this.aristas = aristas;
    }

    public ListaProducto getListaProducto() {
        return productos;
    }

    public void setListaProducto(ListaProducto productos) {
        this.productos = productos;
    }

    public NodoAlmacen getNodoAnterior() {
        return nodoAnterior;
    }

    public void setNodoAnterior(NodoAlmacen nodoAnterior) {
        this.nodoAnterior = nodoAnterior;
    }
}
