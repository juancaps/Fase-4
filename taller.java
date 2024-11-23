package GUI;

import controlador.conectar;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class taller extends javax.swing.JFrame {

  
    public taller() {
        initComponents();
        this.setLocationRelativeTo(null);
        //Limpiar();
        Bloqueo();

    }

    DefaultTableModel model;

    public void cargar(String valor) {

        String[] titulos = {"CEDULA", "NOMBRE", "APELLIDO", "TELEFONO", "EMAIL", "DIRECCION", "FECHA_NACIMIENTO", "COD_CLI", "PLACA"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM clientes WHERE CONCAT(nombre,' ',apellido) LIKE '%" + valor + "%'";

        model = new DefaultTableModel(null, titulos);

        conectar cc = new conectar();
        Connection cn = cc.conexion();

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                registros[0] = rs.getString("cedula");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellido");
                registros[3] = rs.getString("telefono");
                registros[4] = rs.getString("email");
                registros[5] = rs.getString("direccion");
                registros[6] = rs.getString("fecha_nacimiento");
                registros[7] = rs.getString("cod_cli");
                registros[8] = rs.getString("placa");

                model.addRow(registros);
                t_datosCli.setModel(model);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    
    void cargarProductos(String value){
        
        String[] titulos = {"COD_PRODUCTO", "DESCRIPCION", "CANTIDAD", "VALOR_COMPRA", "VALOR_VENTA", "MARCA", "PROVEEDOR", "F_INGRESO"};
        String[] registros = new String[8];
        //String sql = "SELECT * FROM productos";
        String sql = "SELECT * FROM productos WHERE CONCAT(cod_producto,' ',marca) LIKE '%" + value + "%'";// PONER EL KEYRELEASE EN EL JTEXTFIELD PARA QUE PUEDA HACER LA BUSQUEDA DINAMICA
        //String sql = "SELECT * FROM productos WHERE marca LIKE '%" + v +"%'";

        model = new DefaultTableModel(null, titulos);

        conectar cc = new conectar();
        Connection cn = cc.conexion();

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                registros[0] = rs.getString("cod_producto");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("cantidad");
                registros[3] = rs.getString("valorCompra");
                registros[4] = rs.getString("valorVenta");
                registros[5] = rs.getString("marca");
                registros[6] = rs.getString("proveedor");
                registros[7] = rs.getString("fecha_ingreso");
               

                model.addRow(registros);
                table_productos.setModel(model);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    
    
    
    
    /*
    void cargarProductos(){
        
        String[] titulos = {"COD_PRODUCTO", "DESCRIPCION", "CANTIDAD", "VALOR COMPRA", "VALOR VENTA", "MARCA", "PROVEEDOR", "F_INGRESO"};
        String[] registros = new String[8];
        String sql = "SELECT * FROM productos";
        //String sql = "SELECT * FROM clientes WHERE CONCAT(nombre,' ',apellido) LIKE '%" + valor + "%'";

        model = new DefaultTableModel(null, titulos);

        conectar cc = new conectar();
        Connection cn = cc.conexion();

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                registros[0] = rs.getString("cod_producto");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("cantidad");
                registros[3] = rs.getString("valorCompra");
                registros[4] = rs.getString("valorVenta");
                registros[5] = rs.getString("marca");
                registros[6] = rs.getString("proveedor");
                registros[7] = rs.getString("fecha_ingreso");
               

                model.addRow(registros);
                table_productos.setModel(model);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }

    
    public void cargar() {// importante

        String[] titulos = {"CEDULA","NOMBRE","APELLIDO","TELEFONO","EMAIL","DIRECCION","FECHA NACIMIENTO"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM clientes";

        model = new DefaultTableModel(null, titulos);

        conectar cc = new conectar();
        Connection cn = cc.conexion();

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                
                registros [0]=rs.getString("cedula");
                registros [1]=rs.getString("nombre");
                registros [2]=rs.getString("apellido");
                registros [3]=rs.getString("telefono");
                registros [4]=rs.getString("email");
                registros [5]=rs.getString("direccion");
                registros [6]=rs.getString("fecha_nacimiento");
                
                model.addRow(registros);
                t_datosCli.setModel(model);
                
               

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }*/ //Mediante este metodo podemos mostrar todo el contenido de una tabla
    public void Limpiar() {
        t_nom.setText("");
        t_ape.setText("");
        t_cedula.setText("");
        t_tel.setText("");
        t_direccion.setText("");
        t_correo.setText("");
        jTxt_codigoCli.setText("");
        //CFecha.setText("");
        jTxt_placa.setText("");
        jTxt_actualiza.setText("");
    }

    public void Bloqueo() {
        t_nom.setEnabled(false);
        t_ape.setEnabled(false);
        t_cedula.setEnabled(false);
        t_tel.setEnabled(false);
        t_direccion.setEnabled(false);
        t_correo.setEnabled(false);
        CFecha.setEnabled(false);
        jbtnNuevo.setEnabled(true);
        jbtnGuarda.setEnabled(false);
        jbtnCancela.setEnabled(false);
        //jTxt_actualiza.setEditable(false);
        jBtn_Eliminar.setEnabled(false);
        jBtn_actualizar.setEnabled(false);
        jTxt_codigoCli.setEnabled(false);
        jTxt_placa.setEnabled(false);

    }

    public void Desbloqueo() {
        t_nom.setEnabled(true);
        t_ape.setEnabled(true);
        t_cedula.setEnabled(true);
        t_tel.setEnabled(true);
        t_direccion.setEnabled(true);
        t_correo.setEnabled(true);
        CFecha.setEnabled(true);
        jbtnNuevo.setEnabled(false);
        jbtnGuarda.setEnabled(true);
        jbtnCancela.setEnabled(true);
        jTxt_codigoCli.setEnabled(true);
        jTxt_placa.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog_clientes = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        t_nom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        t_ape = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        t_tel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        t_cedula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        t_direccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        t_correo = new javax.swing.JTextField();
        CFecha = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jTxt_codigoCli = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTxt_placa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_datosCli = new javax.swing.JTable();
        jBtn_tabla = new javax.swing.JButton();
        jbtnGuarda = new javax.swing.JButton();
        jbtnNuevo = new javax.swing.JButton();
        jbtnCancela = new javax.swing.JButton();
        jbtnSalir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTxt_aux = new javax.swing.JTextField();
        jBtn_Eliminar = new javax.swing.JButton();
        jBtn_actualizar = new javax.swing.JButton();
        jTxt_actualiza = new javax.swing.JTextField();
        jRadiobtn_actualizar = new javax.swing.JRadioButton();
        jRadiobtn_eliminar = new javax.swing.JRadioButton();
        escoge = new javax.swing.ButtonGroup();
        jDialog_productos = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel_codProducto = new javax.swing.JLabel();
        jTxt_codProducto = new javax.swing.JTextField();
        jLabel_Descripcion = new javax.swing.JLabel();
        jTxt_descripcion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTxt_cantidad = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTxt_valorVenta = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTxt_valorCompra = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTxt_marca = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTxt_proveedor = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        FechaProducto = new com.toedter.calendar.JDateChooser();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_productos = new javax.swing.JTable();
        jTxt_busquedaProducto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jBtn_guardaProducto = new javax.swing.JButton();
        jBtn_cargarTabla = new javax.swing.JButton();
        jBtn_cancelaProducto = new javax.swing.JButton();
        jBtn_salirProducto = new javax.swing.JButton();
        jBtn_nuevoProducto = new javax.swing.JButton();
        jDialog_ventas = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        t_nom1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        t_ape1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        t_tel1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        t_cedula1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        t_direccion1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        t_correo1 = new javax.swing.JTextField();
        CFecha1 = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        jTxt_codigoCli1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTxt_placa1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        menu1 = new javax.swing.JMenu();
        jmenuClientes = new javax.swing.JMenu();
        jMenu_regClientes = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmenuProductos = new javax.swing.JMenu();
        jMenu_regProductos = new javax.swing.JMenuItem();
        jmenuFacturacion = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jmenuProveedores = new javax.swing.JMenu();
        jMenu_regProveedores = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu_listProveedores = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();
        jMenuItem11Salir = new javax.swing.JMenuItem();

        jDialog_clientes.setUndecorated(true);
        jDialog_clientes.setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setBackground(new java.awt.Color(102, 102, 255));
        jLabel3.setFont(new java.awt.Font("Felix Titling", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Registro de clientes");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setText("Nombre");

        t_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_nomActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 255, 255));
        jLabel4.setText("Apellido");

        t_ape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_apeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 255, 255));
        jLabel5.setText("Telefono");

        t_tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_telActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 255, 255));
        jLabel6.setText("Identificacion");

        t_cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cedulaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 255, 255));
        jLabel7.setText("Direccion");

        t_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_direccionActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 255, 255));
        jLabel8.setText("fecha de nacimiento");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 255));
        jLabel9.setText("Email");

        t_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_correoActionPerformed(evt);
            }
        });

        CFecha.setDateFormatString("yyyy/MM/dd");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 255, 255));
        jLabel10.setText("Codigo Cliente");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 255, 255));
        jLabel11.setText("Placa");

        jTxt_placa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxt_placaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxt_placa)
                            .addComponent(jTxt_codigoCli, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(t_nom, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(t_tel)
                            .addComponent(t_direccion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(16, 16, 16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(t_ape, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_cedula, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_correo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(t_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(t_ape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(t_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(t_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_direccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(t_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTxt_codigoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTxt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(CFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        t_datosCli.setBackground(new java.awt.Color(204, 255, 255));
        t_datosCli.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        t_datosCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(t_datosCli);

        jBtn_tabla.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBtn_tabla.setText("LIST TABLE");
        jBtn_tabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_tablaActionPerformed(evt);
            }
        });

        jbtnGuarda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnGuarda.setText("SAVE");
        jbtnGuarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardaActionPerformed(evt);
            }
        });

        jbtnNuevo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnNuevo.setForeground(new java.awt.Color(51, 51, 51));
        jbtnNuevo.setText("NEW");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });

        jbtnCancela.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnCancela.setText("CANCEL");
        jbtnCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelaActionPerformed(evt);
            }
        });

        jbtnSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnSalir.setText("EXIT");
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jbtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jbtnGuarda, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtn_tabla)
                        .addGap(148, 148, 148)
                        .addComponent(jbtnCancela)
                        .addGap(42, 42, 42)
                        .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSalir)
                    .addComponent(jbtnCancela)
                    .addComponent(jBtn_tabla)
                    .addComponent(jbtnNuevo)
                    .addComponent(jbtnGuarda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jButton1.setText("FIND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTxt_aux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxt_auxActionPerformed(evt);
            }
        });
        jTxt_aux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxt_auxKeyReleased(evt);
            }
        });

        jBtn_Eliminar.setText("REMOVE");
        jBtn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_EliminarActionPerformed(evt);
            }
        });

        jBtn_actualizar.setText("UPDATE");
        jBtn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_actualizarActionPerformed(evt);
            }
        });

        escoge.add(jRadiobtn_actualizar);
        jRadiobtn_actualizar.setText("UPDATE");
        jRadiobtn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadiobtn_actualizarActionPerformed(evt);
            }
        });

        escoge.add(jRadiobtn_eliminar);
        jRadiobtn_eliminar.setText("DELETE");
        jRadiobtn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadiobtn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog_clientesLayout = new javax.swing.GroupLayout(jDialog_clientes.getContentPane());
        jDialog_clientes.getContentPane().setLayout(jDialog_clientesLayout);
        jDialog_clientesLayout.setHorizontalGroup(
            jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDialog_clientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_clientesLayout.createSequentialGroup()
                .addGroup(jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_clientesLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_clientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTxt_aux, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)))
                .addGroup(jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadiobtn_eliminar)
                    .addComponent(jRadiobtn_actualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jDialog_clientesLayout.createSequentialGroup()
                        .addComponent(jBtn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTxt_actualiza))
                .addGap(85, 85, 85))
        );
        jDialog_clientesLayout.setVerticalGroup(
            jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_clientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialog_clientesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadiobtn_eliminar)
                        .addGap(12, 12, 12))
                    .addGroup(jDialog_clientesLayout.createSequentialGroup()
                        .addGroup(jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog_clientesLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jTxt_actualiza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_clientesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTxt_aux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadiobtn_actualizar))
                                .addGap(18, 18, 18)))
                        .addGroup(jDialog_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtn_actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDialog_productos.setUndecorated(true);
        jDialog_productos.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("REGISTRO DE PRODUCTOS");

        jLabel_codProducto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_codProducto.setForeground(new java.awt.Color(204, 255, 255));
        jLabel_codProducto.setText("COD PRODUCTO");

        jTxt_codProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxt_codProductoActionPerformed(evt);
            }
        });

        jLabel_Descripcion.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_Descripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Descripcion.setForeground(new java.awt.Color(204, 255, 255));
        jLabel_Descripcion.setText("DESCRIPCION");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 255, 255));
        jLabel13.setText("CANTIDAD");

        jTxt_cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxt_cantidadActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 255, 255));
        jLabel14.setText("VALOR VENTA");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 255, 255));
        jLabel15.setText("VALOR COMPRA");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 255, 255));
        jLabel16.setText("MARCA");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 255, 255));
        jLabel17.setText("PROVEEDOR");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 255, 255));
        jLabel18.setText("INGRESO PRO SISTEMA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_Descripcion)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxt_cantidad)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel_codProducto)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxt_descripcion)
                                    .addComponent(jTxt_codProducto))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxt_valorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxt_valorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FechaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(147, 147, 147))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_codProducto)
                        .addComponent(jTxt_valorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTxt_codProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Descripcion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxt_valorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jTxt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jTxt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jTxt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(FechaProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        table_productos.setBackground(new java.awt.Color(0, 255, 255));
        table_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table_productos);

        jTxt_busquedaProducto.setBackground(new java.awt.Color(0, 0, 0));
        jTxt_busquedaProducto.setForeground(new java.awt.Color(0, 255, 255));
        jTxt_busquedaProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxt_busquedaProductoKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 3, 14)); // NOI18N
        jLabel19.setText("BUSCAR POR CODIGO O MARCA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTxt_busquedaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTxt_busquedaProducto))
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jBtn_guardaProducto.setText("SAVE");
        jBtn_guardaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_guardaProductoActionPerformed(evt);
            }
        });

        jBtn_cargarTabla.setText("PRODUCT LIST");
        jBtn_cargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_cargarTablaActionPerformed(evt);
            }
        });

        jBtn_cancelaProducto.setText("CANCEL");

        jBtn_salirProducto.setText("EXIT");
        jBtn_salirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_salirProductoActionPerformed(evt);
            }
        });

        jBtn_nuevoProducto.setText("NEW");

        javax.swing.GroupLayout jDialog_productosLayout = new javax.swing.GroupLayout(jDialog_productos.getContentPane());
        jDialog_productos.getContentPane().setLayout(jDialog_productosLayout);
        jDialog_productosLayout.setHorizontalGroup(
            jDialog_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
            .addGroup(jDialog_productosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jBtn_nuevoProducto)
                .addGap(60, 60, 60)
                .addComponent(jBtn_guardaProducto)
                .addGap(92, 92, 92)
                .addComponent(jBtn_cargarTabla)
                .addGap(91, 91, 91)
                .addComponent(jBtn_cancelaProducto)
                .addGap(47, 47, 47)
                .addComponent(jBtn_salirProducto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDialog_productosLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog_productosLayout.setVerticalGroup(
            jDialog_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_productosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn_nuevoProducto)
                    .addComponent(jBtn_guardaProducto)
                    .addComponent(jBtn_cargarTabla)
                    .addComponent(jBtn_cancelaProducto)
                    .addComponent(jBtn_salirProducto)))
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setBackground(new java.awt.Color(102, 102, 255));
        jLabel20.setFont(new java.awt.Font("Felix Titling", 1, 48)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("MODULO DE VENTAS");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 255, 255));
        jLabel21.setText("Nombre");

        t_nom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_nom1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 255, 255));
        jLabel22.setText("Apellido");

        t_ape1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_ape1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 255, 255));
        jLabel23.setText("Telefono");

        t_tel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_tel1ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 255, 255));
        jLabel24.setText("Identificacion");

        t_cedula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cedula1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 255, 255));
        jLabel25.setText("Direccion");

        t_direccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_direccion1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 255, 255));
        jLabel26.setText("fecha de nacimiento");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 255, 255));
        jLabel27.setText("Email");

        t_correo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_correo1ActionPerformed(evt);
            }
        });

        CFecha1.setDateFormatString("yyyy/MM/dd");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 255, 255));
        jLabel28.setText("Codigo Cliente");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 255, 255));
        jLabel29.setText("Placa");

        jTxt_placa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxt_placa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxt_placa1)
                            .addComponent(jTxt_codigoCli1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(t_nom1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(t_tel1)
                            .addComponent(t_direccion1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(16, 16, 16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(18, 18, 18)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(18, 18, 18)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(t_ape1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_cedula1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t_correo1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CFecha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(112, 112, 112))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(t_nom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(t_ape1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(t_tel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)
                        .addComponent(t_cedula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_direccion1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(t_correo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jTxt_codigoCli1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jTxt_placa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(CFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog_ventasLayout = new javax.swing.GroupLayout(jDialog_ventas.getContentPane());
        jDialog_ventas.getContentPane().setLayout(jDialog_ventasLayout);
        jDialog_ventasLayout.setHorizontalGroup(
            jDialog_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog_ventasLayout.setVerticalGroup(
            jDialog_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_ventasLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 287, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Sena 2019");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(0, 0, 0));
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        setOpacity(0.8F);
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/imagen/header-5m.png"))); // NOI18N
        jLabel1.setOpaque(true);

        jMenuBar2.setBackground(new java.awt.Color(153, 153, 153));
        jMenuBar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar2.setForeground(new java.awt.Color(0, 255, 255));
        jMenuBar2.setBorderPainted(false);
        jMenuBar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        menu1.setBackground(new java.awt.Color(0, 153, 153));
        menu1.setText("                        MOTOGEST");
        menu1.setContentAreaFilled(false);
        menu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu1.setFont(new java.awt.Font("Engravers MT", 1, 36)); // NOI18N
        menu1.setMinimumSize(new java.awt.Dimension(320, 50));
        menu1.setOpaque(true);
        menu1.setPreferredSize(new java.awt.Dimension(832, 50));
        menu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu1ActionPerformed(evt);
            }
        });

        jmenuClientes.setBorder(new javax.swing.border.MatteBorder(null));
        jmenuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/imagen/clientes.png"))); // NOI18N
        jmenuClientes.setText("Clientes");
        jmenuClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmenuClientes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jmenuClientes.setPreferredSize(new java.awt.Dimension(250, 50));

        jMenu_regClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenu_regClientes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenu_regClientes.setText("Registro de Clientes");
        jMenu_regClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_regClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_regClientesActionPerformed(evt);
            }
        });
        jmenuClientes.add(jMenu_regClientes);
        jmenuClientes.add(jSeparator1);

        menu1.add(jmenuClientes);

        jmenuProductos.setBorder(new javax.swing.border.MatteBorder(null));
        jmenuProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/imagen/productos.png"))); // NOI18N
        jmenuProductos.setText("Productos");
        jmenuProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmenuProductos.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jmenuProductos.setPreferredSize(new java.awt.Dimension(150, 50));

        jMenu_regProductos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenu_regProductos.setText("Registro de Productos");
        jMenu_regProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_regProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_regProductosActionPerformed(evt);
            }
        });
        jmenuProductos.add(jMenu_regProductos);

        menu1.add(jmenuProductos);

        jmenuFacturacion.setBorder(new javax.swing.border.MatteBorder(null));
        jmenuFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/imagen/factura.png"))); // NOI18N
        jmenuFacturacion.setText("Ventas");
        jmenuFacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmenuFacturacion.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jmenuFacturacion.setPreferredSize(new java.awt.Dimension(150, 50));

        jMenuItem7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenuItem7.setText("Venta de Productos");
        jMenuItem7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmenuFacturacion.add(jMenuItem7);
        jmenuFacturacion.add(jSeparator6);

        jMenuItem10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenuItem10.setText("Facturas Clientes");
        jMenuItem10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmenuFacturacion.add(jMenuItem10);

        menu1.add(jmenuFacturacion);

        jmenuProveedores.setBorder(new javax.swing.border.MatteBorder(null));
        jmenuProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/imagen/proveedores.png"))); // NOI18N
        jmenuProveedores.setText("Proveedores");
        jmenuProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmenuProveedores.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jmenuProveedores.setPreferredSize(new java.awt.Dimension(150, 50));

        jMenu_regProveedores.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenu_regProveedores.setText("Register provider");
        jMenu_regProveedores.setActionCommand("Registrar proveedor");
        jMenu_regProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu_regProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_regProveedoresActionPerformed(evt);
            }
        });
        jmenuProveedores.add(jMenu_regProveedores);
        jmenuProveedores.add(jSeparator4);

        jMenu_listProveedores.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenu_listProveedores.setText("Listar proveedor");
        jMenu_listProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmenuProveedores.add(jMenu_listProveedores);

        menu1.add(jmenuProveedores);

        jMenuSalir.setBorder(new javax.swing.border.MatteBorder(null));
        jMenuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/imagen/sistema.png"))); // NOI18N
        jMenuSalir.setText("Sistema");
        jMenuSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuSalir.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        jMenuItem11Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem11Salir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenuItem11Salir.setText("Exit");
        jMenuItem11Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jMenuItem11Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11SalirActionPerformed(evt);
            }
        });
        jMenuSalir.add(jMenuItem11Salir);

        menu1.add(jMenuSalir);

        jMenuBar2.add(menu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu_regProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_regProductosActionPerformed
        if (jDialog_clientes.isVisible() == true) {
            jDialog_clientes.dispose();
            jDialog_productos.setVisible(true);
            jDialog_productos.setSize(800, 600);
            jDialog_productos.setLocationRelativeTo(null);
        } else {
            jDialog_productos.setVisible(true);
            jDialog_productos.setSize(800, 600);
            jDialog_productos.setLocationRelativeTo(null);
        }


    }//GEN-LAST:event_jMenu_regProductosActionPerformed

    private void menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu1ActionPerformed

    }//GEN-LAST:event_menu1ActionPerformed

    private void jMenuItem11SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem11SalirActionPerformed

    private void jMenu_regProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_regProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu_regProveedoresActionPerformed

    private void jMenu_regClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_regClientesActionPerformed

        if (jDialog_productos.isVisible()) {
            jDialog_productos.dispose();
            jDialog_clientes.setVisible(true);
            jDialog_clientes.setSize(800, 600);
            jDialog_clientes.setLocationRelativeTo(null);

        } else {
            jDialog_clientes.setVisible(true);
            jDialog_clientes.setSize(800, 600);
            jDialog_clientes.setLocationRelativeTo(null);
        }


    }//GEN-LAST:event_jMenu_regClientesActionPerformed

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        Desbloqueo();
        t_nom.requestFocus();
        Limpiar();

    }//GEN-LAST:event_jbtnNuevoActionPerformed

    private void t_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_nomActionPerformed
        t_nom.transferFocus();
    }//GEN-LAST:event_t_nomActionPerformed

    private void t_apeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_apeActionPerformed
        t_ape.transferFocus();
    }//GEN-LAST:event_t_apeActionPerformed

    private void t_telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_telActionPerformed
        t_tel.transferFocus();
    }//GEN-LAST:event_t_telActionPerformed

    private void jbtnGuardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardaActionPerformed
        conectar cc = new conectar();
        java.sql.Connection cn = cc.conexion();
        String nom, ape, ced, tel, correo, direccion, fecha, placa;
        int dia, mes, año, cod_cli;
        dia = CFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = CFecha.getCalendar().get(Calendar.MONTH);
        año = CFecha.getCalendar().get(Calendar.YEAR);

        fecha = año + "/" + (mes + 1) + "/" + dia;

        String sql = "";
        nom = t_nom.getText();
        ape = t_ape.getText();
        ced = t_cedula.getText();
        tel = t_tel.getText();
        correo = t_correo.getText();
        direccion = t_direccion.getText();
        cod_cli = parseInt(jTxt_codigoCli.getText());
        placa = jTxt_placa.getText();

        sql = "INSERT INTO clientes(nombre,apellido,cedula,telefono,email,direccion,fecha_nacimiento,cod_cli,placa)VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nom);
            pst.setString(2, ape);
            pst.setString(3, ced);
            pst.setString(4, tel);
            pst.setString(5, correo);
            pst.setString(6, direccion);
            pst.setString(7, fecha);
            pst.setInt(8, cod_cli);
            pst.setString(9, placa);

            int n = pst.executeUpdate();

            if (n > 0) {

                JOptionPane.showMessageDialog(null, "Registro Guardado con exito");
                //Bloqueo();
                Limpiar();
            }

        } catch (SQLException ex) {
            Logger.getLogger(taller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbtnGuardaActionPerformed

    private void jbtnCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelaActionPerformed
        Limpiar();
        Bloqueo();
    }//GEN-LAST:event_jbtnCancelaActionPerformed

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        jDialog_clientes.setVisible(false);
    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void t_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_correoActionPerformed
        t_correo.transferFocus();
    }//GEN-LAST:event_t_correoActionPerformed

    private void jBtn_tablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_tablaActionPerformed
        cargar("");
    }//GEN-LAST:event_jBtn_tablaActionPerformed

    private void jTxt_auxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxt_auxKeyReleased
        cargar(jTxt_aux.getText());
    }//GEN-LAST:event_jTxt_auxKeyReleased

    private void t_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_direccionActionPerformed
        t_direccion.transferFocus();
    }//GEN-LAST:event_t_direccionActionPerformed

    private void t_cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cedulaActionPerformed
        t_cedula.transferFocus();
    }//GEN-LAST:event_t_cedulaActionPerformed

    private void jTxt_auxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxt_auxActionPerformed
        jTxt_aux.transferFocus();
    }//GEN-LAST:event_jTxt_auxActionPerformed

    private void jBtn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_EliminarActionPerformed
        // eliminando Registro 
        conectar cc = new conectar();
        java.sql.Connection cn = cc.conexion();
        /*int cedula;
        String sql = "";
        cedula = parseInt(jTxt_aux.getText());
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente deseas eliminar a: "+cedula);
        if(respuesta==JOptionPane.YES_OPTION){
            sql = "DELETE FROM clientes WHERE cedula ='"+cedula+"'";
           try {
            java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            //pst.setString(1, nom);
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado con exito");
                jTxt_aux.setText("");
                Limpiar();
                //Bloqueo();
            }

        } catch (SQLException ex) {
            Logger.getLogger(taller.class.getName()).log(Level.SEVERE, null, ex);
        }  
        }else{
            JOptionPane.showMessageDialog(null,"Operacion Cancelada");
        }*/

        int codigo;
        String sql = "";
        codigo = parseInt(jTxt_actualiza.getText());
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente deseas eliminar a: " + codigo);
        if (respuesta == JOptionPane.YES_OPTION) {
            sql = "DELETE FROM clientes WHERE cod_cli ='" + codigo + "'";
            try {
                java.sql.PreparedStatement pst = cn.prepareStatement(sql);
                //pst.setString(1, nom);
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado con exito");
                    jTxt_aux.setText("");
                    Limpiar();
                    //Bloqueo();
                }

            } catch (SQLException ex) {
                Logger.getLogger(taller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operacion Cancelada");
        }


    }//GEN-LAST:event_jBtn_EliminarActionPerformed


    private void jBtn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_actualizarActionPerformed
        // Update registros

        conectar cc = new conectar();
        java.sql.Connection cn = cc.conexion();
        String nom, ape, ced, tel, correo, direccion, fecha, placa;
        int dia, mes, año, cod_cli;
        dia = CFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = CFecha.getCalendar().get(Calendar.MONTH);
        año = CFecha.getCalendar().get(Calendar.YEAR);

        fecha = año + "/" + (mes + 1) + "/" + dia;

        String sql = "";
        nom = t_nom.getText();
        ape = t_ape.getText();
        ced = t_cedula.getText();
        tel = t_tel.getText();
        correo = t_correo.getText();
        direccion = t_direccion.getText();
        placa = jTxt_placa.getText();
        cod_cli = parseInt(jTxt_actualiza.getText());

        sql = "UPDATE clientes set nombre=?,apellido=?,cedula=?,telefono=?,email=?,direccion=?,fecha_nacimiento=?,placa=? WHERE cod_cli=?";
        try {
            java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nom);
            pst.setString(2, ape);
            pst.setString(3, ced);
            pst.setString(4, tel);
            pst.setString(5, correo);
            pst.setString(6, direccion);
            pst.setString(7, fecha);
            pst.setString(8, placa);
            pst.setInt(9, cod_cli);

            int n = pst.executeUpdate();

            if (n > 0) {

                JOptionPane.showMessageDialog(null, "Registro Actualizado con exito");
                Limpiar();
                //Bloqueo();
            }

        } catch (SQLException ex) {
            Logger.getLogger(taller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jBtn_actualizarActionPerformed

    private void jTxt_placaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxt_placaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxt_placaActionPerformed

    public void micajaBloqueo() {

        jBtn_Eliminar.setEnabled(false);
        jBtn_actualizar.setEnabled(true);

    }

    private void jRadiobtn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadiobtn_actualizarActionPerformed
        if (jBtn_actualizar.isEnabled() == true) {
            jBtn_actualizar.setEnabled(true);

        }

        if (jBtn_Eliminar.isEnabled() == true) {
            jBtn_Eliminar.setEnabled(false);
            jBtn_actualizar.setEnabled(true);

        }


    }//GEN-LAST:event_jRadiobtn_actualizarActionPerformed

    private void jRadiobtn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadiobtn_eliminarActionPerformed
        if (jRadiobtn_eliminar.isEnabled()) {
            jBtn_Eliminar.setEnabled(true);
            //jBtn_actualizar.setEnabled(false);

        }

        if (jBtn_actualizar.isEnabled() == true) {
            jBtn_actualizar.setEnabled(false);

        }
    }//GEN-LAST:event_jRadiobtn_eliminarActionPerformed

    private void jTxt_cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxt_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxt_cantidadActionPerformed
// CONFIGURACION BOTON GUARDAR PRODUCTOS
    
    private void jBtn_guardaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_guardaProductoActionPerformed
        conectar cc = new conectar();
        java.sql.Connection cn = cc.conexion();
        String descripcion, marca, proveedor;
        int dia, mes, año, cod_producto, cantidad;
        double valorCompra, valorVenta;
        dia = FechaProducto.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = FechaProducto.getCalendar().get(Calendar.MONTH);
        año = FechaProducto.getCalendar().get(Calendar.YEAR);

        String fecha = año + "/" + (mes + 1) + "/" + dia;

        String sql = "";
        cod_producto = parseInt(jTxt_codProducto.getText());
        cantidad = parseInt(jTxt_cantidad.getText());
        descripcion = jTxt_descripcion.getText();
        marca = jTxt_marca.getText();
        proveedor = jTxt_proveedor.getText();
        valorCompra = parseDouble(jTxt_valorCompra.getText());
        valorVenta = parseDouble(jTxt_valorVenta.getText());
        /*MARIA DEL PILAR MARTINEZ ------ INGRID JOHANA ANGULO ----- JUAN CARLOS PULIDO SIERRA*/
        
        
        

        sql = "INSERT INTO productos(cod_producto,descripcion,cantidad,valorCompra,valorVenta,marca,proveedor,fecha_ingreso)VALUES(?,?,?,?,?,?,?,?)";

        try {
            java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, cod_producto);
            pst.setString(2, descripcion);
            pst.setInt(3, cantidad);
            pst.setDouble(4, valorCompra);
            pst.setDouble(5, valorVenta);
            pst.setString(6, marca);
            pst.setString(7, proveedor);
            pst.setString(8, fecha);
          
            int n = pst.executeUpdate();

            if (n > 0) {

                JOptionPane.showMessageDialog(null, "Registro Guardado con exito");
                //Bloqueo();
                //Limpiar();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE GUARDO TU REGISTRO");
        }
    }//GEN-LAST:event_jBtn_guardaProductoActionPerformed

    private void jBtn_cargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_cargarTablaActionPerformed
        
        cargarProductos("");// PILAS PONER EL KEYRELEASE EN EL EVENTO PARA REALIZAR LA BUSQUEDA DINAMICA
    }//GEN-LAST:event_jBtn_cargarTablaActionPerformed

    private void jBtn_salirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_salirProductoActionPerformed
       jDialog_productos.setVisible(false);
    }//GEN-LAST:event_jBtn_salirProductoActionPerformed

    private void jTxt_codProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxt_codProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxt_codProductoActionPerformed

    private void jTxt_busquedaProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxt_busquedaProductoKeyReleased
        cargarProductos(jTxt_busquedaProducto.getText());// PILAS SE DEBE PONER ESTO PARA PODER REALIZAR LA BUSQUEDA DINAMICA 
    }//GEN-LAST:event_jTxt_busquedaProductoKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t_nom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_nom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_nom1ActionPerformed

    private void t_ape1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_ape1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_ape1ActionPerformed

    private void t_tel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_tel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_tel1ActionPerformed

    private void t_cedula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cedula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cedula1ActionPerformed

    private void t_direccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_direccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_direccion1ActionPerformed

    private void t_correo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_correo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_correo1ActionPerformed

    private void jTxt_placa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxt_placa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxt_placa1ActionPerformed

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
            java.util.logging.Logger.getLogger(taller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(taller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(taller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(taller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new taller().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser CFecha;
    private com.toedter.calendar.JDateChooser CFecha1;
    private com.toedter.calendar.JDateChooser FechaProducto;
    public static javax.swing.ButtonGroup escoge;
    private javax.swing.JButton jBtn_Eliminar;
    private javax.swing.JButton jBtn_actualizar;
    private javax.swing.JButton jBtn_cancelaProducto;
    private javax.swing.JButton jBtn_cargarTabla;
    private javax.swing.JButton jBtn_guardaProducto;
    private javax.swing.JButton jBtn_nuevoProducto;
    private javax.swing.JButton jBtn_salirProducto;
    private javax.swing.JButton jBtn_tabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog_clientes;
    private javax.swing.JDialog jDialog_productos;
    private javax.swing.JDialog jDialog_ventas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Descripcion;
    private javax.swing.JLabel jLabel_codProducto;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11Salir;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JMenuItem jMenu_listProveedores;
    private javax.swing.JMenuItem jMenu_regClientes;
    private javax.swing.JMenuItem jMenu_regProductos;
    private javax.swing.JMenuItem jMenu_regProveedores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadiobtn_actualizar;
    private javax.swing.JRadioButton jRadiobtn_eliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTxt_actualiza;
    private javax.swing.JTextField jTxt_aux;
    private javax.swing.JTextField jTxt_busquedaProducto;
    private javax.swing.JTextField jTxt_cantidad;
    private javax.swing.JTextField jTxt_codProducto;
    private javax.swing.JTextField jTxt_codigoCli;
    private javax.swing.JTextField jTxt_codigoCli1;
    private javax.swing.JTextField jTxt_descripcion;
    private javax.swing.JTextField jTxt_marca;
    private javax.swing.JTextField jTxt_placa;
    private javax.swing.JTextField jTxt_placa1;
    private javax.swing.JTextField jTxt_proveedor;
    private javax.swing.JTextField jTxt_valorCompra;
    private javax.swing.JTextField jTxt_valorVenta;
    private javax.swing.JButton jbtnCancela;
    private javax.swing.JButton jbtnGuarda;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JMenu jmenuClientes;
    private javax.swing.JMenu jmenuFacturacion;
    private javax.swing.JMenu jmenuProductos;
    private javax.swing.JMenu jmenuProveedores;
    private javax.swing.JMenu menu1;
    private javax.swing.JTextField t_ape;
    private javax.swing.JTextField t_ape1;
    private javax.swing.JTextField t_cedula;
    private javax.swing.JTextField t_cedula1;
    private javax.swing.JTextField t_correo;
    private javax.swing.JTextField t_correo1;
    private javax.swing.JTable t_datosCli;
    public javax.swing.JTextField t_direccion;
    public javax.swing.JTextField t_direccion1;
    private javax.swing.JTextField t_nom;
    private javax.swing.JTextField t_nom1;
    private javax.swing.JTextField t_tel;
    private javax.swing.JTextField t_tel1;
    private javax.swing.JTable table_productos;
    // End of variables declaration//GEN-END:variables

}
