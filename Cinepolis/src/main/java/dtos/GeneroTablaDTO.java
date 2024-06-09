/**
 * 
 */
package dtos;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class GeneroTablaDTO {
    private int idGenero;
    private String nombreGenero;
    
    public GeneroTablaDTO() {
        
    }

    public GeneroTablaDTO(int idGenero, String nombreGenero) {
        this.idGenero = idGenero;
        this.nombreGenero = nombreGenero;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    @Override
    public String toString() {
        return "GeneroTablaDTO{" + "idGenero=" + idGenero 
                + ", nombreGenero=" + nombreGenero + '}';
    }
    
    
}
