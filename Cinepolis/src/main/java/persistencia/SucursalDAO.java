/**
 * 
 */
package persistencia;

import entidad.SucursalEntidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class SucursalDAO implements ISucursalDAO{
    
    
    private IConexionBD conexionBD;
    

    public SucursalDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
    @Override
    public List<SucursalEntidad> buscarSucursalesTabla() throws PersistenciaException {
        List<SucursalEntidad> sucursalesLista = null;
        Connection conexion = null;
        Statement comandoSQL = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idSucursal, nombre, latitud, longitud, id_Ciudad, id_Sala FROM Sucursales";
            comandoSQL = conexion.createStatement();
            resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                if (sucursalesLista == null) {
                    sucursalesLista = new ArrayList<>();
                }
                SucursalEntidad sucursal = this.convertirAEntidad(resultado);
                sucursalesLista.add(sucursal);
            }
            return sucursalesLista;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo.");
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (comandoSQL != null) {
                    comandoSQL.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    private SucursalEntidad convertirAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idSucursal");
        String nombre = resultado.getString("nombre");
        float latitud = resultado.getFloat("latitud");
        float longitud = resultado.getFloat("longitud");
        int id_Ciudad = resultado.getInt("id_Ciudad");

        return new SucursalEntidad(id, nombre, latitud, longitud, id_Ciudad);
    }    
    
    
}
