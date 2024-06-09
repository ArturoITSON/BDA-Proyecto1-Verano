/**
 * 
 */
package dtos;

import java.sql.Time;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class FuncionTablaDTO {
    private int idFuncion;
    private Time horaInicio;
    private Time horaFinal;
    private String dia;
    private float precio;
    private int idSalaFuncion;
    private int idPeliculaFuncion;
    
    public FuncionTablaDTO() {
        
    }

    public FuncionTablaDTO(int idFuncion, Time horaInicio, Time horaFinal, String dia, float precio, int idSalaFuncion, int idPeliculaFuncion) {
        this.idFuncion = idFuncion;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.dia = dia;
        this.precio = precio;
        this.idSalaFuncion = idSalaFuncion;
        this.idPeliculaFuncion = idPeliculaFuncion;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdSalaFuncion() {
        return idSalaFuncion;
    }

    public void setIdSalaFuncion(int idSalaFuncion) {
        this.idSalaFuncion = idSalaFuncion;
    }

    public int getIdPeliculaFuncion() {
        return idPeliculaFuncion;
    }

    public void setIdPeliculaFuncion(int idPeliculaFuncion) {
        this.idPeliculaFuncion = idPeliculaFuncion;
    }

    @Override
    public String toString() {
        return "FuncionTablaDTO{" + "idFuncion=" + idFuncion 
                + ", horaInicio=" + horaInicio 
                + ", horaFinal=" + horaFinal 
                + ", dia=" + dia 
                + ", precio=" + precio 
                + ", idSalaFuncion=" + idSalaFuncion 
                + ", idPeliculaFuncion=" + idPeliculaFuncion + '}';
    }
    
    
}
