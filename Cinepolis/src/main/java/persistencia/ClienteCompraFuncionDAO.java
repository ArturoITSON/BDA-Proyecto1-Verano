/**
 * 
 */
package persistencia;

import entidad.ClienteCompraFuncion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class ClienteCompraFuncionDAO implements IClienteCompraFuncionDAO{
    private IConexionBD conexionBD;

    public ClienteCompraFuncionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
    public void clienteCompra(ClienteCompraFuncion compra) throws PersistenciaException{
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            conexion = conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sentenciaSql = "INSERT INTO clientes_compra_funciones( fechaCompra, cantidadAsientos, costo, id_Funcion, id_Cliente) VALUES(current_date,?,?,?,?);";
            preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, compra.getAsientosComprados());
            preparedStatement.setFloat(2, compra.getCosto());
            preparedStatement.setInt(3, compra.getIdFuncionCompra());
            preparedStatement.setInt(4, compra.getIdClienteCompra());

            preparedStatement.executeUpdate();

            resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                compra.setIdClienteCompraFuncion(1);
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
            throw new PersistenciaException("Error al registrar el cliente: " + ex.getMessage());
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
}
