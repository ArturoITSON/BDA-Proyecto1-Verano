/**
 * 
 */
package negocio;

import dtos.PeliculaTablaDTO;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public interface IPeliculaNegocio {
    public List<PeliculaTablaDTO> buscarPeliculasTabla() throws NegocioException;
    
    public void registrarPelicula(String titulo, float duracion, 
            String sinopsis, String trailer, int paisOrigen, 
            int generoPelicula, int clasificacionPelicula) 
            throws NegocioException;
    
    public void editarPelicula(int idPelicula, String titulo, float duracion, 
            String sinopsis, String trailer, int paisOrigen, 
            int generoPelicula, int clasificacionPelicula) 
            throws NegocioException;
    
    public void eliminarPelicula(int idPelicula) throws NegocioException;
}
