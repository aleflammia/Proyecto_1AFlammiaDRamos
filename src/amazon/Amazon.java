package Logico;

import EstructurasAuxiliares.Cola;
import EstructurasAuxiliares.ListaNodoAlmacen;
import EstructurasAuxiliares.ListaPedido;
import EstructurasAuxiliares.ListaProducto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alessandro
 */
public class Amazon {

    private ListaNodoAlmacen misAlmacenes;
    private ListaProducto misProductos;
    private ListaPedido misPedidos;
    private static Amazon amazon;

    public Amazon() {
        this.misAlmacenes = new ListaNodoAlmacen();
        this.misProductos = new ListaProducto();
        this.misPedidos = new ListaPedido();
    }

    public static Amazon getInstance() {
        if (amazon == null) {
            amazon = new Amazon();
        }
        return amazon;
    }

    public ListaNodoAlmacen getListaMisAlmacenes() {
        return misAlmacenes;
    }

    public void setListaMisAlmacenes(ListaNodoAlmacen misAlmacenes) {
        this.misAlmacenes = misAlmacenes;
    }

    public ListaProducto getListaMisProductos() {
        return misProductos;
    }

    public void setListaMisProductos(ListaProducto misProductos) {
        this.misProductos = misProductos;
    }

    public ListaPedido getListaMisPedidos() {
        return misPedidos;
    }

    public void setListaMisPedidos(ListaPedido misPedidos) {
        this.misPedidos = misPedidos;
    }

    /**
     * Funciones para añadir
     */
    public void addAlmacen(NodoAlmacen almacen) {
        misAlmacenes.add(almacen);
    }

    public void addProducto(Producto producto) {
        misProductos.add(producto);
    }

    public void addPedido(ListaProducto productos) {
        Pedido pedido = new Pedido();
        ListaProducto listaProductos = new ListaProducto();
        for (Producto producto : productos.getProductos()) {
            if (producto.getStock() <= 0) {
                JOptionPane.showMessageDialog(null, "Para el producto " + producto.getNombre() + " del almacen " + producto.getAlmacen().getNombre() + " no hay Sotck, por lo que se procederá a buscar en el almacén más cercano\n");
                Producto productoStock = buscarEnOtroAlmacen(producto);
                productoStock.setStock(productoStock.getStock() - 1);
                listaProductos.add(productoStock);
            } else {
                producto.setStock(producto.getStock() - 1);
                listaProductos.add(producto);
            }
        }
        pedido.setListaProductosPedido(listaProductos);
        misPedidos.add(pedido);
    }

    private Producto buscarEnOtroAlmacen(Producto producto) {
        String datos = "";
        NodoAlmacen almacen = null;
        Producto productoStock = null;
        int distMayor = distanciaMayor(producto);
        for (NodoAlmacen almacenDestino : misAlmacenes.getAlmacenes()) {
            for (Producto productoTemp : almacenDestino.getListaProducto().getProductos()) {
                if (productoTemp.getNombre().equalsIgnoreCase(producto.getNombre())) {
                    if (productoTemp.getStock() > 0) {
                        if (dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia() < distMayor) {
                            almacen = dijkstra(producto.getAlmacen(), almacenDestino);
                            productoStock = productoTemp;
                            distMayor = (int) dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia();
                        }
                    }
                }
            }
        }
        datos += "Se tomó un producto del almacen " + almacen.getNombre() + ", que es el más cercano con " + almacen.getMinDistancia() + "KM\n\nSu Trayectoria es: ";

        ListaNodoAlmacen trayectoria = new ListaNodoAlmacen();
        while (almacen != null) {
            trayectoria.add(almacen);
            almacen = almacen.getNodoAnterior();
        }
        invertir(trayectoria.getAlmacenes());
        for (NodoAlmacen almacenAux : trayectoria.getAlmacenes()) {
            datos += almacenAux.getNombre() + " -> ";
        }
        JOptionPane.showMessageDialog(null, datos);
        return productoStock;
    }

    private int distanciaMayor(Producto producto) {
        int distMayor = 0;
        for (NodoAlmacen almacenDestino : misAlmacenes.getAlmacenes()) {
            for (Producto productoTemp : almacenDestino.getListaProducto().getProductos()) {
                if (productoTemp.getNombre().equalsIgnoreCase(producto.getNombre())) {
                    if (productoTemp.getStock() > 0) {
                        if (dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia() > distMayor) {
                            distMayor = (int) dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia();
                        }
                    }
                }
            }
        }

        return distMayor;
    }

    public NodoAlmacen dijkstra(NodoAlmacen nodoOrigen, NodoAlmacen nodoDestino) {
        nodoOrigen.setMinDistancia(0);
        Queue<NodoAlmacen> priorityQueue = new LinkedList<>();
        priorityQueue.add(nodoOrigen);

        while (!priorityQueue.isEmpty()) {
            NodoAlmacen vertex = priorityQueue.poll();

            for (Arista edge : vertex.getListaArista().getAristas()) {
                NodoAlmacen v = edge.getNodoDestino();
//                Vertex u = edge.getStartVertex();
                double weight = edge.getDistancia();
                double minDistance = vertex.getMinDistancia() + weight;

                if (minDistance < v.getMinDistancia()) {
                    priorityQueue.remove(vertex);
                    v.setNodoAnterior(vertex);
                    v.setMinDistancia(minDistance);
                    priorityQueue.add(v);
                }
            }
        }

        ListaNodoAlmacen trayectoria = new ListaNodoAlmacen();
        NodoAlmacen almacen = nodoDestino;
        while (almacen != null) {            
            trayectoria.add(almacen);
            almacen = almacen.getNodoAnterior();
        }
        
        invertir(trayectoria.getAlmacenes());
        return nodoDestino;
    }

    public String reporteBFS(NodoAlmacen root) {

        String datos = "";
        datos += "BFS: \n";
        Cola queue = new Cola();
        root.setVisitado(true);
        queue.encolar(root);
        while (!queue.vacia()) {
            NodoAlmacen actualVertex = queue.desencolar();
            datos += "> Almacen " + actualVertex.getNombre() + "\n";
            for (Producto producto : actualVertex.getListaProducto().getProductos()) {
                datos += "NOMBRE: " + producto.getNombre() + " STOCK: " + producto.getStock() + "\n";
            }
            for (Arista arista : actualVertex.getListaArista().getAristas()) {
                if (!arista.getNodoDestino().isVisitado()) {
                    arista.getNodoDestino().setVisitado(true);
                    queue.encolar(arista.getNodoDestino());
                }
            }
        }
        return datos;
    }

    private void setAlmacenFalse() {
        for (NodoAlmacen NodoAlmacen : misAlmacenes.getAlmacenes()) {
            NodoAlmacen.setVisitado(false);
            for (Arista aristas : NodoAlmacen.getListaArista().getAristas()) {
                aristas.getNodoOrigen().setVisitado(false);
                aristas.getNodoDestino().setVisitado(false);
            }
        }
    }

    public String reporteDFS(NodoAlmacen root) {

        String datos = "";
        datos += "DFS\n";
        Stack<NodoAlmacen> stack = new Stack<>();
        stack.add(root);
        root.setVisitado(true);

        while (!stack.isEmpty()) {
            NodoAlmacen actualVertex = stack.pop();
            datos += "> Almacen " + actualVertex.getNombre() + "\n";
            for (Producto producto : actualVertex.getListaProducto().getProductos()) {
                if (producto.getStock() > 0) {
                    datos += "NOMBRE: " + producto.getNombre() + " STOCK: " + producto.getStock() + "\n";
                }
            }
            for (Arista arista : actualVertex.getListaArista().getAristas()) {
                if (!arista.getNodoDestino().isVisitado()) {
                    arista.getNodoDestino().setVisitado(true);
                    arista.getNodoDestino().setNodoAnterior(arista.getNodoDestino());
                    stack.add(arista.getNodoDestino());
                }
            }
        }
        return datos;
    }

    public void invertir(NodoAlmacen[] almacenes) {
        NodoAlmacen temporal;
        int longitudDeArreglo = almacenes.length;
        for (int x = 0; x < longitudDeArreglo / 2; x++) {
            temporal = almacenes[x];
            int indiceContrario = longitudDeArreglo - x - 1;
            almacenes[x] = almacenes[indiceContrario];
            almacenes[indiceContrario] = temporal;
        }
    }

    public void cargarDatos(String direccion) {
        BufferedReader obj = null;
        try {
            ListaNodoAlmacen almacenesCargados = new ListaNodoAlmacen();
            NodoAlmacen almacen = null;
            int control = 0;
            File doc = new File(direccion);
            obj = new BufferedReader(new FileReader(doc));
            String strng;
            String datosCargados = "";
            try {
                while ((strng = obj.readLine()) != null) {
                    if (strng.equalsIgnoreCase("Almacenes;")) {
                        control = 1;
                    }
                    if (strng.equalsIgnoreCase("Rutas;")) {
                        control = 2;
                    }

                    if (control == 1) {
                        if (strng.contains("Almacen") && !strng.contains(";")) {
                            almacen = new NodoAlmacen(strng.substring(strng.indexOf(" ") + 1, strng.indexOf(":")));
                            almacenesCargados.add(almacen);
                        }
                        if (strng.contains(",") && !strng.contains(";")) {
                            Producto producto = new Producto(strng.substring(0, strng.indexOf(",")), Integer.parseInt(strng.substring(strng.indexOf(",") + 1)), almacen);
                            almacen.getListaProducto().add(producto);
                        } else if (strng.contains(",") && strng.contains(";")) {
                            Producto producto = new Producto(strng.substring(0, strng.indexOf(",")), Integer.parseInt(strng.substring(strng.indexOf(",") + 1, strng.indexOf(";"))), almacen);
                            almacen.getListaProducto().add(producto);
                        }
                    }
                    if (control == 2) {
                        if (!strng.contains(";")) {
                            String[] aristaDatos = strng.split(",");
                            NodoAlmacen almacenOrigen = null, almacenDestino = null;

                            for (NodoAlmacen almacenAux : almacenesCargados.getAlmacenes()) {
                                if (almacenAux.getNombre().equalsIgnoreCase(aristaDatos[0])) {
                                    almacenOrigen = almacenAux;
                                }
                                if (almacenAux.getNombre().equalsIgnoreCase(aristaDatos[1])) {
                                    almacenDestino = almacenAux;
                                }
                            }
                            almacenOrigen.getListaArista().add(new Arista(almacenOrigen, almacenDestino, Integer.parseInt(aristaDatos[2])));
                        }
                    }
                    datosCargados += (strng + "\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(Amazon.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int opc = JOptionPane.showConfirmDialog(null, datosCargados + "\n\nLos datos han sido cargados correctamente, desea actualizar para guardalos en memoria", "Guardar archivos", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION) {
                actualizarRepositorio(almacenesCargados);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Amazon.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                obj.close();
            } catch (IOException ex) {
                Logger.getLogger(Amazon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public NodoAlmacen getAlmacen(String nombre) {
        for (NodoAlmacen almacen : misAlmacenes.getAlmacenes()) {
            if (almacen.getNombre().equalsIgnoreCase(nombre)) {
                return almacen;
            }
        }
        return null;
    }

    private void actualizarRepositorio(ListaNodoAlmacen almacenesCargados) {
        for (NodoAlmacen almacen : almacenesCargados.getAlmacenes()) {
            misAlmacenes.add(almacen);
            for (Producto producto : almacen.getListaProducto().getProductos()) {
                addProducto(producto);
            }
        }
    }

    void reportar() {
        String reporte = "";
        for (int i = 0; i < 2; i++) {
            long inicioTiempo = System.nanoTime();
                if(i == 0) {
                    reporte += reporteBFS(misAlmacenes.getAlmacenByIndex(0));
                }else{
                    reporte += reporteBFS(misAlmacenes.getAlmacenByIndex(0));
                }
            setAlmacenFalse();
            long finTiempo = System.nanoTime();

            double tiempo = (double)((finTiempo - inicioTiempo)/1000000);
            reporte += "\nEl tiempo de corrida fue de " + tiempo + " Milisegundos\n";
        }
        
        JOptionPane.showMessageDialog(null, reporte);
    }

    Producto buscarProducto(String nombreProducto, String nombreAlmacen) {
        NodoAlmacen almacen = getAlmacen(nombreAlmacen);
        for (Producto producto : almacen.getListaProducto().getProductos()) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                return producto;
            }
        }
        return null;
    }
}
