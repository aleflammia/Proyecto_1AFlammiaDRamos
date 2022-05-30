/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasAuxiliares;

import Logico.NodoAlmacen;
import java.util.Arrays;

/**
 *
 * @author Alessandro
 */
public class ListaNodoAlmacen{
    
    private NodoAlmacen[] almacenes;
    private int capacidad;
    private int cont;
    
    public ListaNodoAlmacen(){
        almacenes = new NodoAlmacen[1];
        capacidad = 1;
    }
    
    public ListaNodoAlmacen(int capacidad){
        capacidad = capacidad;
        almacenes = new NodoAlmacen[capacidad];
    }
    
    public int getSize(){
        return cont;
    }
    
    public NodoAlmacen[] getAlmacenes(){
        return almacenes;
    }
    
    public NodoAlmacen getAlmacenByIndex(int index){
        return almacenes[index];
    }
    
    public void add(NodoAlmacen almacen){
        cont += 1;
        if (cont > capacidad) {
            capacidad += 1;
            almacenes = Arrays.copyOf(almacenes, capacidad);
        }
        almacenes[cont - 1] = almacen;
    }
}
