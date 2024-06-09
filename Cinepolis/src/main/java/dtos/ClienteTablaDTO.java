/**
 * 
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class ClienteTablaDTO {
    private int idCliente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private Date fechaNacimineto;
    private float latitud;
    private float longitud;
    private int idCiudadCliente;
    
    public ClienteTablaDTO() {
        
    }

    public ClienteTablaDTO(int idCliente, String nombres, String apellidoPaterno, String apellidoMaterno, String correoElectronico, Date fechaNacimineto, float latitud, float longitud, int idCiudadCliente) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.fechaNacimineto = fechaNacimineto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idCiudadCliente = idCiudadCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaNacimineto() {
        return fechaNacimineto;
    }

    public void setFechaNacimineto(Date fechaNacimineto) {
        this.fechaNacimineto = fechaNacimineto;
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

    public int getIdCiudadCliente() {
        return idCiudadCliente;
    }

    public void setIdCiudadCliente(int idCiudadCliente) {
        this.idCiudadCliente = idCiudadCliente;
    }

    @Override
    public String toString() {
        return "ClienteTablaDTO{" + "idCliente=" + idCliente 
                + ", nombres=" + nombres 
                + ", apellidoPaterno=" + apellidoPaterno 
                + ", apellidoMaterno=" + apellidoMaterno 
                + ", correoElectronico=" + correoElectronico 
                + ", fechaNacimineto=" + fechaNacimineto 
                + ", latitud=" + latitud 
                + ", longitud=" + longitud 
                + ", idCiudadCliente=" + idCiudadCliente + '}';
    }
    
    
}
