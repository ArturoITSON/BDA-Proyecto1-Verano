/**
 * 
 */
package negocio;

import dtos.FuncionTablaDTO;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IFuncionNegocio {
    public List<FuncionTablaDTO> buscarFuncionesTabla() throws NegocioException;
    
    public void registrarFuncion(String nombre, Time horaInicio, Time horaFin, 
            String dia, float precio, int idSalaFuncion, int idPeliculaFuncion) 
            throws NegocioException;
    
    public void editarFuncion(int idFuncion, Time horaInicio, Time horaFin, 
            String dia, float precio, int idSalaFuncion, int idPeliculaFuncion) 
            throws NegocioException;
    
    public void eliminarFuncion(int idFuncion) throws NegocioException;
}
