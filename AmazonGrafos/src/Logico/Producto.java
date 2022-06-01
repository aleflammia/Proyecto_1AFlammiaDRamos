/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logico;

/**
 *
 * @author Alessandro
 */
public class Producto {
    
    private static int idProducto = 0;
    private String nombre;
    private int stock;
    private NodoAlmacen almacen;

    public Producto(String nombre, int stock, NodoAlmacen almacen) {
        super();
        this.idProducto += 1;  
        this.nombre = nombre;
        this.stock = stock;
        this.almacen = almacen;
    }

    public String getNombre() {
            return nombre;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
    public int getStock() {
            return stock;
    }
    public void setStock(int stock) {
            this.stock = stock;
    }
    public NodoAlmacen getAlmacen() {
        return almacen;
    }
    public void setAlmacen(NodoAlmacen almacen) {
        this.almacen = almacen;
    }
}
