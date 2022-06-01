package EstructurasAuxiliares;

import Logico.NodoAlmacen;

public class Cola {
    
    class node{
        private NodoAlmacen infoAlmacen;
        private node sig;
        
        public node(NodoAlmacen infoAlmacen, node sig){
            this.infoAlmacen = infoAlmacen;
            this.sig = sig;
        }
    }
    
    private node cabecera;
    private node cola;
    private int longitud;
    
    public Cola(){
        cabecera = cola = null;
        longitud = 0;
    }
    
    public int longitud(){
        return longitud;
    }
    
    public boolean vacia(){
        return (cabecera == null);
    }
    
    public void encolar(NodoAlmacen almacen){
        node n = new node(almacen, null);
        if (longitud == 0) {
            cabecera = n;
        }else{
            cola.sig = n;
        }
        cola = n;
        longitud++;
    }
    
    public NodoAlmacen desencolar(){
        NodoAlmacen auxAlmacen;
        if (vacia()) {
            return null;
        }
        auxAlmacen = cabecera.infoAlmacen;
        cabecera = cabecera.sig;
        longitud--;
        if (longitud == 0) {
            cola = null;
        }
        
        return auxAlmacen;
    }
}