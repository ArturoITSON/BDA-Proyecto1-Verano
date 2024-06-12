/**
 * 
 */
package negocio;

import java.util.List;
import persistencia.IClasificacionDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class ClasificacionNegocio implements IClasificacionNegocio {

    private final IClasificacionDAO clasificacionDAO;

    public ClasificacionNegocio(IClasificacionDAO clasificacionDAO) {
        this.clasificacionDAO = clasificacionDAO;
    }

    @Override
    public List<String> obtenerClasificaciones() throws NegocioException {
        try {
            return clasificacionDAO.obtenerClasificaciones();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener las clasificaciones");
        }
    }
}
