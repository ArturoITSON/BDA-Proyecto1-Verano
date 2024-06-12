/**
 * 
 */
package persistencia;

import entidad.PeliculaEntidad;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IPeliculaDAO {
    public List<PeliculaEntidad> buscarPeliculasTabla() throws PersistenciaException;
    public void registrarPelicula(PeliculaEntidad pelicula) throws PersistenciaException;
    public void editarPelicula(PeliculaEntidad pelicula) throws PersistenciaException;
    public void eliminarPelicula(int idPelicula) throws PersistenciaException;
    public PeliculaEntidad buscarPelicula(PeliculaEntidad peli) throws PersistenciaException;
    public String buscarPeliculaTituloString(String titulo) throws PersistenciaException;
    public PeliculaEntidad buscarPeliculaTitulo(String titulo) throws PersistenciaException;


}
