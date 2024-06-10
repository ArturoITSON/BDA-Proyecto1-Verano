/**
 * 
 */
package negocio;

import dtos.SucursalTablaDTO;
import entidad.SucursalEntidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ISucursalDAO;
import persistencia.PersistenciaException;
import persistencia.SucursalDAO;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class SucursalNegocio implements ISucursalNegocio {
    
    
    private ISucursalDAO sucursalDAO;

    public SucursalNegocio(ISucursalDAO sucursalDAO) {
        this.sucursalDAO = sucursalDAO;
    }

    @Override
    public List<SucursalTablaDTO> buscarSucursalesTabla() throws NegocioException {
        
        try {
            
            List<SucursalEntidad> sucursales = this.sucursalDAO.buscarSucursalesTabla();
            return this.convertirSucursalTablaDTO(sucursales);
            
        }
        
        catch (PersistenciaException ex) {
            System.out.println("aqio");
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
            dto.setSalas(sucursal.getSalas());

            sucursalDTO.add(dto);
        }
        return sucursalDTO;
    }    
    
    @Override
    public void registrarSucursal(SucursalEntidad sucursal) throws NegocioException{
        
        SucursalEntidad nuevaSucursal = new SucursalEntidad();
        
        nuevaSucursal.setCiudad(sucursal.getCiudad());
        nuevaSucursal.setLatitud(sucursal.getLatitud());
        nuevaSucursal.setLongitud(sucursal.getLongitud());
        nuevaSucursal.setNombre(sucursal.getNombre());
        nuevaSucursal.setSalas(sucursal.getSalas());
        
        try {
            sucursalDAO.registrarSucursal(nuevaSucursal);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar la sucursal: " + ex.getMessage());
        }
    
    }
    
    
    @Override
    public SucursalEntidad buscarSucursal(SucursalEntidad sucursal) throws NegocioException{
        
            SucursalEntidad nuevaSucursal = new SucursalEntidad();
        
            
        try {
            nuevaSucursal = sucursalDAO.buscarSucursal(sucursal);
            
            return nuevaSucursal;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PeliculaNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            return nuevaSucursal;
        
    
    }

    
    
   @Override 
   public void eliminarSucursal(int idSucursal) throws NegocioException{
       
        if (idSucursal <= 0) {
            throw new NegocioException("ID de Sucursal no válido.");
        }

        try {
            sucursalDAO.eliminarSucursal(idSucursal);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar la sucursal: " + ex.getMessage());
        }
       
       
   } 
    
    
}
