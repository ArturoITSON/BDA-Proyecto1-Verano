/**
 *
 */
package presentacion;

import dtos.PeliculaTablaDTO;
import entidad.PeliculaEntidad;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.ClasificacionNegocio;
import negocio.FuncionNegocio;
import negocio.GeneroNegocio;
import negocio.IClasificacionNegocio;
import negocio.IFuncionNegocio;
import negocio.IGeneroNegocio;
import negocio.IPeliculaNegocio;
import negocio.ISucursalNegocio;
import negocio.NegocioException;
import negocio.PeliculaNegocio;
import negocio.SucursalNegocio;
import persistencia.ClasificacionDAO;
import persistencia.ConexionBD;
import persistencia.FuncionDAO;
import persistencia.GeneroDAO;
import persistencia.IClasificacionDAO;
import persistencia.IConexionBD;
import persistencia.IFuncionDAO;
import persistencia.IGeneroDAO;
import persistencia.IPeliculaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.PersistenciaException;
import persistencia.SucursalDAO;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294. René Ezequiel Figueroa
 * López - 228691. Sergio Arturo García Ramírez - 233316.
 */
public class FrmModificarPelicula extends javax.swing.JFrame {

    // CAPA persistencia
    IConexionBD ConexionBD = new ConexionBD();
    IPeliculaDAO peliculaDAO = new PeliculaDAO(ConexionBD);
    ISucursalDAO sucursalDAO = new SucursalDAO(ConexionBD);
    IFuncionDAO funcionDAO = new FuncionDAO(ConexionBD);
    IGeneroDAO generoDAO = new GeneroDAO(ConexionBD);
    IClasificacionDAO clasificaciónDAO = new ClasificacionDAO(ConexionBD);

    // CAPA negocio
    ISucursalNegocio sucursalNegocio = new SucursalNegocio(sucursalDAO);
    IPeliculaNegocio peliculaNegocio = new PeliculaNegocio(peliculaDAO);
    IFuncionNegocio funcionNegocio = new FuncionNegocio(funcionDAO);

    PeliculaEntidad pelicula = new PeliculaEntidad();

    private final String rutaCinepolisLogo = "src/main/java/utilerias/Imagenes/CinepolisLogo.png";
    private static int id;

    /**
     * Creates new form FrmModificarPelicula
     * @param peliculaNegocio
     * @param sucursalNegocio
     * @param funcionNegocio
     */
    public FrmModificarPelicula(IPeliculaNegocio peliculaNegocio, ISucursalNegocio sucursalNegocio, IFuncionNegocio funcionNegocio) {
        initComponents();

        this.peliculaNegocio = peliculaNegocio;
        this.sucursalNegocio = sucursalNegocio;
        this.funcionNegocio = funcionNegocio;

        setImagenLabel(jblCinepolisLogo, rutaCinepolisLogo);

        btnGuardar.setVisible(false);
        btnEliminar.setVisible(false);

        cargarMetodosIniciales();
        
    }

    private void setImagenLabel(JLabel nombreJlb, String ruta) {

        ImageIcon image = new ImageIcon(ruta);

        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(nombreJlb.getWidth(), nombreJlb.getHeight(), Image.SCALE_DEFAULT));

        nombreJlb.setIcon(icon);

        this.repaint();

    }

    private void cargarGenerosEnComboBox() {
        try {
            List<String> generos = generoDAO.obtenerGeneros();
            for (String genero : generos) {
                cbGenerosPelicula.addItem(genero);
            }
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los géneros", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarMetodosIniciales() {
        this.cargarConfiguracionInicialTablaPeliculas();
        this.cargarPeliculasEnTabla();
        this.cargarGenerosEnComboBox();
        this.cargarClasificacionesEnComboBox();
        campoTextoDuracion.setEditable(false);
        campoTextoPais.setEditable(false);
        campoTextoSinopsis.setEditable(false);
        campoTextoTitulo.setEditable(false);
        campoTextoTrailer.setEditable(false);

        btnHecho.setVisible(false);
    }
    
    private void cargarClasificacionesEnComboBox() {
    try {
        List<String> clasificaciones = clasificaciónDAO.obtenerClasificaciones(); // Aquí obtienes las clasificaciones desde la capa de negocio
        cbClasificacionPelicula.removeAllItems(); // Primero limpias el comboBox
        for (String clasificacion : clasificaciones) {
            cbClasificacionPelicula.addItem(clasificacion); // Luego agregas cada clasificación al comboBox
        }
    } catch (PersistenciaException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar las clasificaciones", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void cargarConfiguracionInicialTablaPeliculas() {
        final int columnaId = 0;

    }

    private int getIdSeleccionadoTablaPeliculas() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
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
        int id = this.getIdSeleccionadoTablaPeliculas();

    }

    private void eliminar() {
        //Metodo para regresar el alumno seleccionado
        int id = this.getIdSeleccionadoTablaPeliculas();
    }

    private void llenarTablaPeliculas(List<PeliculaTablaDTO> peliculasLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPeliculas.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (peliculasLista != null) {
            peliculasLista.forEach(row -> {
                Object[] fila = new Object[7];
                fila[0] = row.getIdPelicula();
                fila[1] = row.getTituloPelicula();
                fila[2] = row.getDuracion();
                fila[3] = row.getGeneroPelicula();
                fila[4] = row.getClasificacionPelicula();
                fila[5] = row.getSinopsis();
                fila[6] = row.getTrailer();

                modeloTabla.addRow(fila);
            });
        }
    }

    private void cargarPeliculasEnTabla() {
        try {
            List<PeliculaTablaDTO> peliculas = this.peliculaNegocio.buscarPeliculasTabla();
            this.llenarTablaPeliculas(peliculas);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
            System.out.println("aqui");
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
        lblModificarPelicula = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        campoTextoTitulo = new javax.swing.JTextField();
        campoTextoPais = new javax.swing.JTextField();
        campoTextoSinopsis = new javax.swing.JTextField();
        campoTextoTrailer = new javax.swing.JTextField();
        campoTextoDuracion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblSinopsis = new javax.swing.JLabel();
        lblPais = new javax.swing.JLabel();
        lblTrailer = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblClasificacion = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        btnNuevoRegistro = new javax.swing.JButton();
        btnHecho = new javax.swing.JButton();
        cbGenerosPelicula = new javax.swing.JComboBox<>();
        cbClasificacionPelicula = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar Pelicula");
        setSize(new java.awt.Dimension(700, 550));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 599));

        jblCinepolisLogo.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jblCinepolisLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        lblModificarPelicula.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblModificarPelicula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModificarPelicula.setText("Modificar Pelicula");

        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Titulo", "Duracion", "Genero", "Clasificacion", "Sinopsis", "Trailer"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPeliculas);

        campoTextoTitulo.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoTituloActionPerformed(evt);
            }
        });

        campoTextoPais.setBackground(new java.awt.Color(136, 201, 239));

        campoTextoSinopsis.setBackground(new java.awt.Color(136, 201, 239));

        campoTextoTrailer.setBackground(new java.awt.Color(136, 201, 239));
        campoTextoTrailer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoTrailerActionPerformed(evt);
            }
        });

        campoTextoDuracion.setBackground(new java.awt.Color(136, 201, 239));

        btnGuardar.setBackground(new java.awt.Color(8, 148, 249));
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(8, 148, 249));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(8, 148, 249));
        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblTitulo.setText("Título");

        lblSinopsis.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblSinopsis.setText("Sinopsis");

        lblPais.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblPais.setText("País");

        lblTrailer.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblTrailer.setText("Trailer");

        lblGenero.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblGenero.setText("Género");

        lblClasificacion.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblClasificacion.setText("Clasificación");

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblDuracion.setText("Duración");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblModificarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoTextoTitulo)
                                    .addComponent(lblGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbGenerosPelicula, 0, 150, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbClasificacionPelicula, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(283, 283, 283))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(119, 119, 119))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblClasificacion)
                                                    .addComponent(campoTextoPais, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(156, 156, 156)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnNuevoRegistro)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnEditar))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnGuardar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnEliminar))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(lblDuracion)
                                                        .addGap(58, 58, 58))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(20, 20, 20)
                                                        .addComponent(lblSinopsis, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(campoTextoTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(campoTextoDuracion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoTextoSinopsis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 143, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(192, 192, 192)
                                .addComponent(btnHecho)))))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModificarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSinopsis, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTextoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoSinopsis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTextoTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar)
                        .addComponent(btnNuevoRegistro)
                        .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(campoTextoDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnEliminar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(cbGenerosPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbClasificacionPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnHecho)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:

        String titulo = campoTextoTitulo.getText();
        String duracion = campoTextoDuracion.getText();
        String sinopsis = campoTextoSinopsis.getText();
        String trailer = campoTextoTrailer.getText();

        pelicula.setClasificacionPelicula(1);
        pelicula.setDuracion(Float.parseFloat(duracion));
        pelicula.setPaisOrigen(1);
        pelicula.setSinopsis(sinopsis);
        pelicula.setTituloPelicula(titulo);
        pelicula.setTrailer(trailer);
        pelicula.setGeneroPelicula(1);

        try {
            peliculaNegocio.registrarPelicula(pelicula);
            JOptionPane.showMessageDialog(this, "Pelicula agregada");
            this.cargarPeliculasEnTabla();

        } catch (NegocioException ex) {
            Logger.getLogger(FrmModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnEliminar.setVisible(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        try {
            peliculaNegocio.eliminarPelicula(id);

            JOptionPane.showMessageDialog(this, "Pelicula Eliminada");
            btnEliminar.setVisible(false);
            this.cargarPeliculasEnTabla();

        } catch (NegocioException ex) {
            Logger.getLogger(FrmModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        FrmPantallaAdmin admin = new FrmPantallaAdmin(peliculaNegocio, sucursalNegocio, funcionNegocio);

        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        String texto = JOptionPane.showInputDialog("Ingrese el id de la pelicula");

        id = Integer.parseInt(texto);

        PeliculaEntidad nueva = new PeliculaEntidad();

        nueva.setIdPelicula(id);

        campoTextoDuracion.setEditable(true);
        campoTextoPais.setEditable(true);
        campoTextoSinopsis.setEditable(true);
        campoTextoTitulo.setEditable(true);
        campoTextoTrailer.setEditable(true);

        btnHecho.setVisible(true);
        btnEliminar.setVisible(true);

        try {
            pelicula = peliculaNegocio.buscarPelicula(nueva);

            campoTextoDuracion.setText(Float.toString(pelicula.getDuracion()));
            campoTextoPais.setText(Integer.toString(pelicula.getPaisOrigen()));
            campoTextoSinopsis.setText(pelicula.getSinopsis());
            campoTextoTitulo.setText(pelicula.getTituloPelicula());
            campoTextoTrailer.setText(pelicula.getTrailer());

        } catch (NegocioException ex) {
            Logger.getLogger(FrmModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void campoTextoTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoTituloActionPerformed

    private void btnNuevoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRegistroActionPerformed
        // TODO add your handling code here:
        campoTextoDuracion.setEditable(true);
        campoTextoPais.setEditable(true);
        campoTextoSinopsis.setEditable(true);
        campoTextoTitulo.setEditable(true);
        campoTextoTrailer.setEditable(true);

        campoTextoDuracion.setText("");
        campoTextoPais.setText("");
        campoTextoSinopsis.setText("");
        campoTextoTitulo.setText("");
        campoTextoTrailer.setText("");

        btnGuardar.setVisible(true);
    }//GEN-LAST:event_btnNuevoRegistroActionPerformed

    private void btnHechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHechoActionPerformed
        // TODO add your handling code here:

        String titulo = campoTextoTitulo.getText();
        String duracion = campoTextoDuracion.getText();
        String sinopsis = campoTextoSinopsis.getText();
        String trailer = campoTextoTrailer.getText();

        pelicula.setClasificacionPelicula(1);
        pelicula.setDuracion(Float.parseFloat(duracion));
        pelicula.setPaisOrigen(1);
        pelicula.setSinopsis(sinopsis);
        pelicula.setTituloPelicula(titulo);
        pelicula.setTrailer(trailer);
        pelicula.setGeneroPelicula(1);

        try {
            peliculaNegocio.editarPelicula(pelicula);
            JOptionPane.showMessageDialog(this, "Pelicula actualizada");
            this.cargarPeliculasEnTabla();

        } catch (NegocioException ex) {
            Logger.getLogger(FrmModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ok");
        }

    }//GEN-LAST:event_btnHechoActionPerformed

    private void campoTextoTrailerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoTrailerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoTrailerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHecho;
    private javax.swing.JButton btnNuevoRegistro;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JTextField campoTextoDuracion;
    private javax.swing.JTextField campoTextoPais;
    private javax.swing.JTextField campoTextoSinopsis;
    private javax.swing.JTextField campoTextoTitulo;
    private javax.swing.JTextField campoTextoTrailer;
    private javax.swing.JComboBox<String> cbClasificacionPelicula;
    private javax.swing.JComboBox<String> cbGenerosPelicula;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jblCinepolisLogo;
    private javax.swing.JLabel lblClasificacion;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblModificarPelicula;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblSinopsis;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTrailer;
    private javax.swing.JTable tblPeliculas;
    // End of variables declaration//GEN-END:variables
}
