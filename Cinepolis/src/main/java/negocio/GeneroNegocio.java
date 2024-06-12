/**
 * 
 */
package negocio;

import java.util.List;
import persistencia.IGeneroDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class GeneroNegocio implements IGeneroNegocio {
    private final IGeneroDAO generoDAO;

    public GeneroNegocio(IGeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }

    @Override
    public List<String> obtenerGeneros() throws PersistenciaException {
        return generoDAO.obtenerGeneros();
    }
}
