package Logico;

import EstructurasAuxiliares.ListaNodoAlmacen;
import EstructurasAuxiliares.ListaProducto;
import javax.swing.JOptionPane;

/**
 *
 * @author Alessandro
 */
public class Main {
    
    public static void main(String[] args) {
        while(true){
            int opc = Integer.parseInt(JOptionPane.showInputDialog(null, "(1) Cargar Archivo \n(2) Reportar \n(3) Hacer Pedido \n(4)Agregar un nuevo Almacen - Para implementar en lo gráfico de verdad \n(5) Agregar un nuevo Camino - Para implementar en lo gráfico de verdad \n(6) Gestión de Stock - Para implementar en lo gráfico de verdad", "Menú", JOptionPane.QUESTION_MESSAGE));
            if (opc == 1) {
                String archivo = JOptionPane.showInputDialog(null, "Ingresa la ruta del archivo", "Ruta del archivo", JOptionPane.QUESTION_MESSAGE);
                Amazon.getInstance().cargarDatos(archivo);
            }else if (opc == 2) {
                Amazon.getInstance().reportar();
            }else if (opc == 3) {
                String almacenes = "";
                for (NodoAlmacen almacen : Amazon.getInstance().getListaMisAlmacenes().getAlmacenes()) {
                    almacenes += almacen.getNombre() + "\n";
                }
                String nombreAlmacen = JOptionPane.showInputDialog(null, "Elige un Almacen\n\n" + almacenes, "Almacen", JOptionPane.QUESTION_MESSAGE);
                NodoAlmacen almacen = Amazon.getInstance().getAlmacen(nombreAlmacen);
                
                int cant = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de productos", "Productos", JOptionPane.QUESTION_MESSAGE));
                
                ListaProducto productos = new ListaProducto();
                String productosAlmacen = "";
                for (Producto producto : almacen.getListaProducto().getProductos()) {
                    productosAlmacen += producto.getNombre() + " " + producto.getStock() + "\n";
                }
                for (int i = 0; i < cant; i++) {
                    String nombreProducto = JOptionPane.showInputDialog(null, "Elige un Producto (" + i+1 + ") \n\n" + productosAlmacen, "Productos", JOptionPane.QUESTION_MESSAGE);
                    productos.add(Amazon.getInstance().buscarProducto(nombreProducto, nombreAlmacen));
                }
                
                Amazon.getInstance().addPedido(productos);
            }
        }
        
    }
}
