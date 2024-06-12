/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.PeliculaTablaDTO;
import entidad.ClienteEntidad;
import entidad.FuncionEntidad;
import entidad.PeliculaEntidad;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.IClienteNegocio;
import negocio.IPeliculaNegocio;
import negocio.NegocioException;
import negocio.PeliculaNegocio;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.FuncionDAO;
import persistencia.GeneroDAO;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.PeliculaDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294. René Ezequiel Figueroa
 * López - 228691. Sergio Arturo García Ramírez - 233316.
 */
public class FrmCartelera extends javax.swing.JFrame {
    int idCliente;
    private ClienteEntidad cliente = new ClienteEntidad();
    private IClienteNegocio clienteNegocio;
    private IConexionBD ConexionBD = new ConexionBD();
    private IClienteDAO ClienteDato = new ClienteDAO(ConexionBD);

    private String rutaCinepolisLogo = "src/main/java/utilerias/Imagenes/CinepolisLogo.png";
    private String rutaImagen;
    GregorianCalendar date = new GregorianCalendar();

        
    /**
     * Creates new form FrmCartelera
     */
    public FrmCartelera(int idCliente) {
        initComponents();
        cargarUbicaciones();
        cargarPeliculas();
        this.idCliente = idCliente;
        
        
        setImagenLabel(jblCinepolisLogo, rutaCinepolisLogo);

    }

    private void setImagenLabel(JLabel nombreJlb, String ruta) {

        ImageIcon image = new ImageIcon(ruta);

        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(nombreJlb.getWidth(), nombreJlb.getHeight(), Image.SCALE_DEFAULT));

        nombreJlb.setIcon(icon);

        this.repaint();

        jlbFecha.setText("Funciones de hoy: " + date.get(Calendar.DAY_OF_MONTH) + " - " + (date.get(Calendar.MONTH) + 1) + " - " + date.get(Calendar.YEAR));
    }
    
    
    public void setImagenPeli(JLabel nombreJlb, String ruta) throws IOException{
        // Atributos para colocar imagenes a la pelicula
        Image image = null;
        URL url = new URL(ruta);
        image = ImageIO.read(url);
        
                Icon icon = new ImageIcon(
                image.getScaledInstance(nombreJlb.getWidth(), nombreJlb.getHeight(), Image.SCALE_DEFAULT));

        nombreJlb.setIcon(icon);
    
        
        this.repaint();
        
    }
    
    

    private void cargarUbicaciones() {
        ClienteDAO clienteDAO = new ClienteDAO(new ConexionBD());
        try {
            List<String> ciudades = clienteDAO.obtenerSucursales((float) -109.934337, (float) 27.480956);
//            List<String> ciudades = clienteDAO.obtenerSucursales(cliente.getLongitud(),cliente.getLatitud());
            for (String ciudad : ciudades) {
                cbUbicacion.addItem(ciudad);
            }
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las ubicaciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarPeliculas() {
        PeliculaDAO peliDAO = new PeliculaDAO(new ConexionBD());
        try {
            List<PeliculaEntidad> pelis = peliDAO.buscarPeliculasTabla();
            for (PeliculaEntidad pelicula : pelis) {
                cbPeliculas.addItem(pelicula.getTituloPelicula());
            }
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las ubicaciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getHoraSeleccionada() {
        int indiceFilaSeleccionada = this.tblHoras.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblHoras.getModel();
            int indiceColumnaId = 0;
            String horaFuncion = modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId).toString();
            return horaFuncion;
        } else {
            return null;
        }
    }
    
    private void llenarTablaFunciones(List<FuncionEntidad> funcion) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblHoras.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (funcion != null) {
            funcion.forEach(row -> {
                Object[] fila = new Object[1];
                fila[0] = row.getHoraInicio();

                modeloTabla.addRow(fila);
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jblCinepolisLogo = new javax.swing.JLabel();
        cbUbicacion = new javax.swing.JComboBox<>();
        jlbFecha = new javax.swing.JTextField();
        cbPeliculas = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblSinopsis = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoras = new javax.swing.JTable();
        lblImagenPeli = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cartelera");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 127));

        jblCinepolisLogo.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        cbUbicacion.setBackground(new java.awt.Color(8, 148, 249));
        cbUbicacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUbicacionActionPerformed(evt);
            }
        });

        jlbFecha.setEditable(false);
        jlbFecha.setBackground(new java.awt.Color(8, 148, 249));
        jlbFecha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlbFechaActionPerformed(evt);
            }
        });

        cbPeliculas.setBackground(new java.awt.Color(8, 148, 249));
        cbPeliculas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbPeliculas.setMaximumRowCount(10);
        cbPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPeliculasActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Titulo");

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDuracion.setText("Duracion");

        lblGenero.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblGenero.setText("Genero");

        lblSinopsis.setText("Sinopsis");

        tblHoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Horas"
            }
        ));
        jScrollPane1.setViewportView(tblHoras);

        lblImagenPeli.setText("jLabel1");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblImagenPeli, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblSinopsis)
                                .addContainerGap(366, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDuracion)
                                    .addComponent(lblGenero))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAceptar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(29, 29, 29)
                        .addComponent(lblDuracion)
                        .addGap(27, 27, 27)
                        .addComponent(lblGenero)
                        .addGap(41, 41, 41)
                        .addComponent(lblSinopsis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addComponent(lblImagenPeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addGap(152, 152, 152)))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbUbicacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbFecha)
                    .addComponent(cbPeliculas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbUbicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jlbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlbFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbFechaActionPerformed

    private void cbUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUbicacionActionPerformed

    private void cbPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPeliculasActionPerformed
        // TODO add your handling code here:
        PeliculaDAO peliDAO = new PeliculaDAO(new ConexionBD());
        FuncionDAO funcionDAO = new FuncionDAO(new ConexionBD());
        GeneroDAO generoDAO = new GeneroDAO(new ConexionBD());
        try {
            lblTitulo.setText(peliDAO.buscarPeliculaTitulo(cbPeliculas.getItemAt(cbPeliculas.getSelectedIndex())).getTituloPelicula());
            lblSinopsis.setText(peliDAO.buscarPeliculaTitulo(cbPeliculas.getItemAt(cbPeliculas.getSelectedIndex())).getSinopsis());
            lblDuracion.setText(String.valueOf(peliDAO.buscarPeliculaTitulo(cbPeliculas.getItemAt(cbPeliculas.getSelectedIndex())).getDuracion()));
            lblGenero.setText(generoDAO.obtenerGeneroDePelicula(peliDAO.buscarPeliculaTitulo(cbPeliculas.getItemAt(cbPeliculas.getSelectedIndex())).getGeneroPelicula()));
            
            String direccion = peliDAO.buscarPeliculaTituloString(lblTitulo.getText());
            try {
                setImagenPeli(lblImagenPeli,direccion);
            } catch (IOException ex) {
                Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            llenarTablaFunciones(funcionDAO.buscarFuncionesPorPelicula(peliDAO.buscarPeliculaTitulo(cbPeliculas.getItemAt(cbPeliculas.getSelectedIndex())).getIdPelicula()));
        } catch (PersistenciaException | SQLException ex) {
            Logger.getLogger(FrmCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbPeliculasActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        FrmCompraPelicula frmCompra = new FrmCompraPelicula(lblTitulo.getText(),lblDuracion.getText(),lblGenero.getText(),getHoraSeleccionada(), this.idCliente);
        frmCompra.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JComboBox<String> cbPeliculas;
    private javax.swing.JComboBox<String> cbUbicacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jblCinepolisLogo;
    private javax.swing.JTextField jlbFecha;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblImagenPeli;
    private javax.swing.JLabel lblSinopsis;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblHoras;
    // End of variables declaration//GEN-END:variables
}
