/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Visual;

import EstructurasAuxiliares.ListaArista;
import EstructurasAuxiliares.ListaNodoAlmacen;
import Logico.Amazon;
import Logico.Arista;
import Logico.NodoAlmacen;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alessandro
 */


public class CrearAlmacen extends javax.swing.JDialog {
    
    DefaultListModel<String> modeloRutasDisponible = new DefaultListModel<>();
    DefaultListModel<String> modeloRutasSeleccionado = new DefaultListModel<>();
    ListaArista listaArista = new ListaArista();
    
    public CrearAlmacen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        cargarList();
        listaRutasSeleccionadas.setModel(modeloRutasSeleccionado);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        crearPedido = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        scrollPanelDFS = new javax.swing.JScrollPane();
        listaRutasSeleccionadas = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaRutasDisponibles = new javax.swing.JList<>();
        lblTitulo3 = new javax.swing.JLabel();
        ingresar = new javax.swing.JButton();
        lblTitulo5 = new javax.swing.JLabel();
        lblTitulo7 = new javax.swing.JLabel();
        almacenNombre = new javax.swing.JTextField();
        lblTitulo8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crearPedido.setText("Aceptar");
        crearPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearPedidoActionPerformed(evt);
            }
        });
        jPanel2.add(crearPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 190, 40));

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 190, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 570, 80));

        scrollPanelDFS.setBackground(new java.awt.Color(255, 255, 255));
        scrollPanelDFS.setBorder(null);

        listaRutasSeleccionadas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaRutasSeleccionadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaRutasSeleccionadasMouseClicked(evt);
            }
        });
        scrollPanelDFS.setViewportView(listaRutasSeleccionadas);

        jPanel1.add(scrollPanelDFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 240, 120));

        ListaRutasDisponibles.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaRutasDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaRutasDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ListaRutasDisponibles);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 230, 120));

        lblTitulo3.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo3.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo3.setText("Nombre");
        jPanel1.add(lblTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, -1));

        ingresar.setText(">>");
        ingresar.setEnabled(false);
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });
        jPanel1.add(ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 60, 60));

        lblTitulo5.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo5.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo5.setText("Rutas Seleccionadas");
        jPanel1.add(lblTitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 230, -1));

        lblTitulo7.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo7.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo7.setText("> Crear Almacen");
        jPanel1.add(lblTitulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, -1));

        almacenNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenNombreActionPerformed(evt);
            }
        });
        jPanel1.add(almacenNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 180, 30));

        lblTitulo8.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo8.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo8.setText("Rutas disponibles");
        jPanel1.add(lblTitulo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearPedidoActionPerformed
        if (almacenNombre.getText().length() > 0 && verificarNombre(almacenNombre.getText()) == false) {
            if (listaArista.getSize() > 1 ) {
                NodoAlmacen almacenNuevo = new NodoAlmacen(almacenNombre.getText());
                System.out.println("Almacen " + almacenNuevo.getNombre());
                for (Arista arista : listaArista.getAristas()) {
                    System.out.println("Conexion con " + arista.getNodoDestino().getNombre());
                    arista.setNodoOrigen(almacenNuevo);
                    almacenNuevo.getListaArista().add(arista);
                }
                ListaNodoAlmacen listaNodoAlmacenAux = new ListaNodoAlmacen();
                listaNodoAlmacenAux.add(almacenNuevo);
                for (NodoAlmacen almacen : Amazon.getInstance().getListaMisAlmacenes().getAlmacenes()) {
                    listaNodoAlmacenAux.add(almacen);
                }
                Amazon.getInstance().getListaMisAlmacenes().eliminarTodos();
                for (NodoAlmacen almacen : listaNodoAlmacenAux.getAlmacenes()) {
                     Amazon.getInstance().addAlmacen(almacen);
                }
                modeloRutasSeleccionado.removeAllElements();
                listaArista.eliminarTodos();
                JOptionPane.showMessageDialog(null, "El almacen fue agregado correctamente");
                cargarList();
            }else{
                JOptionPane.showMessageDialog(null, "La cantidad de rutas seleccionadas no es suficiente");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nombre no valido");
        }
    }//GEN-LAST:event_crearPedidoActionPerformed

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        int peso = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el peso (Distancia) para la conexi??n con el " + ListaRutasDisponibles.getSelectedValue(), "Agregar peso"));
        Arista a = new Arista(null, Amazon.getInstance().getAlmacen(ListaRutasDisponibles.getSelectedValue().substring(ListaRutasDisponibles.getSelectedValue().indexOf(" ")+1)), peso);
        listaArista.add(a);
        modeloRutasSeleccionado.addElement(ListaRutasDisponibles.getSelectedValue() + " | Peso: " + peso);
        modeloRutasDisponible.remove(ListaRutasDisponibles.getSelectedIndex());
        ingresar.setEnabled(false);
    }//GEN-LAST:event_ingresarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void ListaRutasDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaRutasDisponiblesMouseClicked
        int index = -1;
        index = ListaRutasDisponibles.getSelectedIndex();
        if (index != -1) {
            ingresar.setEnabled(true);
        }
    }//GEN-LAST:event_ListaRutasDisponiblesMouseClicked

    private void listaRutasSeleccionadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaRutasSeleccionadasMouseClicked

    }//GEN-LAST:event_listaRutasSeleccionadasMouseClicked

    private void almacenNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacenNombreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CrearAlmacen dialog = new CrearAlmacen(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaRutasDisponibles;
    private javax.swing.JTextField almacenNombre;
    private javax.swing.JButton crearPedido;
    private javax.swing.JButton ingresar;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulo5;
    private javax.swing.JLabel lblTitulo7;
    private javax.swing.JLabel lblTitulo8;
    private javax.swing.JList<String> listaRutasSeleccionadas;
    private javax.swing.JScrollPane scrollPanelDFS;
    // End of variables declaration//GEN-END:variables

    private void cargarList() {
        modeloRutasDisponible.removeAllElements();
        ListaRutasDisponibles.setModel(modeloRutasDisponible);
        for (NodoAlmacen almacen : Amazon.getInstance().getListaMisAlmacenes().getAlmacenes()) {
            modeloRutasDisponible.addElement("Almacen " + almacen.getNombre());
            System.out.println("Almacen " + almacen.getNombre());
        }
    }

    private boolean verificarNombre(String nombre) {
        for (NodoAlmacen almacen : Amazon.getInstance().getListaMisAlmacenes().getAlmacenes()) {
            if (almacen.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
}
