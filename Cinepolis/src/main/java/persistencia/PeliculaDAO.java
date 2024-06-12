/**
 *
 */
package persistencia;

import entidad.PeliculaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class PeliculaDAO implements IPeliculaDAO {

    private IConexionBD conexionBD;

    public PeliculaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<PeliculaEntidad> buscarPeliculasTabla() throws PersistenciaException {
        List<PeliculaEntidad> peliculasLista = null;
        Connection conexion = null;
        Statement comandoSQL = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idPelicula, titulo, duración, sinopsis, trailer, idPais, idGenero, id_Clasificacion FROM Peliculas";
            comandoSQL = conexion.createStatement();
            resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                if (peliculasLista == null) {
                    peliculasLista = new ArrayList<>();
                }
                PeliculaEntidad pelicula = this.convertirAEntidad(resultado);
                peliculasLista.add(pelicula);
            }
            return peliculasLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo.");
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (comandoSQL != null) {
                    comandoSQL.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    private PeliculaEntidad convertirAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idPelicula");
        String titulo = resultado.getString("titulo");
        float duracion = resultado.getFloat("duración");
        String sinopsis = resultado.getString("sinopsis");
        String trailer = resultado.getString("trailer");
        int paisOrigen = resultado.getInt("idPais");
        int genero = resultado.getInt("idGenero");
        int clasificacion = resultado.getInt("id_Clasificacion");

        return new PeliculaEntidad(id, titulo, duracion, sinopsis, trailer, paisOrigen, genero, clasificacion);
    }

    @Override
    public void registrarPelicula(PeliculaEntidad pelicula) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            conexion = conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sentenciaSql = "INSERT INTO Peliculas (titulo, duración, sinopsis, trailer, idPais, idGenero, id_Clasificacion) VALUES (?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, pelicula.getTituloPelicula());
            preparedStatement.setFloat(2, pelicula.getDuracion());
            preparedStatement.setString(3, pelicula.getSinopsis());
            preparedStatement.setString(4, pelicula.getTrailer());
            preparedStatement.setInt(5, pelicula.getPaisOrigen());
            preparedStatement.setInt(6, pelicula.getGeneroPelicula());
            preparedStatement.setInt(7, pelicula.getClasificacionPelicula());

            preparedStatement.executeUpdate();

            resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                pelicula.setIdPelicula(resultado.getInt(1));
            }

            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    throw new PersistenciaException("Error al revertir la transacción: " + e.getMessage());
                }
            }
            throw new PersistenciaException("Error al registrar la película: " + ex.getMessage());
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public void editarPelicula(PeliculaEntidad pelicula) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "UPDATE Peliculas SET titulo = ?, duración = ?, sinopsis = ?, trailer = ?, idPais = ?, idGenero = ?, id_Clasificacion = ? WHERE idPelicula = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setString(1, pelicula.getTituloPelicula());
            preparedStatement.setFloat(2, pelicula.getDuracion());
            preparedStatement.setString(3, pelicula.getSinopsis());
            preparedStatement.setString(4, pelicula.getTrailer());
            preparedStatement.setInt(5, pelicula.getPaisOrigen());
            preparedStatement.setInt(6, pelicula.getGeneroPelicula());
            preparedStatement.setInt(7, pelicula.getClasificacionPelicula());
            preparedStatement.setInt(8, pelicula.getIdPelicula());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al editar la película: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public void eliminarPelicula(int idPelicula) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Peliculas WHERE idPelicula = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, idPelicula);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar la película: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
    
    
    
    public PeliculaEntidad buscarPelicula(PeliculaEntidad peli) throws PersistenciaException{
    
        PeliculaEntidad nuevaPeli = new PeliculaEntidad();
        
        nuevaPeli.setIdPelicula(peli.getIdPelicula());
        
        try{
        
        // Establecer la conexion a la base de datos
        Connection conexion = this.conexionBD.crearConexion();
        
        
        // Sentencia SQL para seleccionar un alumno por su id
        String sentenciaSql = "SELECT * FROM Peliculas WHERE idPelicula =  (?) ";
        
        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
        
        comandoSQL.setInt(1, nuevaPeli.getIdPelicula());
        
        ResultSet resultado = comandoSQL.executeQuery();
        
        resultado.next();
            
        PeliculaEntidad peliConsultada = convertirAEntidad(resultado);
            
             System.out.println("21");
            return peliConsultada;
            
        }

         catch(SQLException ex){
             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un errorS " + ex.getMessage());
             System.out.println("aqui dao");
         }

         
        return null;
        
        
    }

    public PeliculaEntidad buscarPeliculaTitulo(String titulo) throws PersistenciaException{
    
        PeliculaEntidad nuevaPeli = new PeliculaEntidad();
        
        
        try{
        
        // Establecer la conexion a la base de datos
        Connection conexion = this.conexionBD.crearConexion();
        
        
        // Sentencia SQL para seleccionar un alumno por su id
        String sentenciaSql = "SELECT idPelicula, titulo, duración, sinopsis, trailer, idPais, idGenero, id_Clasificacion FROM peliculas WHERE titulo=?;";
        
        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
        
        comandoSQL.setString(1, titulo);
        
        ResultSet resultado = comandoSQL.executeQuery();
        
        resultado.next();
        
        PeliculaEntidad peliConsultada = new PeliculaEntidad(
            resultado.getInt(1),
            resultado.getString(2),
            resultado.getInt(3),
            resultado.getString(4),
            resultado.getString(5),
            resultado.getInt(6),
            resultado.getInt(7),
            resultado.getInt(8)
 
        );
            
             System.out.println("21");
            return peliConsultada;
            
        }

         catch(SQLException ex){
             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un errorS " + ex.getMessage());
             System.out.println("aqui dao");
         }

         
        return null;
        
        
    }
    
    
}
