/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Visual;

import EstructurasAuxiliares.ListaProducto;
import Logico.Amazon;
import Logico.NodoAlmacen;
import Logico.Producto;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


/**
 *
 * @author Alessandro
 */


public class RealizarPedido extends javax.swing.JDialog {
    
    DefaultListModel<String> modeloDisponible = new DefaultListModel<>();
    DefaultListModel<String> modeloSeleccionado = new DefaultListModel<>();
    DefaultComboBoxModel<String> modeloCbx = new DefaultComboBoxModel<>();
    ListaProducto listaPedido = new ListaProducto();
    
    public RealizarPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        cargarOpcionesCbx();
        cargarList();
        listSeleccionado.setModel(modeloSeleccionado);
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
        listSeleccionado = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDisponible = new javax.swing.JList<>();
        lblTitulo3 = new javax.swing.JLabel();
        ingresar = new javax.swing.JButton();
        almacenSeleccionar = new javax.swing.JComboBox<>();
        lblTitulo4 = new javax.swing.JLabel();
        lblTitulo5 = new javax.swing.JLabel();
        lblTitulo6 = new javax.swing.JLabel();

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
        jPanel2.add(crearPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 190, 40));

        jButton5.setText("Cerrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 190, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 860, 80));

        scrollPanelDFS.setBackground(new java.awt.Color(255, 255, 255));
        scrollPanelDFS.setBorder(null);

        listSeleccionado.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listSeleccionado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSeleccionadoMouseClicked(evt);
            }
        });
        scrollPanelDFS.setViewportView(listSeleccionado);

        jPanel1.add(scrollPanelDFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 400, 220));

        listDisponible.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listDisponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listDisponibleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listDisponible);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 330, 220));

        lblTitulo3.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo3.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo3.setText("Seleccionar Almacen");
        jPanel1.add(lblTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 65, 250, -1));

        ingresar.setText(">>");
        ingresar.setEnabled(false);
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });
        jPanel1.add(ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 225, 80, 60));

        almacenSeleccionar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        almacenSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenSeleccionarActionPerformed(evt);
            }
        });
        jPanel1.add(almacenSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 330, 40));

        lblTitulo4.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo4.setFont(new java.awt.Font("Arial Black", 0, 25)); // NOI18N
        lblTitulo4.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo4.setText("> Realizar Pedido");
        jPanel1.add(lblTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 430, -1));

        lblTitulo5.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo5.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo5.setText("Productos Disponibles");
        jPanel1.add(lblTitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 320, -1));

        lblTitulo6.setBackground(new java.awt.Color(153, 153, 153));
        lblTitulo6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo6.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo6.setText("Productos Seleccionados");
        jPanel1.add(lblTitulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 320, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearPedidoActionPerformed
        if (listaPedido.getSize() > 0) {
            Amazon.getInstance().addPedido(listaPedido);
            modeloSeleccionado.removeAllElements();
            listaPedido.eliminarTodos();
            cargarList();
        }else{
            JOptionPane.showMessageDialog(null, "No hay productos seleccionadoss");
        }
    }//GEN-LAST:event_crearPedidoActionPerformed

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        Producto p = Amazon.getInstance().buscarProducto(listDisponible.getSelectedValue().substring(0, listDisponible.getSelectedValue().indexOf("|")-1), almacenSeleccionar.getSelectedItem().toString());
        int cant = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad que desea enlistar", "Agregar cantidad"));
        for (int i = 0; i < cant; i++) {
            listaPedido.add(p);
            modeloSeleccionado.addElement(listDisponible.getSelectedValue());
        }
        ingresar.setEnabled(false);
    }//GEN-LAST:event_ingresarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JOptionPane.showMessageDialog(null, "Recuerdar actualizar el repositorio para que al cerrar el programa no se pierdan los datos");
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void listDisponibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDisponibleMouseClicked
        int index = -1;
        index = listDisponible.getSelectedIndex();
        if (index != -1) {
            ingresar.setEnabled(true);
        }
    }//GEN-LAST:event_listDisponibleMouseClicked

    private void almacenSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenSeleccionarActionPerformed
        cargarList();
    }//GEN-LAST:event_almacenSeleccionarActionPerformed

    private void listSeleccionadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSeleccionadoMouseClicked

    }//GEN-LAST:event_listSeleccionadoMouseClicked

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
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RealizarPedido dialog = new RealizarPedido(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> almacenSeleccionar;
    private javax.swing.JButton crearPedido;
    private javax.swing.JButton ingresar;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JLabel lblTitulo5;
    private javax.swing.JLabel lblTitulo6;
    private javax.swing.JList<String> listDisponible;
    private javax.swing.JList<String> listSeleccionado;
    private javax.swing.JScrollPane scrollPanelDFS;
    // End of variables declaration//GEN-END:variables

    private void cargarList() {
        modeloDisponible.removeAllElements();
        listDisponible.setModel(modeloDisponible);
        NodoAlmacen almacen = Amazon.getInstance().getAlmacen(almacenSeleccionar.getSelectedItem().toString());
        for (Producto producto : almacen.getListaProducto().getProductos()) {
            if (producto != null) {
                modeloDisponible.addElement(producto.getNombre() + " | Cant Disponible: " + producto.getStock());
            } 
        }
    }

    private void cargarOpcionesCbx() {
        modeloCbx.removeAllElements();
        almacenSeleccionar.setModel(modeloCbx);
        for (NodoAlmacen almacen : Amazon.getInstance().getListaMisAlmacenes().getAlmacenes()) {
            modeloCbx.addElement(almacen.getNombre());
        }
    }
}
