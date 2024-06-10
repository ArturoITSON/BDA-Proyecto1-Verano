/**
 * 
 */
package negocio;

import dtos.SucursalTablaDTO;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface ISucursalNegocio {
    
    
    public List<SucursalTablaDTO> buscarSucursalesTabla() throws NegocioException;

    
    
}
