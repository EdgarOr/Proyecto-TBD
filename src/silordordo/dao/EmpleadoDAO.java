package silordordo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import silordordo.bo.Conexion;
import silordordo.bo.Empleado;

public class EmpleadoDAO extends GenericDAO<Empleado, String> {
    
    public final static String idEmpleadoDAO = "empleado_id";
    public final static String nombreDAO = "empleado_nombre";
    public final static String apellidoPaternoDAO = "empleado_appater";
    public final static String apellidoMaternoDAO = "empleado_apmater";
    public final static String horaEntradaDAO = "empleado_horaentrada";
    public final static String horaSalidaDAO = "empleado_horasalida";
    public final static String fechaNacimientoDAO = "empleado_fechanacimiento";
    public final static String fechaRegistroDAO = "empleado_fecharegistro";
    public final static String estadoDAO = "empleado_edo";
    public final static String puestoDAO = "empleado_puesto";
    
    
    public EmpleadoDAO(Conexion conexion) {
        super(conexion);
    }

    @Override
    public boolean persistir(Empleado e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO empleado ("
                            + idEmpleadoDAO +", "
                            + nombreDAO + ", "
                            + apellidoPaternoDAO + ", "
                            + apellidoMaternoDAO + ", "
                            + horaEntradaDAO + ", "
                            + horaSalidaDAO + ", "
                            + fechaNacimientoDAO + ", "
                            + fechaRegistroDAO + ", "
                            + estadoDAO + ", "
                            + puestoDAO + " )"
                            + " VALUES (?, ?, ?, ?, ?::time, ?::time, ?::timestamp, "
                            + "now(), ?::empleado_estado, ?::empleado_puesto);");
            ps.setString(1, e.getIdEmpleado());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellidoPaterno());
            ps.setString(4, e.getApellidoMaterno());
            ps.setString(5, e.getHoraEntrada());
            ps.setString(6, e.getHoraSalida());
            ps.setString(7, e.getFechaNacimiento());
            ps.setString(8, e.getEstado());
            ps.setString(9, e.getPuesto());
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
    public boolean actualizar(Empleado e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE empleado SET "
                    + nombreDAO +" = ?, "
                    + apellidoPaternoDAO + " = ?, "
                    + apellidoMaternoDAO + " = ?, "
                    + horaEntradaDAO + " = ?::time, "
                    + horaSalidaDAO + " = ?::time, "
                    + fechaNacimientoDAO+ " = ?::timestamp, "
                    + fechaRegistroDAO + " = ?::timestamp, "
                    + estadoDAO + " = ?::empleado_estado, "
                    + puestoDAO + " = ?::empleado_puesto"
                    + " WHERE "
                            + idEmpleadoDAO + " = ?;");
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellidoPaterno());
            ps.setString(3, e.getApellidoMaterno());
            ps.setString(4, e.getHoraEntrada());
            ps.setString(5, e.getHoraSalida());
            ps.setString(6, e.getFechaNacimiento());
            ps.setString(7, e.getFechaRegistro());
            ps.setString(8, e.getEstado());
            ps.setString(9, e.getPuesto());
            ps.setString(10, e.getIdEmpleado());
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
    public boolean eliminar(Empleado e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM empleado WHERE "
                            + idEmpleadoDAO
                            + " = ?;");
            ps.setString(1, e.getIdEmpleado());
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
    public List<Empleado> buscarTodos() {
        Connection con = DataBaseHelper.getConexion(conexion);
        ArrayList<Empleado> lista = new ArrayList<>();
        String statement
                = "SELECT * FROM empleado;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            con.commit();
            con.close();
            while (rs.next()) {
                String idEmpleado = rs.getString(1);
                String nombre = rs.getString(2);
                String apellidoPaterno = rs.getString(3);
                String apellidoMaterno = rs.getString(4);
                String horaEntrada = rs.getString(5);
                String horaSalida = rs.getString(6);
                String fechaNacimiento = rs.getString(7);
                String fechaRegistro = rs.getString(8);
                String estado = rs.getString(9);
                String puesto = rs.getString(10);
                lista.add(new Empleado(idEmpleado, horaEntrada, horaSalida, estado, puesto, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro));
            }

        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return lista;
    }

    @Override
    public Empleado buscarPorId(String id) {
        Connection con = DataBaseHelper.getConexion(conexion);
        Empleado e = null;
        String statement
                = "SELECT * FROM empleado WHERE "
                + idEmpleadoDAO
                + " = ? ;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String idEmpleado = rs.getString(1);
            String nombre = rs.getString(2);
            String apellidoPaterno = rs.getString(3);
            String apellidoMaterno = rs.getString(4);
            String horaEntrada = rs.getString(5);
            String horaSalida = rs.getString(6);
            String fechaNacimiento = rs.getString(7);
            String fechaRegistro = rs.getString(8);
            String estado = rs.getString(9);
            String puesto = rs.getString(10);
            e = new Empleado(idEmpleado, horaEntrada, horaSalida, estado, puesto, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro);
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
