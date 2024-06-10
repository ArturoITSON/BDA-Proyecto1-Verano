/**
 * 
 */
package negocio;

import dtos.SucursalTablaDTO;
import entidad.SucursalEntidad;
import java.util.ArrayList;
import java.util.List;
import persistencia.ISucursalDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class SucursalNegocio implements ISucursalNegocio {
    
    
    private ISucursalDAO sucursalDAO;

    public SucursalNegocio(ISucursalDAO SucursalDAO) {
        this.sucursalDAO = sucursalDAO;
    }

    @Override
    public List<SucursalTablaDTO> buscarSucursalesTabla() throws NegocioException {
        try {
            List<SucursalEntidad> sucursales = this.sucursalDAO.buscarSucursalesTabla();
            return this.convertirSucursalTablaDTO(sucursales);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<SucursalTablaDTO> convertirSucursalTablaDTO(List<SucursalEntidad> sucursales) throws NegocioException {
        if (sucursales == null) {
            throw new NegocioException("No se pudieron obtener las sucursales. No hay registros.");
        }

        List<SucursalTablaDTO> sucursalDTO = new ArrayList<>();
        for (SucursalEntidad sucursal : sucursales) {
            SucursalTablaDTO dto = new SucursalTablaDTO();
            dto.setCiudad(sucursal.getCiudad());
            dto.setIdSucursal(sucursal.getIdSucursal());
            dto.setLatitud(sucursal.getLatitud());
            dto.setLongitud(sucursal.getLongitud());
            dto.setNombre(sucursal.getNombre());

            sucursalDTO.add(dto);
        }
        return sucursalDTO;
    }    
    
    
}
