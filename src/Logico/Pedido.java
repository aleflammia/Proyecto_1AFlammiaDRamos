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
public class Pedido {
    private static int idPedido;
    private ListaProducto productosPedido;

    public Pedido() {
            super();
            this.idPedido += 1;
            this.productosPedido = new ListaProducto();
    }

    public ListaProducto getListaProductoPedido() {
        return productosPedido;
    }

    public void setListaProductosPedido(ListaProducto productosPedido) {
        this.productosPedido = productosPedido;
    }
}
