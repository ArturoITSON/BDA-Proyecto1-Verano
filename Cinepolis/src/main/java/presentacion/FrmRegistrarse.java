/**
 *
 */
package presentacion;

import entidad.ClienteEntidad;
import java.awt.Image;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import negocio.ClienteNegocio;
import negocio.IClienteNegocio;
import negocio.NegocioException;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.PersistenciaException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294. René Ezequiel Figueroa
 * López - 228691. Sergio Arturo García Ramírez - 233316.
 */
public class FrmRegistrarse extends javax.swing.JFrame {

    /**
     * Creates new form FrmRegistrarse
     */
    private ClienteEntidad cliente = new ClienteEntidad();
    private IClienteNegocio clienteNegocio;
    private IConexionBD ConexionBD = new ConexionBD();
    private IClienteDAO ClienteDato = new ClienteDAO(ConexionBD);

    private FrmInicio inicio = new FrmInicio();

    private String rutaCinepolisLogo = "src/main/java/utilerias/Imagenes/CinepolisLogo.png";

    public FrmRegistrarse(IClienteNegocio clienteNegocio) {
        initComponents();
        cargarCiudades();
        this.clienteNegocio = clienteNegocio;

        setImagenLabel(jblCinepolisLogo, rutaCinepolisLogo);

    }

    private void setImagenLabel(JLabel nombreJlb, String ruta) {

        ImageIcon image = new ImageIcon(ruta);

        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(nombreJlb.getWidth(), nombreJlb.getHeight(), Image.SCALE_DEFAULT));

        nombreJlb.setIcon(icon);

        this.repaint();

    }
    
    private void cargarCiudades() {
        ClienteDAO clienteDAO = new ClienteDAO(new ConexionBD()); // Suponiendo que tienes una implementación de IConexionBD
        try {
            List<String> ciudades = clienteDAO.obtenerCiudades();
            for (String ciudad : ciudades) {
                cbCiudades.addItem(ciudad);
            }
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las ciudades: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        campoTextoApellidoMaterno = new javax.swing.JTextField();
        campoTextoFecha = new javax.swing.JTextField();
        campoTextoNombre2 = new javax.swing.JTextField();
        campoTextoApellidoPaterno = new javax.swing.JTextField();
        campoTextoCorreo = new javax.swing.JTextField();
        campoTextoContraseña = new javax.swing.JTextField();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblNacimiento = new javax.swing.JLabel();
        lblApellidoPaterno2 = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        btnRegistrarse = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbCiudades = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrarse");

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
                .addGap(157, 157, 157))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        campoTextoApellidoMaterno.setBackground(new java.awt.Color(136, 201, 239));

        campoTextoFecha.setBackground(new java.awt.Color(136, 201, 239));

        campoTextoNombre2.setBackground(new java.awt.Color(136, 201, 239));

        campoTextoApellidoPaterno.setBackground(new java.awt.Color(136, 201, 239));

        campoTextoCorreo.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoCorreoActionPerformed(evt);
            }
        });

        campoTextoContraseña.setBackground(new java.awt.Color(136, 201, 239));

        lblApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblApellidoMaterno.setText("Apellido Materno");

        lblNombre1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNombre1.setText("Nombre");

        lblCiudad.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblCiudad.setText("Ciudad");

        lblNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNacimiento.setText("Fecha de nacimiento (dd/mm/aaaa)");

        lblApellidoPaterno2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblApellidoPaterno2.setText("Apellido Paterno");

        lblContraseña.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblContraseña.setText("Contraseña");

        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblCorreo.setText("Correo Electronico");

        btnRegistrarse.setBackground(new java.awt.Color(8, 148, 249));
        btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegistrarse.setText("Registrarse");
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(240, 76, 76));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoTextoFecha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTextoNombre2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblApellidoPaterno2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbCiudades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(campoTextoApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addComponent(campoTextoApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTextoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(campoTextoContraseña))))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblApellidoPaterno2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoTextoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoTextoCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoCorreoActionPerformed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        // TODO add your handling code here:

        String nombres = campoTextoNombre2.getText();
        String apellidoP = campoTextoApellidoPaterno.getText();
        String apellidoM = campoTextoApellidoMaterno.getText();
        String correo = campoTextoCorreo.getText();
        String contra = campoTextoContraseña.getText();
        String fecha = campoTextoFecha.getText();
        String ciudad = (String) cbCiudades.getSelectedItem(); // Obtener la ciudad seleccionada del ComboBox

        // Convertir la fecha a un objeto Date
        String[] partesFecha = fecha.split("/");
        int dia = Integer.parseInt(partesFecha[0]);
        int mes = Integer.parseInt(partesFecha[1]);
        int año = Integer.parseInt(partesFecha[2]);
        Date fechaNacimiento = new Date(año - 1900, mes - 1, dia);

        // Crear un objeto Cliente con los datos obtenidos
        ClienteEntidad cliente = new ClienteEntidad();
        cliente.setApellidoMaterno(apellidoM);
        cliente.setApellidoPaterno(apellidoP);
        cliente.setCorreoElectronico(correo);
        cliente.setNombres(nombres);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setContra(contra);
        cliente.setCiudad(ciudad); // Establecer la ciudad seleccionada en el objeto Cliente

        try {
            // Registrar el cliente utilizando el método registrarCliente de ClienteNegocio
            clienteNegocio.registrarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente registrado con éxito");
            this.dispose();
            inicio.setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(FrmRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:        
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JTextField campoTextoApellidoMaterno;
    private javax.swing.JTextField campoTextoApellidoPaterno;
    private javax.swing.JTextField campoTextoContraseña;
    private javax.swing.JTextField campoTextoCorreo;
    private javax.swing.JTextField campoTextoFecha;
    private javax.swing.JTextField campoTextoNombre2;
    private javax.swing.JComboBox<String> cbCiudades;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jblCinepolisLogo;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno2;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNacimiento;
    private javax.swing.JLabel lblNombre1;
    // End of variables declaration//GEN-END:variables
}
