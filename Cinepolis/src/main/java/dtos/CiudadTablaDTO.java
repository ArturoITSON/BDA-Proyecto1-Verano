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
public class CiudadTablaDTO {
    private int idCiudad;
    private String nombreCiudad;
    private int cantHabitantes;
    private int pais;
    
    public CiudadTablaDTO() {
        
    }

    public CiudadTablaDTO(int idCiudad, String nombreCiudad, int cantHabitantes, int pais) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        this.cantHabitantes = cantHabitantes;
        this.pais = pais;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public int getCantHabitantes() {
        return cantHabitantes;
    }

    public void setCantHabitantes(int cantHabitantes) {
        this.cantHabitantes = cantHabitantes;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "CiudadTablaDTO{" + "idCiudad=" + idCiudad 
                + ", nombreCiudad=" + nombreCiudad 
                + ", cantHabitantes=" + cantHabitantes 
                + ", pais=" + pais + '}';
    }
    
    
}
