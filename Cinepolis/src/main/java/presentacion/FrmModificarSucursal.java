/**
 * 
 */
package presentacion;

import dtos.SucursalTablaDTO;
import entidad.PeliculaEntidad;
import entidad.SucursalEntidad;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.IPeliculaNegocio;
import negocio.ISucursalNegocio;
import negocio.NegocioException;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.SucursalDAO;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class FrmModificarSucursal extends javax.swing.JFrame {

    private IPeliculaNegocio peliculaNegocio;
    private SucursalEntidad sucursal = new SucursalEntidad();
    private ISucursalNegocio sucursalNegocio;
    private IConexionBD ConexionBD = new ConexionBD();
    private ISucursalDAO sucursalDAO = new SucursalDAO(ConexionBD);
    
    private String rutaCinepolisLogo = "src/main/java/utilerias/Imagenes/CinepolisLogo.png";
    static int id = 0;
    
    /**
     * Creates new form FrmModificarSucursal
     * @param sucursalNegocio
     */
    public FrmModificarSucursal(ISucursalNegocio sucursalNegocio) {
        initComponents();
        
        this.sucursalNegocio = sucursalNegocio;
        
        btnGuardar.setVisible(false);
        btnEliminar.setVisible(false);        
        setImagenLabel(jblCinepolisLogo, rutaCinepolisLogo);
        
        cargarMetodosIniciales();
        

    }
    
    
    
   private void cargarMetodosIniciales(){
        this.cargarConfiguracionInicialTablaSucursales();
        this.cargarSucursalesEnTabla();
        
        
        campoTextoCiudad.setEditable(false);
        campoTextoLatitud.setEditable(false);
        campoTextoLongitud.setEditable(false);
        campoTextoNombre.setEditable(false);
        campoTextoSalas.setEditable(false);
        
        btnHecho.setVisible(false);
        btnCancelar.setVisible(false);
        btnEditar.setVisible(true);
        
    }
    
      
    
    
    private void cargarConfiguracionInicialTablaSucursales() {
            final int columnaId = 0;


    }
    
    private int getIdSeleccionadoTablaSucursales() {
        int indiceFilaSeleccionada = this.tblSucursales.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblSucursales.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private void editar() {
        //Metodo para regresar el alumno seleccionado
        int id = this.getIdSeleccionadoTablaSucursales();


            
        
    }

    private void eliminar() {
        //Metodo para regresar el alumno seleccionado
        int id = this.getIdSeleccionadoTablaSucursales();
    }
    
    private void llenarTablaSucursales(List<SucursalTablaDTO> sucursalesLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblSucursales.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (sucursalesLista != null) {
            sucursalesLista.forEach(row -> {
                Object[] fila = new Object[6];
                fila[0] = row.getIdSucursal();
                fila[1] = row.getNombre();
                fila[2] = row.getCiudad();
                fila[3] = row.getSalas();
                fila[4] = row.getLatitud();
                fila[5] = row.getLongitud();

                modeloTabla.addRow(fila);
            });
        }
    }
     
    private void cargarSucursalesEnTabla() {
        try {
            List<SucursalTablaDTO> sucursales = this.sucursalNegocio.buscarSucursalesTabla();
            this.llenarTablaSucursales(sucursales);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
            System.out.println("aqui");
        }
    }    
    
    
    
    
 
    private void setImagenLabel(JLabel nombreJlb, String ruta){
    
        ImageIcon image = new ImageIcon(ruta);
        
        Icon icon = new ImageIcon(
       image.getImage().getScaledInstance(nombreJlb.getWidth(), nombreJlb.getHeight(), Image.SCALE_DEFAULT));
        
        nombreJlb.setIcon(icon);
        
        this.repaint();
   
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
        jblCinepolisLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSucursales = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        lblModificarSucursal = new javax.swing.JLabel();
        campoTextoNombre = new javax.swing.JTextField();
        campoTextoCiudad = new javax.swing.JTextField();
        campoTextoSalas = new javax.swing.JTextField();
        campoTextoLatitud = new javax.swing.JTextField();
        campoTextoLongitud = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblSalas = new javax.swing.JLabel();
        lblLatitud = new javax.swing.JLabel();
        lblLongitud = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevoRegistro = new javax.swing.JButton();
        btnHecho = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar Sucursal");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jblCinepolisLogo.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblSucursales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Ciudad", "Salas", "Latitud", "Longitud"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSucursales);

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblModificarSucursal.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblModificarSucursal.setForeground(new java.awt.Color(0, 0, 0));
        lblModificarSucursal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModificarSucursal.setText("Modificar Sucursal");

        campoTextoNombre.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoNombreActionPerformed(evt);
            }
        });

        campoTextoCiudad.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoCiudadActionPerformed(evt);
            }
        });

        campoTextoSalas.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoSalasActionPerformed(evt);
            }
        });

        campoTextoLatitud.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoLatitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoLatitudActionPerformed(evt);
            }
        });

        campoTextoLongitud.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoLongitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoLongitudActionPerformed(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Nombre");

        lblCiudad.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblCiudad.setForeground(new java.awt.Color(0, 0, 0));
        lblCiudad.setText("Ciudad");

        lblSalas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblSalas.setForeground(new java.awt.Color(0, 0, 0));
        lblSalas.setText("Salas");

        lblLatitud.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblLatitud.setForeground(new java.awt.Color(0, 0, 0));
        lblLatitud.setText("Latitud");

        lblLongitud.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblLongitud.setForeground(new java.awt.Color(0, 0, 0));
        lblLongitud.setText("Longitud");

        btnRegresar.setBackground(new java.awt.Color(8, 148, 249));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(8, 148, 249));
        btnEditar.setForeground(new java.awt.Color(0, 0, 0));
        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(8, 148, 249));
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevoRegistro.setBackground(new java.awt.Color(8, 148, 249));
        btnNuevoRegistro.setText("Nuevo");
        btnNuevoRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRegistroActionPerformed(evt);
            }
        });

        btnHecho.setBackground(new java.awt.Color(8, 148, 249));
        btnHecho.setText("Hecho");
        btnHecho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHechoActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(8, 148, 249));
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblModificarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLatitud)
                                .addComponent(campoTextoLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(campoTextoCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(61, 61, 61))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblLongitud)
                                        .addComponent(campoTextoLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoTextoSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSalas)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnNuevoRegistro)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnHecho))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addComponent(btnCancelar)))
                            .addGap(6, 6, 6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnEditar)
                                .addComponent(btnGuardar)
                                .addComponent(btnEliminar)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModificarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSalas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLatitud)
                    .addComponent(lblLongitud)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevoRegistro)
                    .addComponent(btnHecho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoTextoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoNombreActionPerformed

    private void campoTextoCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoCiudadActionPerformed

    private void campoTextoSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoSalasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoSalasActionPerformed

    private void campoTextoLatitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoLatitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoLatitudActionPerformed

    private void campoTextoLongitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoLongitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoLongitudActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        FrmPantallaAdmin admin = new FrmPantallaAdmin(peliculaNegocio, sucursalNegocio);

        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        
       String texto = JOptionPane.showInputDialog("Ingrese el id de la sucursal");

        id = Integer.parseInt(texto);
        
        
        sucursal.setIdSucursal(id);
        
        
        campoTextoCiudad.setEditable(true);
        campoTextoLatitud.setEditable(true);
        campoTextoLongitud.setEditable(true);
        campoTextoNombre.setEditable(true);
        campoTextoSalas.setEditable(true);
        
        btnHecho.setVisible(true);
        btnEliminar.setVisible(true);
        
        try {
            sucursal = sucursalNegocio.buscarSucursal(sucursal);

            campoTextoCiudad.setText(Integer.toString(sucursal.getCiudad()));
            campoTextoLatitud.setText(Float.toString(sucursal.getLatitud()));
            campoTextoLongitud.setText(Float.toString(sucursal.getLongitud()));
            campoTextoNombre.setText(sucursal.getNombre());
            campoTextoSalas.setText(String.valueOf(sucursal.getSalas()));
            
            JOptionPane.showMessageDialog(this, "Sucursal Actualizada");
 
        } catch (NegocioException ex) {
            Logger.getLogger(FrmModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        
        String nombre = campoTextoNombre.getText();
        int ciudad = Integer.parseInt(campoTextoCiudad.getText());
        int sala = Integer.parseInt(campoTextoSalas.getText());
        float latitud = Float.parseFloat(campoTextoLatitud.getText());
        float longitud = Float.parseFloat(campoTextoLongitud.getText());

        sucursal.setCiudad(ciudad);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setNombre(nombre);
        sucursal.setSalas(sala);

        try {
            sucursalNegocio.registrarSucursal(sucursal);
            JOptionPane.showMessageDialog(this, "Sucursal agregada");
            cargarMetodosIniciales();
            
        } catch (NegocioException ex) {
            Logger.getLogger(FrmModificarSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
                
        try {            
            sucursalNegocio.eliminarSucursal(id);
            
            JOptionPane.showMessageDialog(this, "Sucursal Eliminada");
            btnEliminar.setVisible(false);
            this.cargarSucursalesEnTabla();

        } catch (NegocioException ex) {
            Logger.getLogger(FrmModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRegistroActionPerformed
        // TODO add your handling code here:
        campoTextoCiudad.setEditable(true);
        campoTextoLatitud.setEditable(true);
        campoTextoLongitud.setEditable(true);
        campoTextoNombre.setEditable(true);
        campoTextoSalas.setEditable(true);

        campoTextoCiudad.setText("");
        campoTextoLatitud.setText("");
        campoTextoLongitud.setText("");
        campoTextoNombre.setText("");
        campoTextoSalas.setText("");

        btnGuardar.setVisible(true);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        
        btnHecho.setVisible(false);
    }//GEN-LAST:event_btnNuevoRegistroActionPerformed

    private void btnHechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHechoActionPerformed
        // TODO add your handling code here:

        String nombre = campoTextoNombre.getText();
        int ciudad = Integer.parseInt(campoTextoCiudad.getText());
        int sala = Integer.parseInt(campoTextoSalas.getText());
        float latitud = Float.parseFloat(campoTextoLatitud.getText());
        float longitud = Float.parseFloat(campoTextoLongitud.getText());

        sucursal.setCiudad(ciudad);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setNombre(nombre);
        sucursal.setSalas(sala);

//        try {
//            peliculaNegocio.editarPelicula(pelicula);
//            JOptionPane.showMessageDialog(this, "Pelicula actualizada");
//            this.cargarPeliculasEnTabla();
//
//        } catch (NegocioException ex) {
//            Logger.getLogger(FrmModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("ok");
//        }

    }//GEN-LAST:event_btnHechoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        
        btnHecho.setVisible(false);
        btnEditar.setVisible(true);
        
        
        btnCancelar.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHecho;
    private javax.swing.JButton btnNuevoRegistro;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JTextField campoTextoCiudad;
    private javax.swing.JTextField campoTextoLatitud;
    private javax.swing.JTextField campoTextoLongitud;
    private javax.swing.JTextField campoTextoNombre;
    private javax.swing.JTextField campoTextoSalas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jblCinepolisLogo;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblLatitud;
    private javax.swing.JLabel lblLongitud;
    private javax.swing.JLabel lblModificarSucursal;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSalas;
    private javax.swing.JTable tblSucursales;
    // End of variables declaration//GEN-END:variables
}
