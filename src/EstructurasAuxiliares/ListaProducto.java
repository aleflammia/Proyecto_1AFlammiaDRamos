/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasAuxiliares;

import java.util.Arrays;
import Logico.Producto;

/**
 *
 * @author Alessandro
 */
public class ListaProducto {
    
    private Producto[] productos;
    private int capacidad;
    private int cont;
    
    public ListaProducto(){
        productos = new Producto[1];
        capacidad = 1;
    }
    
    public ListaProducto(int capacidad){
        capacidad = capacidad;
        productos = new Producto[capacidad];
    }
    
    public int getSize(){
        return cont;
    }
    
    public Producto[] getProductos(){
        return productos;
    }
    
    public Producto getProductoByIndex(int index){
        return productos[index];
    }
    
    public void add(Producto producto){
        cont += 1;
        if (cont > capacidad) {
            capacidad += 1;
            productos = Arrays.copyOf(productos, capacidad);
        }
        productos[cont - 1] = producto;
    }
}
