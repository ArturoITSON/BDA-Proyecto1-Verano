/**
 * 
 */
package persistencia;

import entidad.ClienteCompraFuncion;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IClienteCompraFuncionDAO {
    public void clienteCompra(ClienteCompraFuncion compra) throws PersistenciaException;
}
