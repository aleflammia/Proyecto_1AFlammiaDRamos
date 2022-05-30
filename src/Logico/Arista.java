/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logico;
/**
 *
 * @author Alessandro
 */
public class Arista {
    private NodoAlmacen nodoOrigen;
    private NodoAlmacen nodoDestino;
    private int distancia;
    
    public Arista(NodoAlmacen nodoOrigen, NodoAlmacen nodoDestino, int distancia){
        this.nodoOrigen = nodoOrigen;
        this.nodoDestino = nodoDestino;
        this.distancia = distancia;
    }

    public NodoAlmacen getNodoOrigen() {
        return nodoOrigen;
    }

    public void setNodoOrigen(NodoAlmacen nodoOrigen) {
        this.nodoOrigen = nodoOrigen;
    }

    public NodoAlmacen getNodoDestino() {
        return nodoDestino;
    }

    public void setNodoDestino(NodoAlmacen nodoDestino) {
        this.nodoDestino = nodoDestino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }    
}
