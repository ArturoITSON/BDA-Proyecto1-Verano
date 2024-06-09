/**
 *
 */
package negocio;

import dtos.FuncionTablaDTO;
import entidad.FuncionEntidad;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import persistencia.IFuncionDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class FuncionNegocio implements IFuncionNegocio {

    private IFuncionDAO funcionDAO;

    public FuncionNegocio(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }

    @Override
    public List<FuncionTablaDTO> buscarFuncionesTabla() throws NegocioException {
        try {
            List<FuncionEntidad> funciones = this.funcionDAO.buscarFuncionesTabla();
            return this.convertirFuncionTablaDTO(funciones);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<FuncionTablaDTO> convertirFuncionTablaDTO(List<FuncionEntidad> funciones) throws NegocioException {
        if (funciones == null) {
            throw new NegocioException("No se pudieron obtener las funciones. No hay registros.");
        }

        List<FuncionTablaDTO> funcionesDTO = new ArrayList<>();
        for (FuncionEntidad funcion : funciones) {
            FuncionTablaDTO dto = new FuncionTablaDTO();
            dto.setIdFuncion(funcion.getIdFuncion());
            dto.setHoraInicio(funcion.getHoraInicio());
            dto.setHoraFinal(funcion.getHoraFinal());
            dto.setDia(funcion.getDia());
            dto.setPrecio(funcion.getPrecio());
            dto.setIdSalaFuncion(funcion.getIdSalaFuncion());
            dto.setIdPeliculaFuncion(funcion.getIdPeliculaFuncion());
            funcionesDTO.add(dto);
        }
        return funcionesDTO;
    }

    @Override
    public void registrarFuncion(String nombre, Time horaInicio, Time horaFin, String dia, float precio, int idSalaFuncion, int idPeliculaFuncion) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty() || horaInicio == null || horaFin == null || dia == null || dia.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada.");
        }

        FuncionEntidad nuevaFuncion = new FuncionEntidad(0, horaInicio, horaFin, dia, precio, idSalaFuncion, idPeliculaFuncion);

        try {
            funcionDAO.registrarFuncion(nuevaFuncion);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar la función: " + ex.getMessage());
        }
    }

    @Override
    public void editarFuncion(int idFuncion, Time horaInicio, Time horaFin, String dia, float precio, int idSalaFuncion, int idPeliculaFuncion) throws NegocioException {
        if (idFuncion <= 0 || horaInicio == null || horaFin == null || dia == null || dia.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada.");
        }

        FuncionEntidad funcion = new FuncionEntidad(idFuncion, horaInicio, horaFin, dia, precio, idSalaFuncion, idPeliculaFuncion);

        try {
            funcionDAO.editarFuncion(funcion);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al editar la función: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarFuncion(int idFuncion) throws NegocioException {
        if (idFuncion <= 0) {
            throw new NegocioException("ID de función no válido.");
        }

        try {
            funcionDAO.eliminarFuncion(idFuncion);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar la función: " + ex.getMessage());
        }
    }
}
