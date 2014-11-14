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
import silordordo.bo.Genero;

public class GeneroDAO extends GenericDAO<Genero, Long> {

    public final static String idGeneroDAO = "genero_id";
    public final static String nombreDAO = "genero_nombre";
    public final static String descripcionDAO = "genero_descripcion";

    public GeneroDAO(Conexion conexion) {
        super(conexion);
    }

    @Override
    public boolean persistir(Genero e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO genero ("
                    + nombreDAO + ", "
                    + descripcionDAO + ")"
                    + " VALUES (?, ?);");
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDescripcion());
            ps.execute();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizar(Genero n) {
        Connection con = DataBaseHelper.getConexion(conexion);
        String statement
                = "UPDATE genero SET "
                + nombreDAO + " = ?, "
                + descripcionDAO + " = ? WHERE "
                + idGeneroDAO
                + " = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, n.getNombre());
            ps.setString(2, n.getDescripcion());
            ps.setLong(3, n.getIdGenero());
            ps.executeUpdate();
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
            return false;
        }
        return true;
    }

    @Override
    public List<Genero> buscarTodos() {
        Connection con = DataBaseHelper.getConexion(conexion);
        ArrayList<Genero> lista = new ArrayList<>();
        String statement
                = "SELECT * FROM genero;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            con.commit();
            con.close();
            while (rs.next()) {
                long idGenero = rs.getLong(idGeneroDAO);
                String nombre = rs.getString(nombreDAO);
                String descripcion = rs.getString(descripcionDAO);
                lista.add(new Genero(idGenero, nombre, descripcion));
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
    public boolean eliminar(Genero e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        String statement
                = "DELETE FROM genero WHERE "
                + idGeneroDAO
                + " = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setLong(1, e.getIdGenero());
            ps.executeUpdate();
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
            return false;
        }
        return true;
    }

    @Override
    public Genero buscarPorId(Long id) {
        Connection con = DataBaseHelper.getConexion(conexion);
        Genero e = null;
        String statement
                = "SELECT * FROM genero WHERE "
                + idGeneroDAO
                + " = ?;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            long idGenero = rs.getLong(1);
            String nombre = rs.getString(2);
            String descripcion = rs.getString(3);
            e = new Genero(idGenero, nombre, descripcion);
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
