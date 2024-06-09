/**
 *
 */
package negocio;

import dtos.PeliculaTablaDTO;
import entidad.PeliculaEntidad;
import java.util.ArrayList;
import java.util.List;
import persistencia.IPeliculaDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294. René Ezequiel Figueroa
 * López - 228691. Sergio Arturo García Ramírez - 233316.
 */
public class PeliculaNegocio implements IPeliculaNegocio {

    private IPeliculaDAO peliculaDAO;

    public PeliculaNegocio(IPeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

    @Override
    public List<PeliculaTablaDTO> buscarPeliculasTabla() throws NegocioException {
        try {
            List<PeliculaEntidad> peliculas = this.peliculaDAO.buscarPeliculasTabla();
            return this.convertirPeliculaTablaDTO(peliculas);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<PeliculaTablaDTO> convertirPeliculaTablaDTO(List<PeliculaEntidad> peliculas) throws NegocioException {
        if (peliculas == null) {
            throw new NegocioException("No se pudieron obtener las películas. No hay registros.");
        }

        List<PeliculaTablaDTO> peliculasDTO = new ArrayList<>();
        for (PeliculaEntidad pelicula : peliculas) {
            PeliculaTablaDTO dto = new PeliculaTablaDTO();
            dto.setIdPelicula(pelicula.getIdPelicula());
            dto.setTituloPelicula(pelicula.getTituloPelicula());
            dto.setDuracion(pelicula.getDuracion());
            dto.setSinopsis(pelicula.getSinopsis());
            dto.setTrailer(pelicula.getTrailer());
            dto.setPaisOrigen(pelicula.getPaisOrigen());
            dto.setGeneroPelicula(pelicula.getGeneroPelicula());
            dto.setClasificacionPelicula(pelicula.getClasificacionPelicula());
            peliculasDTO.add(dto);
        }
        return peliculasDTO;
    }

    @Override
    public void registrarPelicula(String titulo, float duracion, String sinopsis, String trailer, int paisOrigen, int generoPelicula, int clasificacionPelicula) throws NegocioException {
        if (titulo == null || titulo.trim().isEmpty() || sinopsis == null || sinopsis.trim().isEmpty() || trailer == null || trailer.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada.");
        }

        PeliculaEntidad nuevaPelicula = new PeliculaEntidad(0, titulo, duracion, sinopsis, trailer, paisOrigen, generoPelicula, clasificacionPelicula);

        try {
            peliculaDAO.registrarPelicula(nuevaPelicula);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar la película: " + ex.getMessage());
        }
    }

    @Override
    public void editarPelicula(int idPelicula, String titulo, float duracion, String sinopsis, String trailer, int paisOrigen, int generoPelicula, int clasificacionPelicula) throws NegocioException {
        if (idPelicula <= 0 || titulo == null || titulo.trim().isEmpty() || sinopsis == null || sinopsis.trim().isEmpty() || trailer == null || trailer.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada.");
        }

        PeliculaEntidad pelicula = new PeliculaEntidad(idPelicula, titulo, duracion, sinopsis, trailer, paisOrigen, generoPelicula, clasificacionPelicula);

        try {
            peliculaDAO.editarPelicula(pelicula);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al editar la película: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarPelicula(int idPelicula) throws NegocioException {
        if (idPelicula <= 0) {
            throw new NegocioException("ID de película no válido.");
        }

        try {
            peliculaDAO.eliminarPelicula(idPelicula);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar la película: " + ex.getMessage());
        }
    }
}
