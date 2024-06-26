/**
 * 
 */
package persistencia;

import entidad.SucursalEntidad;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface ISucursalDAO {
    
    
        public List<SucursalEntidad> buscarSucursalesTabla() throws PersistenciaException;
        
        public void registrarSucursal(SucursalEntidad sucursal) throws PersistenciaException;
        
        public SucursalEntidad buscarSucursal(SucursalEntidad sucursal) throws PersistenciaException;
        
        public void eliminarSucursal(int idSucursal) throws PersistenciaException;


}
