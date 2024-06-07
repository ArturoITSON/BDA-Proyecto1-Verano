/**
 * 
 */
package persistencia;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IConexionBD {
    public Connection crearConexion() throws SQLException;
}
