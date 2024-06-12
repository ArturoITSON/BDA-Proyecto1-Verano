/**
 *
 */
package persistencia;

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
public class ClasificacionDAO implements IClasificacionDAO {

    private final IConexionBD conexionBD;

    public ClasificacionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<String> obtenerClasificaciones() throws PersistenciaException {
        List<String> clasificaciones = new ArrayList<>();
        String query = "SELECT nombre FROM Clasificaciones";

        try (Connection conexion = conexionBD.crearConexion(); PreparedStatement stmt = conexion.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clasificaciones.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener las clasificaciones");
        }

        return clasificaciones;
    }
}
