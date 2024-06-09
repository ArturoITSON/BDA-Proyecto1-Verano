/**
 *
 */
package negocio;

import dtos.ClienteTablaDTO;
import java.sql.Date;
import java.util.List;

/**
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IClienteNegocio {

    public List<ClienteTablaDTO> buscarClientesTabla() throws NegocioException;

    public void registrarCliente(String nombres, String apellidoPaterno, 
            String apellidoMaterno, String correoElectronico, 
            Date fechaNacimiento, float latitud, float longitud, 
            int idCiudad) throws NegocioException;
    
     public void editarCliente(int idCliente, String nombres, 
             String apellidoPaterno, String apellidoMaterno, 
             String correoElectronico, Date fechaNacimiento, float latitud, 
             float longitud, int idCiudad) throws NegocioException;
     
         public void eliminarCliente(int idCliente) throws NegocioException;
         
         
}
