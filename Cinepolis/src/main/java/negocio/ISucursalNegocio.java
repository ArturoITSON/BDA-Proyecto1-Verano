/**
 * 
 */
package negocio;

import dtos.SucursalTablaDTO;
import entidad.SucursalEntidad;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface ISucursalNegocio {
    
    
    public List<SucursalTablaDTO> buscarSucursalesTabla() throws NegocioException;
    
    public void registrarSucursal(SucursalEntidad sucursal) throws NegocioException;
    
    public SucursalEntidad buscarSucursal(SucursalEntidad sucursal) throws NegocioException;
    
    public void eliminarSucursal(int idSucursal) throws NegocioException;
    
    
}
