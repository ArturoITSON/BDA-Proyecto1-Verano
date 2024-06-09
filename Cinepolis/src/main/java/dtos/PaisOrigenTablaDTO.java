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
public class PaisOrigenTablaDTO {
    private int idPais;
    private String nombrePais;
    
    public PaisOrigenTablaDTO() {
        
    }

    public PaisOrigenTablaDTO(int idPais, String nombrePais) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public String toString() {
        return "PaisOrigenTablaDTO{" + "idPais=" + idPais 
                + ", nombrePais=" + nombrePais + '}';
    }
    
    
}
