/**
 * 
 */
package persistencia;

import entidad.SucursalEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    @Override
    public void registrarSucursal(SucursalEntidad sucursal) throws PersistenciaException{
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            conexion = conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sentenciaSql = "INSERT INTO Sucursales (nombre, latitud, longitud, id_Ciudad, id_Sala) VALUES (?, ?, ?, ?, ?);";
            preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, sucursal.getNombre());
            preparedStatement.setFloat(2, sucursal.getLatitud());
            preparedStatement.setFloat(3, sucursal.getLongitud());
            preparedStatement.setInt(4, sucursal.getCiudad());
            preparedStatement.setInt(5, sucursal.getSalas());

            preparedStatement.executeUpdate();

            resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                sucursal.setIdSucursal(resultado.getInt(1));
            }

            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    throw new PersistenciaException("Error al revertir la transacción: " + e.getMessage());
                }
            }
            throw new PersistenciaException("Error al registrar la película: " + ex.getMessage());
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    
    
    }
    

    
    @Override
    public SucursalEntidad buscarSucursal(SucursalEntidad sucursal) throws PersistenciaException{
        
        SucursalEntidad nuevaSucursal = new SucursalEntidad();
        
        nuevaSucursal.setIdSucursal(sucursal.getIdSucursal());
        
        try{
        
        // Establecer la conexion a la base de datos
        Connection conexion = this.conexionBD.crearConexion();
        
        
        // Sentencia SQL para seleccionar un alumno por su id
        String sentenciaSql = "SELECT idSucursal, nombre, latitud, longitud, id_Ciudad, id_Sala FROM Sucursales WHERE idSucursal =  (?) ";
        
        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
        
        comandoSQL.setInt(1, nuevaSucursal.getIdSucursal());
        
        ResultSet resultado = comandoSQL.executeQuery();
        
        resultado.next();
            System.out.println("ss");
        
        SucursalEntidad SucursalConsultada = new SucursalEntidad(
            resultado.getInt(1),
            resultado.getString(2),
            resultado.getFloat(3),
            resultado.getFloat(4),
            resultado.getInt(5),
            resultado.getInt(6)
 
        );
            
             System.out.println("21");
            return SucursalConsultada;
            
        }

         catch(SQLException ex){
             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un errorS " + ex.getMessage());
             System.out.println("aqui dao");
         }

         
        return null;    
    }

    
    @Override
    public void eliminarSucursal(int idSucursal) throws PersistenciaException{
    
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Sucursales WHERE idSucursal = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, idSucursal);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar la Sucursal: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }        
    
    
    }

    
    
}
