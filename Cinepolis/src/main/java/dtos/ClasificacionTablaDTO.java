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
public class ClasificacionTablaDTO {
    private int idClasificacion;
    private String nombreClasificacion;
    
    public ClasificacionTablaDTO() {
        
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    @Override
    public String toString() {
        return "ClasificacionTablaDTO{" + "idClasificacion=" + idClasificacion 
                + ", nombreClasificacion=" + nombreClasificacion + '}';
    }
    
    
}
