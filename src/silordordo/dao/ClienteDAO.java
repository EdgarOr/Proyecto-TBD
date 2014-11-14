package silordordo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import silordordo.bo.Cliente;
import silordordo.bo.Conexion;

public class ClienteDAO extends GenericDAO<Cliente, String> {

    public final static String idClienteDAO = "cliente_id";
    public final static String nombreDAO = "cliente_nombre";
    public final static String apellidoPaternoDAO = "cliente_appater";
    public final static String apellidoMaternoDAO = "cliente_apmater";
    public final static String fechaNacimientoDAO = "cliente_fechanacimiento";
    public final static String fechaRegistroDAO = "cliente_fecharegistro";

    public ClienteDAO(Conexion conexion) {
        super(conexion);
    }

    @Override
    public boolean persistir(Cliente e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO cliente ("
                    + idClienteDAO + ", "
                    + nombreDAO + ", "
                    + apellidoPaternoDAO + ", "
                    + apellidoMaternoDAO + ", "
                    + fechaNacimientoDAO + ", "
                    + fechaRegistroDAO + " "
                    + ") VALUES (?, ?, ?, ?, ?::timestamp, ?::timestamp);");
            ps.setString(1, e.getIdCliente());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellidoPaterno());
            ps.setString(4, e.getApellidoMaterno());
            ps.setString(5, e.getFechaNacimiento());
            ps.setString(6, e.getFechaRegistro());
            ps.execute();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizar(Cliente e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE cliente SET "
                    + nombreDAO + " = ?, "
                    + apellidoPaternoDAO + " = ?, "
                    + apellidoMaternoDAO + " = ?, "
                    + fechaNacimientoDAO + " = ?::timestamp, "
                    + fechaRegistroDAO + " = ?::timestamp "
                    + " WHERE "
                    + idClienteDAO + " = ?;");
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellidoPaterno());
            ps.setString(3, e.getApellidoMaterno());
            ps.setString(4, e.getFechaNacimiento());
            ps.setString(5, e.getFechaRegistro());
            ps.setString(6, e.getIdCliente());
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(Cliente e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM cliente WHERE "
                    + idClienteDAO + " = ?;");
            ps.setString(1, e.getIdCliente());
            ps.execute();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public List<Cliente> buscarTodos() {
        Connection con = DataBaseHelper.getConexion(conexion);
        ArrayList<Cliente> lista = new ArrayList<>();
        String statement
                = "SELECT * FROM cliente;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            con.commit();
            con.close();
            while (rs.next()) {
                String idCliente = rs.getString(idClienteDAO);
                String nombre = rs.getString(nombreDAO);
                String apellidoPaterno = rs.getString(apellidoPaternoDAO);
                String apellidoMaterno = rs.getString(apellidoMaternoDAO);
                String fechaNacimiento = rs.getString(fechaNacimientoDAO);
                String fechaRegistro = rs.getString(fechaRegistroDAO);
                lista.add(new Cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro));
            }

        } catch (SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return lista;

    }

    @Override
    public Cliente buscarPorId(String id) {
        Connection con = DataBaseHelper.getConexion(conexion);
        Cliente e = null;
        String statement
                = "SELECT * FROM cliente WHERE "
                + idClienteDAO
                + " = ? ;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            rs.next();
            String idCliente = rs.getString(idClienteDAO);
            String nombre = rs.getString(nombreDAO);
            String apellidoPaterno = rs.getString(apellidoPaternoDAO);
            String apellidoMaterno = rs.getString(apellidoMaternoDAO);
            String fechaNacimiento = rs.getString(fechaNacimientoDAO);
            String fechaRegistro = rs.getString(fechaRegistroDAO);

            e = new Cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro);

            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return e;
    }

}
