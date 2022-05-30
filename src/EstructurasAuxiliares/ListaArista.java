/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasAuxiliares;

import Logico.Arista;
import java.util.Arrays;

/**
 *
 * @author Alessandro
 */
public class ListaArista {
    
    private Arista[] aristas;
    private int capacidad;
    private int cont;
    
    public ListaArista(){
        aristas = new Arista[1];
        capacidad = 1;
    }
    
    public ListaArista(int capacidad){
        capacidad = capacidad;
        aristas = new Arista[capacidad];
    }
    
    public int getSize(){
        return cont;
    }
    
    
    public Arista[] getAristas(){
        return aristas;
    }
    
    public Arista getAristaByIndex(int index){
        return aristas[index];
    }
    
    public void add(Arista arista){
        cont += 1;
        if (cont > capacidad) {
            capacidad += 1;
            aristas = Arrays.copyOf(aristas, capacidad);
        }
        aristas[cont - 1] = arista;
    }
}
