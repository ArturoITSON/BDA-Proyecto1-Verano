/**
 * 
 */
package entidad;
import java.sql.Date;
/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class ClienteCompraFuncion {
    private int idClienteCompraFuncion;
    private Date fechaCompra;
    private int asientosComprados;
    private float costo;
    private int idFuncionCompra;
    private int idClienteCompra;

    public ClienteCompraFuncion(int idClienteCompraFuncion, Date fechaCompra, int asientosComprados, float costo, int idFuncionCompra, int idClienteCompra) {
        this.idClienteCompraFuncion = idClienteCompraFuncion;
        this.fechaCompra = fechaCompra;
        this.asientosComprados = asientosComprados;
        this.costo = costo;
        this.idFuncionCompra = idFuncionCompra;
        this.idClienteCompra = idClienteCompra;
    }

    public ClienteCompraFuncion(int asientosComprados, float costo, int idFuncionCompra, int idClienteCompra) {
        this.asientosComprados = asientosComprados;
        this.costo = costo;
        this.idFuncionCompra = idFuncionCompra;
        this.idClienteCompra = idClienteCompra;
    }
    
    

    public int getIdClienteCompraFuncion() {
        return idClienteCompraFuncion;
    }

    public void setIdClienteCompraFuncion(int idClienteCompraFuncion) {
        this.idClienteCompraFuncion = idClienteCompraFuncion;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getAsientosComprados() {
        return asientosComprados;
    }

    public void setAsientosComprados(int asientosComprados) {
        this.asientosComprados = asientosComprados;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getIdFuncionCompra() {
        return idFuncionCompra;
    }

    public void setIdFuncionCompra(int idFuncionCompra) {
        this.idFuncionCompra = idFuncionCompra;
    }

    public int getIdClienteCompra() {
        return idClienteCompra;
    }

    public void setIdClienteCompra(int idClienteCompra) {
        this.idClienteCompra = idClienteCompra;
    }
    
    
}
