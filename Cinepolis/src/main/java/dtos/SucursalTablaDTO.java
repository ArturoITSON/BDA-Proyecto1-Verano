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
public class SucursalTablaDTO {
    private int idSucursal;
    private String nombre;
    private float latitud;
    private float longitud;
    private int ciudad;
    private int salas;

    public SucursalTablaDTO(int idSucursal, String nombre, float latitud, float longitud, int ciudad, int salas) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
        this.salas = salas;
    }

    public SucursalTablaDTO(int idSucursal, String nombre, float latitud, float longitud, int ciudad) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
    }

    public int getSalas() {
        return salas;
    }

    public void setSalas(int salas) {
        this.salas = salas;
    }
    
    
    public SucursalTablaDTO() {
        
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public int getCiudad() {
        return ciudad;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "SucursalTablaDTO{" + "idSucursal=" + idSucursal 
                + ", nombre=" + nombre 
                + ", latitud=" + latitud 
                + ", longitud=" + longitud 
                + ", ciudad=" + ciudad + '}';
    }
    
    
}
