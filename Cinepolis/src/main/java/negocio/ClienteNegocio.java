/**
 *
 */
package negocio;

import dtos.ClienteTablaDTO;
import entidad.ClienteEntidad;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import persistencia.IClienteDAO;
import persistencia.PersistenciaException;

/**
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class ClienteNegocio implements IClienteNegocio {

    private IClienteDAO clienteDAO;

    public ClienteNegocio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public List<ClienteTablaDTO> buscarClientesTabla() throws NegocioException {
        try {
            List<ClienteEntidad> clientes = this.clienteDAO.buscarClientesTabla();
            return this.convertirClienteTablaDTO(clientes);
        } catch (PersistenciaException ex) {
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<ClienteTablaDTO> convertirClienteTablaDTO(List<ClienteEntidad> clientes) throws NegocioException {
        if (clientes == null) {
            throw new NegocioException("No se pudieron obtener los clientes. Debido a que no hay registros.");
        }

        List<ClienteTablaDTO> clientesDTO = new ArrayList<>();
        for (ClienteEntidad cliente : clientes) {
            ClienteTablaDTO dto = new ClienteTablaDTO();
            dto.setIdCliente(cliente.getIdCliente());
            dto.setNombres(cliente.getNombres());
            dto.setApellidoPaterno(cliente.getApellidoPaterno());
            dto.setApellidoMaterno(cliente.getApellidoMaterno());
            dto.setCorreoElectronico(cliente.getCorreoElectronico());
            dto.setFechaNacimiento(cliente.getFechaNacimiento());
            dto.setLatitud(cliente.getLatitud());
            dto.setLongitud(cliente.getLongitud());
            dto.setIdCiudadCliente(cliente.getIdCiudadCliente());
            clientesDTO.add(dto);
        }
        return clientesDTO;
    }

    @Override
    public void registrarCliente(String nombres, String apellidoPaterno, String apellidoMaterno, String correoElectronico, Date fechaNacimiento, float latitud, float longitud, int idCiudad) throws NegocioException {
        if (nombres == null || nombres.trim().isEmpty() || apellidoPaterno == null || apellidoPaterno.trim().isEmpty() || correoElectronico == null || correoElectronico.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada en este formulario.");
        }

        if (!esNombreValido(nombres)) {
            throw new NegocioException("El nombre ingresado no es válido. Por favor, verifica que solo contenga letras y espacios.");
        }

        ClienteEntidad nuevoCliente = new ClienteEntidad(0, nombres, apellidoPaterno, apellidoMaterno, correoElectronico, fechaNacimiento, latitud, longitud, idCiudad);
        nuevoCliente.setNombres(nombres.trim());
        nuevoCliente.setApellidoPaterno(apellidoPaterno.trim());
        nuevoCliente.setApellidoMaterno(apellidoMaterno != null ? apellidoMaterno.trim() : null);

        try {
            clienteDAO.registrarCliente(nuevoCliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente: " + ex.getMessage());
        }
    }
    
    
    //Metodo prueba de registrar cliente que recibe una entidad entera
    @Override
    public void registrarCliente(ClienteEntidad cliente) throws NegocioException {

        ClienteEntidad nuevoCliente = new ClienteEntidad();
        nuevoCliente.setNombres(cliente.getNombres().trim());
        nuevoCliente.setApellidoPaterno(cliente.getApellidoPaterno().trim());
        nuevoCliente.setApellidoMaterno(cliente.getApellidoMaterno() != null ? cliente.getApellidoMaterno().trim() : null);
        nuevoCliente.setCorreoElectronico(cliente.getCorreoElectronico());
        nuevoCliente.setFechaNacimiento(cliente.getFechaNacimiento());
        nuevoCliente.setIdCiudadCliente(1);

        try {
            clienteDAO.registrarCliente(nuevoCliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente: " + ex.getMessage());
        }
    }    

    @Override
    public void editarCliente(int idCliente, String nombres, String apellidoPaterno, String apellidoMaterno, String correoElectronico, Date fechaNacimiento, float latitud, float longitud, int idCiudad) throws NegocioException {
        if (idCliente <= 0 || nombres == null || nombres.trim().isEmpty() || apellidoPaterno == null || apellidoPaterno.trim().isEmpty() || correoElectronico == null || correoElectronico.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada en este formulario.");
        }

        if (!esNombreValido(nombres)) {
            throw new NegocioException("El nombre ingresado no es válido. Por favor, verifica que solo contenga letras y espacios.");
        }

        ClienteEntidad cliente = new ClienteEntidad(idCliente, nombres, apellidoPaterno, apellidoMaterno, correoElectronico, fechaNacimiento, latitud, longitud, idCiudad);
        cliente.setIdCliente(idCliente);
        cliente.setNombres(nombres.trim());
        cliente.setApellidoPaterno(apellidoPaterno.trim());
        cliente.setApellidoMaterno(apellidoMaterno != null ? apellidoMaterno.trim() : null);

        try {
            clienteDAO.editarCliente(cliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al editar el cliente: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarCliente(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            throw new NegocioException("ID de cliente no válido.");
        }

        try {
            clienteDAO.eliminarCliente(idCliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el cliente: " + ex.getMessage());
        }
    }

    private boolean esNombreValido(String nombre) {
        return nombre.matches("^[\\p{L} .'-]+$");
    }
}
