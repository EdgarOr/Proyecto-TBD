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
import silordordo.bo.CopiaPelicula;
import silordordo.bo.Pelicula;

public class CopiaPeliculaDAO extends GenericDAO<CopiaPelicula, Long> {

    public final static String idCopiaPeliculaDAO = "copia_id";
    public final static String codigoDAO = "copia_codigo";
    public final static String formatoDAO = "copia_fmto";
    public final static String fechaAdquisicionDAO = "copia_fechaadquisicion";
    public final static String precioDAO = "copia_precio";
    public final static String estadoDAO = "copia_edo";
    public final static String comentariosDAO = "copia_comentarios";

    public CopiaPeliculaDAO(Conexion conexion) {
        super(conexion);
    }

    @Override
    public boolean persistir(CopiaPelicula e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO copia_pelicula ("
                    + codigoDAO + ", "
                    + formatoDAO + ", "
                    + fechaAdquisicionDAO + ", "
                    + precioDAO + ", "
                    + estadoDAO + ", "
                    + comentariosDAO + ", "
                    + PeliculaDAO.idPeliculaDAO + " "
                    + ") VALUES (?, ?::copia_formato, ?::date, ?, ?::copia_estado, ?, ?);");

            ps.setString(1, e.getCodigo());
            ps.setString(2, e.getFormato());
            ps.setString(3, e.getFechaAdquisicion());
            ps.setDouble(4, e.getPrecio());
            ps.setString(5, e.getEstado());
            ps.setString(6, e.getComentarios());
            ps.setLong(7, e.getPelicula().getIdPelicula());
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
    public boolean actualizar(CopiaPelicula e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE copia_pelicula SET "
                    + codigoDAO + " = ?, "
                    + formatoDAO + " = ?::copia_formato, "
                    + fechaAdquisicionDAO + " = ?::date, "
                    + precioDAO + " = ?, "
                    + estadoDAO + " = ?::copia_estado, "
                    + comentariosDAO + " = ? "
                    + " WHERE "
                    + idCopiaPeliculaDAO + " = ? ;");
            ps.setString(1, e.getCodigo());
            ps.setString(2, e.getFormato());
            ps.setString(3, e.getFechaAdquisicion());
            ps.setDouble(4, e.getPrecio());
            ps.setString(5, e.getEstado());
            ps.setString(6, e.getComentarios());
            ps.setLong(7, e.getIdCopiaPelicula());
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(CopiaPeliculaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(CopiaPelicula e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM copia_pelicula WHERE "
                    + idCopiaPeliculaDAO
                    + " = ?;");
            ps.setLong(1, e.getIdCopiaPelicula());
            ps.execute();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(CopiaPeliculaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public List<CopiaPelicula> buscarTodos() {
        Connection con = DataBaseHelper.getConexion(conexion);
        ArrayList<CopiaPelicula> lista = new ArrayList<>();
        String statement
                = "SELECT * FROM copia_pelicula;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long idCopiaPelicula = rs.getLong(idCopiaPeliculaDAO);
                String formato = rs.getString(formatoDAO);
                String fechaAdquisicion = rs.getString(fechaAdquisicionDAO);
                String codigo = rs.getString(codigoDAO);
                String estado = rs.getString(estadoDAO);
                String comentarios = rs.getString(comentariosDAO);
                double precio = rs.getDouble(precioDAO);
                long idPelicula = rs.getLong(PeliculaDAO.idPeliculaDAO);
                PeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
                Pelicula pelicula = peliculaDAO.buscarPorId(idPelicula);
                lista.add(new CopiaPelicula(idCopiaPelicula, pelicula, codigo, formato, fechaAdquisicion, precio, estado, comentarios));
            }
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(CopiaPeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(CopiaPeliculaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return lista;
    }

    @Override
    public CopiaPelicula buscarPorId(Long id) {
        Connection con = DataBaseHelper.getConexion(conexion);
        CopiaPelicula e = null;
        String statement
                = "SELECT * FROM copia_pelicula WHERE "
                + idCopiaPeliculaDAO + " = ? ;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            long idCopiaPelicula = rs.getLong(idCopiaPeliculaDAO);
            String formato = rs.getString(formatoDAO);
            String fechaAdquisicion = rs.getString(fechaAdquisicionDAO);
            String codigo = rs.getString(codigoDAO);
            String estado = rs.getString(estadoDAO);
            String comentarios = rs.getString(comentariosDAO);
            double precio = rs.getLong(precioDAO);
            long idPelicula = rs.getLong(PeliculaDAO.idPeliculaDAO);
            PeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
            Pelicula pelicula = peliculaDAO.buscarPorId(idPelicula);
            e = new CopiaPelicula(idCopiaPelicula, pelicula, codigo, formato, fechaAdquisicion, precio, estado, comentarios);
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
