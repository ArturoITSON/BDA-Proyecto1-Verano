/**
 * 
 */
package persistencia;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 */
public interface IConexionBD {
    public Connection crearConexion() throws SQLException;
}
