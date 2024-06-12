/**
 * 
 */
package persistencia;

import entidad.FuncionEntidad;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IFuncionDAO {
    List<FuncionEntidad> buscarFuncionesTabla() throws PersistenciaException;
    public void registrarFuncion(FuncionEntidad funcion) throws PersistenciaException;
    public void editarFuncion(FuncionEntidad funcion) throws PersistenciaException;
    public void eliminarFuncion(int idFuncion) throws PersistenciaException;
    public FuncionEntidad buscarFuncionPorPeliculaYHora(int id, String hora) throws PersistenciaException;
}
