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
public class SalaTablaDTO {
    private int idSala;
    private String nombreSala;
    private int capacidadAsientos;
    private int tiempoLimpieza;
    private int sucursal;
    
    public SalaTablaDTO() {
        
    }

    public SalaTablaDTO(int idSala, String nombreSala, int capacidadAsientos, int tiempoLimpieza, int sucursal) {
        this.idSala = idSala;
        this.nombreSala = nombreSala;
        this.capacidadAsientos = capacidadAsientos;
        this.tiempoLimpieza = tiempoLimpieza;
        this.sucursal = sucursal;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public int getCapacidadAsientos() {
        return capacidadAsientos;
    }

    public void setCapacidadAsientos(int capacidadAsientos) {
        this.capacidadAsientos = capacidadAsientos;
    }

    public int getTiempoLimpieza() {
        return tiempoLimpieza;
    }

    public void setTiempoLimpieza(int tiempoLimpieza) {
        this.tiempoLimpieza = tiempoLimpieza;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "SalaTablaDTO{" + "idSala=" + idSala 
                + ", nombreSala=" + nombreSala 
                + ", capacidadAsientos=" + capacidadAsientos 
                + ", tiempoLimpieza=" + tiempoLimpieza 
                + ", sucursal=" + sucursal + '}';
    }
    
    
}
