/**
 *
 */
package persistencia;

import entidad.ClienteEntidad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class ClienteDAO implements IClienteDAO {

    private IConexionBD conexionBD;

    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<ClienteEntidad> buscarClientesTabla() throws PersistenciaException {
        List<ClienteEntidad> clientesLista = null;
        Connection conexion = null;
        Statement comandoSQL = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idCliente, nombres, apellidoPaterno, apellidoMaterno, correoElectrónico, fechaNacimiento, ubicación, id_Ciudad FROM Clientes";
            comandoSQL = conexion.createStatement();
            resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                if (clientesLista == null) {
                    clientesLista = new ArrayList<>();
                }
                ClienteEntidad cliente = this.convertirAEntidad(resultado);
                clientesLista.add(cliente);
            }
            return clientesLista;
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

    private ClienteEntidad convertirAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idCliente");
        String nombres = resultado.getString("nombres");
        String apellidoPaterno = resultado.getString("apellidoPaterno");
        String apellidoMaterno = resultado.getString("apellidoMaterno");
        String correoElectronico = resultado.getString("correoElectrónico");
        Date fechaNacimiento = resultado.getDate("fechaNacimiento");
        float latitud = resultado.getFloat("latitud");
        float longitud = resultado.getFloat("latitud");
        int idCiudad = resultado.getInt("id_Ciudad");

        return new ClienteEntidad(id, nombres, apellidoPaterno, apellidoMaterno, correoElectronico, fechaNacimiento, latitud, longitud, idCiudad);
    }

    @Override
    public void registrarCliente(ClienteEntidad cliente) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            conexion = conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sentenciaSql = "INSERT INTO Clientes (nombres, apellidoPaterno, apellidoMaterno, correoElectrónico, fechaNacimiento, ubicación, id_Ciudad, contraseña) VALUES (?, ?, ?, ?, ?, POINT(?, ?), ?, ?);";
            preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidoPaterno());
            preparedStatement.setString(3, cliente.getApellidoMaterno());
            preparedStatement.setString(4, cliente.getCorreoElectronico());
            preparedStatement.setDate(5, cliente.getFechaNacimiento());
            preparedStatement.setFloat(6, cliente.getLongitud());
            preparedStatement.setFloat(7, cliente.getLatitud());
            preparedStatement.setInt(8, cliente.getIdCiudadCliente());
            preparedStatement.setString(9, cliente.getContra());

            preparedStatement.executeUpdate();

            resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                cliente.setIdCliente(resultado.getInt(1));
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

    @Override
    public void editarCliente(ClienteEntidad cliente) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "UPDATE Clientes SET nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, correoElectrónico = ?, fechaNacimiento = ?, ubicación = POINT(?, ?), id_Ciudad = ? WHERE idCliente = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidoPaterno());
            preparedStatement.setString(3, cliente.getApellidoMaterno());
            preparedStatement.setString(4, cliente.getCorreoElectronico());
            preparedStatement.setDate(5, cliente.getFechaNacimiento());
            preparedStatement.setDouble(6, cliente.getLongitud());
            preparedStatement.setDouble(7, cliente.getLatitud());
            preparedStatement.setInt(8, cliente.getIdCiudadCliente());
            preparedStatement.setInt(9, cliente.getIdCliente());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al editar el cliente: " + ex.getMessage());
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

    @Override
    public void eliminarCliente(int idCliente) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Clientes WHERE idCliente = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, idCliente);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar el cliente: " + ex.getMessage());
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
    
    
    public ClienteEntidad buscarCliente(ClienteEntidad cliente) throws PersistenciaException{
    
        ClienteEntidad cliEnti = new ClienteEntidad();
        
        cliEnti.setCorreoElectronico(cliente.getCorreoElectronico());
        cliEnti.setContra(cliente.getContra());
        
        try{
        
        // Establecer la conexion a la base de datos
        Connection conexion = this.conexionBD.crearConexion();
        
        
        // Sentencia SQL para seleccionar un alumno por su id
        String sentenciaSql = "SELECT * FROM Clientes WHERE correoElectrónico =  (?) ";
        
        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
        
        comandoSQL.setString(1, cliEnti.getCorreoElectronico());
        
        ResultSet resultado = comandoSQL.executeQuery();
        
        resultado.next();
            System.out.println("ss");
        
        ClienteEntidad ClienteConsultado = new ClienteEntidad(
            resultado.getInt(1),
            resultado.getString(2),
            resultado.getString(3),
            resultado.getString(4),
            resultado.getString(5),
            resultado.getDate(6),
            resultado.getFloat(7),
            resultado.getFloat(8),
            resultado.getInt(9)
 
        );
            
             System.out.println("21");
            return ClienteConsultado;
            
        }

         catch(SQLException ex){
             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un errorS " + ex.getMessage());
             System.out.println("aqui dao");
         }

         
        return null;
        
    
    }
}
