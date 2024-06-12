/**
 *
 */
package persistencia;

import entidad.FuncionEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class FuncionDAO implements IFuncionDAO {

    private IConexionBD conexionBD;

    public FuncionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<FuncionEntidad> buscarFuncionesTabla() throws PersistenciaException {
        try {
            List<FuncionEntidad> funcionesLista = null;
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idFuncion, horaInicio, horaAcaba, dia, precio, idSala, id_Pelicula FROM Funciones";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
                if (funcionesLista == null) {
                    funcionesLista = new ArrayList<>();
                }
                FuncionEntidad funcion = this.convertirAEntidad(resultado);
                funcionesLista.add(funcion);
            }
            conexion.close();
            return funcionesLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo.");
        }

    }

    private FuncionEntidad convertirAEntidad(ResultSet resultado) throws SQLException {
        int idFuncion = resultado.getInt("idFuncion");
        Time horaInicio = resultado.getTime("horaInicio");
        Time horaAcaba = resultado.getTime("horaAcaba");
        String dia = resultado.getString("dia");
        float precio = resultado.getFloat("precio");
        int idSala = resultado.getInt("idSala");
        int idPelicula = resultado.getInt("id_Pelicula");

        return new FuncionEntidad(idFuncion, horaInicio, horaAcaba, dia, precio, idSala, idPelicula);
    }

    @Override
    public void registrarFuncion(FuncionEntidad funcion) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            conexion = conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sentenciaSql = "INSERT INTO Funciones (horaInicio, horaAcaba, dia, precio, idSala, id_Pelicula) VALUES (?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setTime(1, funcion.getHoraInicio());
            preparedStatement.setTime(2, funcion.getHoraFinal());
            preparedStatement.setString(3, funcion.getDia());
            preparedStatement.setFloat(4, funcion.getPrecio());
            preparedStatement.setInt(5, funcion.getIdSalaFuncion());
            preparedStatement.setInt(6, funcion.getIdFuncion());

            preparedStatement.executeUpdate();

            resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                funcion.setIdFuncion(resultado.getInt(1));
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
            throw new PersistenciaException("Error al registrar la función: " + ex.getMessage());
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
    public void editarFuncion(FuncionEntidad funcion) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "UPDATE Funciones SET horaInicio = ?, horaAcaba = ?, dia = ?, precio = ?, idSala = ?, id_Pelicula = ? WHERE idFuncion = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setTime(1, funcion.getHoraInicio());
            preparedStatement.setTime(2, funcion.getHoraFinal());
            preparedStatement.setString(3, funcion.getDia());
            preparedStatement.setFloat(4, funcion.getPrecio());
            preparedStatement.setInt(5, funcion.getIdSalaFuncion());
            preparedStatement.setInt(6, funcion.getIdFuncion());
            preparedStatement.setInt(7, funcion.getIdPeliculaFuncion());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al editar la función: " + ex.getMessage());
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
    public void eliminarFuncion(int idFuncion) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM Funciones WHERE idFuncion = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, idFuncion);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar la función: " + ex.getMessage());
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
    
    public List<FuncionEntidad> buscarFuncionesPorPelicula(int id) throws PersistenciaException {
        PreparedStatement preparedStatement = null;
        try {
            List<FuncionEntidad> funcionesLista = null;
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idFuncion, horaInicio, horaAcaba, dia, precio, idSala, id_Pelicula FROM Funciones where id_Pelicula = ?";
            preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                if (funcionesLista == null) {
                    funcionesLista = new ArrayList<>();
                }
                FuncionEntidad funcion = this.convertirAEntidad(resultado);
                funcionesLista.add(funcion);
            }
            conexion.close();
            return funcionesLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo.");
        }

    }
}
