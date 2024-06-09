/**
 * 
 */
package persistencia;

import entidad.ClienteEntidad;
import entidad.FuncionEntidad;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IClienteDAO {
    List<ClienteEntidad> buscarClientesTabla() throws PersistenciaException;
    public void registrarCliente(ClienteEntidad cliente) throws PersistenciaException;
    public void editarCliente(ClienteEntidad cliente) throws PersistenciaException;
    public void eliminarCliente(int idCliente) throws PersistenciaException;
}
