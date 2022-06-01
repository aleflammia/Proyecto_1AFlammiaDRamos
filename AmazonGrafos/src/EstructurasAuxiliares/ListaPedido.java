/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasAuxiliares;

import Logico.Pedido;
import java.util.Arrays;

/**
 *
 * @author Alessandro
 */
public class ListaPedido {
    
    private Pedido[] pedidos;
    private int capacidad;
    private int cont;
    
    public ListaPedido(){
        pedidos = new Pedido[1];
        capacidad = 1;
    }
    
    public ListaPedido(int capacidad){
        pedidos = new Pedido[capacidad];
    }
    
    public int getSize(){
        return cont;
    }
    
    public Pedido[] getPedidos(){
        return pedidos;
    }
    
    public Pedido getPedidoByIndex(int index){
        return pedidos[index];
    }
    
    public void add(Pedido pedido){
        cont += 1;
        if (cont > capacidad) {
            capacidad += 1;
            pedidos = Arrays.copyOf(pedidos, capacidad);
        }
        pedidos[cont - 1] = pedido;
    }
}
