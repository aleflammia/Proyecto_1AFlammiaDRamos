package Logico;

import EstructurasAuxiliares.Cola;
import EstructurasAuxiliares.ListaNodoAlmacen;
import EstructurasAuxiliares.ListaPedido;
import EstructurasAuxiliares.ListaProducto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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
                if (productoStock == null) {
                    JOptionPane.showMessageDialog(null, "El producto " + producto.getNombre() + " no fue encontrado en otro almacen");
                }else{
                    productoStock.setStock(productoStock.getStock() - 1);
                    listaProductos.add(productoStock);
                }
            } else {
                producto.setStock(producto.getStock() - 1);
                listaProductos.add(producto);
            }
        }
        JOptionPane.showMessageDialog(null, "El pedido fue realizado con exito");
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
                if (productoTemp != null) {
                    if (productoTemp.getNombre().equalsIgnoreCase(producto.getNombre())) {
                        if (productoTemp.getStock() > 0) {
                            if (dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia() <= distMayor) {
                                almacen = dijkstra(producto.getAlmacen(), almacenDestino);
                                productoStock = productoTemp;
                                distMayor = (int) dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia();
                            }
                        }
                    }
                }
                
            }
        }
        if (almacen == null || productoStock == null) {
            return null;
        }
        
        datos += "Se tomó un producto del almacen " + almacen.getNombre() + ", que es el más cercano con " + almacen.getMinDistancia() + "KM\n\nSu Trayectoria es: ";

        ListaNodoAlmacen trayectoria = new ListaNodoAlmacen();
        while (almacen != null) {
            trayectoria.add(almacen);
            almacen = almacen.getNodoAnterior();
        }
        Collections.reverse(Arrays.asList(trayectoria.getAlmacenes()));
        int cont = 0;
        for (NodoAlmacen almacenAux : trayectoria.getAlmacenes()) {
            if (cont != trayectoria.getSize()-1) {
                datos += almacenAux.getNombre() + " -> ";
            }else{
                datos += almacenAux.getNombre();
            }
            cont++;
        }
        JOptionPane.showMessageDialog(null, datos);
        return productoStock;
    }

    private int distanciaMayor(Producto producto) {
        int distMayor = 0;
        for (NodoAlmacen almacenDestino : misAlmacenes.getAlmacenes()) {
            for (Producto productoTemp : almacenDestino.getListaProducto().getProductos()) {
                if (productoTemp != null) {
                    if (productoTemp.getNombre().equalsIgnoreCase(producto.getNombre())) {
                        if (productoTemp.getStock() > 0) {
                            if (dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia() > distMayor) {
                                distMayor = (int) dijkstra(producto.getAlmacen(), almacenDestino).getMinDistancia();
                            }
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

    public void setAlmacenFalse() {
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
                    //System.out.println(datos);
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

    public ListaNodoAlmacen cargarDatos(String direccion) {
        BufferedReader obj = null;
        ListaNodoAlmacen almacenesCargados = null;
        try {
            almacenesCargados = new ListaNodoAlmacen();
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
                            if ((almacenOrigen == null || almacenDestino == null) && misAlmacenes.getAlmacenes() != null) {
                                for (NodoAlmacen almacenAux2 : misAlmacenes.getAlmacenes()) {
                                    if (almacenAux2.getNombre().equalsIgnoreCase(aristaDatos[0])) {
                                        almacenOrigen = almacenAux2;
                                    }
                                    if (almacenAux2.getNombre().equalsIgnoreCase(aristaDatos[1])) {
                                        almacenDestino = almacenAux2;

                                    }
                                }
                            }
                            
                            almacenOrigen.getListaArista().add(new Arista(almacenOrigen, almacenDestino, Integer.parseInt(aristaDatos[2])));
                        }
                    }
                    datosCargados += (strng+"\n");
                    System.out.println(strng);
                }
            } catch (IOException ex) {
                Logger.getLogger(Amazon.class.getName()).log(Level.SEVERE, null, ex);
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

        return almacenesCargados;
    }

    public void cargarFichero(String direccion) {
        BufferedReader obj = null;
        try {
            NodoAlmacen almacen = null;
            int control = 0;
            File doc = new File(direccion);
            obj = new BufferedReader(new FileReader(doc));
            String strng;
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
                            misAlmacenes.add(almacen);
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

                            for (NodoAlmacen almacenAux : misAlmacenes.getAlmacenes()) {
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
                }
            } catch (IOException ex) {
                Logger.getLogger(Amazon.class.getName()).log(Level.SEVERE, null, ex);
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

    public void actualizarRepositorioArchivo(ListaNodoAlmacen almacenesCargados) {
        try {
            boolean encontrado = false;
            ListaNodoAlmacen almacenesAux = new ListaNodoAlmacen();
            for (NodoAlmacen almacen1 : almacenesCargados.getAlmacenes()) {
                for (NodoAlmacen almacen2 : misAlmacenes.getAlmacenes()) {
                    if (almacen1.getNombre().equalsIgnoreCase(almacen2.getNombre())) {
                        encontrado = true;
                    }
                }
                if (encontrado) {
                    JOptionPane.showMessageDialog(null, "El almacen " + almacen1.getNombre() + " no pudo se guardado por que ya se encuentra en la lista");
                }else{
                    almacenesAux.add(almacen1);
                }
                encontrado = false;
            }
            
            if (almacenesAux.getSize() <= 0) {
                return;
            }else{
                ListaNodoAlmacen almacenAux2 = misAlmacenes;
                misAlmacenes = null;
                misProductos = null;
                for (NodoAlmacen a: almacenAux2.getAlmacenes()) {
                    almacenesAux.add(a);
                }
                misAlmacenes = new ListaNodoAlmacen();
                for (NodoAlmacen almacen : almacenesAux.getAlmacenes()) {
                    misAlmacenes.add(almacen);
                }
            }
            FileWriter archivoDatos = null;
            archivoDatos = new FileWriter("src/Archivos/Amazon.txt");
            String datos = "";
            datos = "Almacenes;\n";
            for (NodoAlmacen almacen : misAlmacenes.getAlmacenes()) {
                datos += "Almacen " + almacen.getNombre() + ":\n";
                for (int i = 0; i < almacen.getListaProducto().getSize(); i++) {
                    datos += almacen.getListaProducto().getProductos()[i].getNombre() + "," + almacen.getListaProducto().getProductos()[i].getStock();
                    if (i == almacen.getListaProducto().getSize() - 1) {
                        datos += ";\n";
                    } else {
                        datos += "\n";
                    }
                }
            }
            datos += "Rutas;\n";
            for (NodoAlmacen almacen : misAlmacenes.getAlmacenes()) {
                for (Arista arista : almacen.getListaArista().getAristas()) {
                    datos += arista.getNodoOrigen().getNombre() + "," + arista.getNodoDestino().getNombre() + "," + arista.getDistancia() + "\n";
                }
            }
            archivoDatos.write("");
            archivoDatos.write(datos);
            archivoDatos.close();
            JOptionPane.showMessageDialog(null, "Archivo creado Correctamente");

        } catch (IOException ex) {
            Logger.getLogger(Amazon.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el repositorio " + ex);
        }
    }
    
    public void actualizarRepositorioSobrescribir() {
        try {
            FileWriter archivoDatos = null;
            archivoDatos = new FileWriter("src/Archivos/Amazon.txt");
            String datos = "";
            datos = "Almacenes;\n";
            for (NodoAlmacen almacen : misAlmacenes.getAlmacenes()) {
                datos += "Almacen " + almacen.getNombre() + ":\n";
                for (int i = 0; i < almacen.getListaProducto().getSize(); i++) {
                    datos += almacen.getListaProducto().getProductos()[i].getNombre() + "," + almacen.getListaProducto().getProductos()[i].getStock();
                    if (i == almacen.getListaProducto().getSize() - 1) {
                        datos += ";\n";
                    } else {
                        datos += "\n";
                    }
                }
            }
            datos += "Rutas;\n";
            for (NodoAlmacen almacen : misAlmacenes.getAlmacenes()) {
                for (Arista arista : almacen.getListaArista().getAristas()) {
                    datos += arista.getNodoOrigen().getNombre() + "," + arista.getNodoDestino().getNombre() + "," + arista.getDistancia() + "\n";
                }
            }
            archivoDatos.write("");
            archivoDatos.write(datos);
            archivoDatos.close();
            JOptionPane.showMessageDialog(null, "Repositorio actualizado Correctamente");

        } catch (IOException ex) {
            Logger.getLogger(Amazon.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el repositorio " + ex);
        }
    }

    public void reportar() {
        setAlmacenFalse();
        String reporte = "";
        for (int i = 0; i < 2; i++) {
            long inicioTiempo = System.nanoTime();
            if (i == 0) {
                reporte += reporteBFS(misAlmacenes.getAlmacenByIndex(0));
            } else {
                reporte += reporteDFS(misAlmacenes.getAlmacenByIndex(0));
            }
            setAlmacenFalse();
            long finTiempo = System.nanoTime();

            double tiempo = (double) ((finTiempo - inicioTiempo) / 1000000);
            reporte += "\nEl tiempo de corrida fue de " + tiempo + " Milisegundos\n";
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    public Producto buscarProducto(String nombreProducto, String nombreAlmacen) {
        NodoAlmacen almacen = getAlmacen(nombreAlmacen);
        for (Producto producto : almacen.getListaProducto().getProductos()) {
            if (producto != null) {
                if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                    return producto;
                }
            }
            
        }
        return null;
    }
}
