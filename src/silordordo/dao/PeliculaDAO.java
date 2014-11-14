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
import silordordo.bo.Conexion;
import silordordo.bo.Genero;
import silordordo.bo.Pelicula;
import static silordordo.dao.GenericDAO.getProperties;

public class PeliculaDAO extends GenericDAO<Pelicula, Long> {

    private final static Properties propiedades = getProperties();
    public final static String nombreTabla = propiedades.getProperty("pelicula-tabla");
    public final static String idPeliculaDAO = propiedades.getProperty("pelicula-id");
    public final static String estelaresDAO = propiedades.getProperty("pelicula-estelares");
    public final static String anioEstrenoDAO = propiedades.getProperty("pelicula-anio");
    public final static String directorDAO = propiedades.getProperty("pelicula-director");
    public final static String duracionDAO = propiedades.getProperty("pelicula-duracion");
    public final static String clasificacionDAO = propiedades.getProperty("pelicula-clasificacion");
    public final static String tituloDAO = propiedades.getProperty("pelicula-titulo");

    public PeliculaDAO(Conexion conexion) {
        super(conexion);
    }

    @Override
    public boolean persistir(Pelicula e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO " + nombreTabla + " ("
                    + tituloDAO + ", "
                    + anioEstrenoDAO + ", "
                    + directorDAO + ", "
                    + estelaresDAO + ", "
                    + duracionDAO + ", "
                    + clasificacionDAO + ", "
                    + GeneroDAO.idGeneroDAO + " "
                    + ") VALUES (?, ?::date, ?, ?, ?::time, ?::pelicula_clasificacion, ?);");

            ps.setString(1, e.getTitulo());
            ps.setString(2, e.getAnioEstreno());
            ps.setString(3, e.getDirector());
            ps.setString(4, e.getEstelares());
            ps.setString(5, e.getDuracion());
            ps.setString(6, e.getClasificacion());
            ps.setLong(7, e.getGenero().getIdGenero());
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizar(Pelicula e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE " + nombreTabla + " SET "
                    + tituloDAO + " = ?, "
                    + anioEstrenoDAO + " = ?::date, "
                    + directorDAO + " = ?, "
                    + estelaresDAO + " = ?, "
                    + duracionDAO + " = ?::time, "
                    + clasificacionDAO + " = ?::pelicula_clasificacion, "
                    + GeneroDAO.idGeneroDAO + " = ?"
                    + "WHERE "
                    + idPeliculaDAO + " = ?;");
            ps.setString(1, e.getTitulo());
            ps.setString(2, e.getAnioEstreno());
            ps.setString(3, e.getDirector());
            ps.setString(4, e.getEstelares());
            ps.setString(5, e.getDuracion());
            ps.setString(6, e.getClasificacion());
            ps.setLong(7, e.getGenero().getIdGenero());
            ps.setLong(8, e.getIdPelicula());
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
    public boolean eliminar(Pelicula e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM " + nombreTabla  + " WHERE "
                    + idPeliculaDAO + " = ?;");
            ps.setLong(1, e.getIdPelicula());
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
    public List<Pelicula> buscarTodos() {
        Connection con = DataBaseHelper.getConexion(conexion);
        ArrayList<Pelicula> lista = new ArrayList<>();
        String statement
                = "SELECT * FROM " + nombreTabla + ";";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long idPelicula = rs.getLong(idPeliculaDAO);
                    String titulo = rs.getString(tituloDAO);
                    String estelares = rs.getString(estelaresDAO);
                    String anioEstreno = rs.getString(anioEstrenoDAO);
                    String director = rs.getString(directorDAO);
                    String clasificacion = rs.getString(clasificacionDAO);
                    String duracion = rs.getString(duracionDAO);
                    long idGenero = rs.getLong(GeneroDAO.idGeneroDAO);
                    GeneroDAO generoDAO = new GeneroDAO(conexion);
                    Genero genero = generoDAO.buscarPorId(idGenero);
                    lista.add(new Pelicula(idPelicula, genero, "portada", estelares, titulo, anioEstreno, director, clasificacion, duracion));
                    con.commit();
                }
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
    public Pelicula buscarPorId(Long id) {
        Connection con = DataBaseHelper.getConexion(conexion);
        Pelicula e = null;
        String statement
                = "SELECT * FROM " + nombreTabla + " WHERE "
                + idPeliculaDAO + " = ? ;";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            long idPelicula = rs.getLong(idPeliculaDAO);
            String titulo = rs.getString(tituloDAO);
            String estelares = rs.getString(estelaresDAO);
            String anioEstreno = rs.getString(anioEstrenoDAO);
            String director = rs.getString(directorDAO);
            String clasificacion = rs.getString(clasificacionDAO);
            String duracion = rs.getString(duracionDAO);
            long idGenero = rs.getLong(GeneroDAO.idGeneroDAO);
            GeneroDAO generoDAO = new GeneroDAO(conexion);
            Genero genero = generoDAO.buscarPorId(idGenero);
            e = new Pelicula(idPelicula, genero, "portada", estelares, titulo, anioEstreno, director, clasificacion, duracion);
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
