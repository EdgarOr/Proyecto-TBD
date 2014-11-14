package silordordo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import silordordo.bo.Cliente;
import silordordo.bo.Conexion;
import silordordo.bo.Empleado;
import silordordo.bo.Venta;

public class VentaDAO extends GenericDAO<Venta, Long> {
    
    private final static Properties propiedades = getProperties();
    public final static String nombreTabla = propiedades.getProperty("venta-tabla");
    public final static String idVentaDAO = propiedades.getProperty("venta-id");
    public final static String fechaVentaDAO = propiedades.getProperty("venta-fecha");
    public final static String netoVentaDAO = propiedades.getProperty("venta-neto");
    
    public VentaDAO(Conexion conexion) {
        super(conexion);
    }

    @Override
    public boolean persistir(Venta e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO venta ("
                    + fechaVentaDAO + ","
                    + netoVentaDAO + ", "
                    + ClienteDAO.idClienteDAO + ","
                    + EmpleadoDAO.idEmpleadoDAO + ") "
                    + " VALUES ("
                    + " ?::date, ?, ?, ?"
                    + ");");
            ps.setString(1, e.getFechaVenta());
            ps.setDouble(2, e.getNetoVenta());
            ps.setString(3, e.getCliente().getIdCliente());
            ps.setString(4, e.getEmpleado().getIdEmpleado());
            ps.execute();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizar(Venta e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE " + nombreTabla + " SET "
                    + fechaVentaDAO + " = ?::date, "
                    + netoVentaDAO + " = ?, "
                    + ClienteDAO.idClienteDAO + " = ?, "
                    + EmpleadoDAO.idEmpleadoDAO + " = ? "
                    + "WHERE "
                    + idVentaDAO + " = ?;");
            ps.setString(1, e.getFechaVenta());
            ps.setDouble(2, e.getNetoVenta());
            ps.setString(3, e.getCliente().getIdCliente());
            ps.setString(4, e.getEmpleado().getIdEmpleado());
            ps.setLong(5, e.getIdVenta());
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(Venta e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM venta WHERE "
                    + idVentaDAO + " = ?;");
            ps.setLong(1, e.getIdVenta());
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
    public List<Venta> buscarTodos() {
        Connection con = DataBaseHelper.getConexion(conexion);
        ArrayList<Venta> lista = new ArrayList<>();
        String statement
                = "SELECT * FROM venta;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long idVenta = rs.getLong(idVentaDAO);
                    String idCliente = rs.getString(ClienteDAO.idClienteDAO);
                    String idEmpleado = rs.getString(EmpleadoDAO.idEmpleadoDAO);
                    String fechaVenta = rs.getString(fechaVentaDAO);
                    double netoVenta = rs.getDouble(netoVentaDAO);
                    
                    ClienteDAO clienteDAO = new ClienteDAO(conexion);
                    EmpleadoDAO empleadoDAO = new EmpleadoDAO(conexion);
                    Cliente cliente = clienteDAO.buscarPorId(idCliente);
                    Empleado empleado = empleadoDAO.buscarPorId(idEmpleado);
                    lista.add(new Venta(idVenta, cliente, empleado, fechaVenta, netoVenta));
                    con.commit();
                }
            }

        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return lista;
    }

    @Override
    public Venta buscarPorId(Long id) {
        Connection con = DataBaseHelper.getConexion(conexion);
        Venta e = null;
        String statement
                = "SELECT * FROM venta WHERE "
                + idVentaDAO + " = ? ;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            long idVenta = rs.getLong(idVentaDAO);
            String idCliente = rs.getString(ClienteDAO.idClienteDAO);
            String idEmpleado = rs.getString(EmpleadoDAO.idEmpleadoDAO);
            String fechaVenta = rs.getString(fechaVentaDAO);
            double netoVenta = rs.getDouble(netoVentaDAO);

            ClienteDAO clienteDAO = new ClienteDAO(conexion);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(conexion);

            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            Empleado empleado = empleadoDAO.buscarPorId(idEmpleado);

            e = new Venta(idVenta, cliente, empleado, fechaVenta, netoVenta);
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
