package silordordo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import silordordo.bo.Conexion;

public class DataBaseHelper {

    public static Connection getConexion(Conexion conexion) {
        Connection connection = null;
        try {
            Class.forName(conexion.getDriver()).newInstance();
            connection = DriverManager.getConnection(
                    conexion.getUrl() + conexion.getHost() + ":"
                    + conexion.getPort() + "/" + conexion.getBaseDatos(),
                    conexion.getUser(), conexion.getPassword());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println("Problema con la conexion" + ex.getMessage());
        }
        return connection;
    }

    public static boolean testConexion(Conexion conexion) {
        Connection connection = null;
        try {
            Class.forName(conexion.getDriver()).newInstance();
            connection = DriverManager.getConnection(
                    conexion.getUrl() + conexion.getHost() + ":"
                    + conexion.getPort() + "/" + conexion.getBaseDatos(),
                    conexion.getUser(), conexion.getPassword());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            
        }
        return (connection != null);
    }
}
