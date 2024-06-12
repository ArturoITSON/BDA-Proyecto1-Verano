/**
 *
 */
package persistencia;

import entidad.GeneroEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294. René Ezequiel Figueroa
 * López - 228691. Sergio Arturo García Ramírez - 233316.
 */
public class GeneroDAO {

    private IConexionBD conexionBD;

    public GeneroDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public String obtenerGeneroDePelicula(int id_pelicula) throws PersistenciaException {
        String genero= null;
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idGenero, nombre FROM generos where idGenero = ?;";
            
            PreparedStatement comandoSQL = conexion.prepareStatement(codigoSQL);
            comandoSQL.setInt(1, id_pelicula);
            
            ResultSet resultado = comandoSQL.executeQuery();
            while (resultado.next()) {
            genero = resultado.getString("nombre");
        }
            return genero;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos de generos, inténtelo de nuevo.");
        } 
    }
    
    private GeneroEntidad convertirAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idGenero");
        String nombres = resultado.getString("nombre");

        return new GeneroEntidad(id, nombres);
    }
}
