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
import silordordo.bo.Pelicula;

public class PeliculaDAO extends GenericDAO<Pelicula, Long> {

    public final static String idPeliculaDAO = "pelicula_id";
    public final static String estelaresDAO = "pelicula_estelares";
    public final static String anioEstrenoDAO = "pelicula_anioestreno";
    public final static String directorDAO = "pelicula_director";
    public final static String duracionDAO = "pelicula_duracion";
    public final static String clasificacionDAO = "pelicula_clasif";
    public final static String tituloDAO = "pelicula_titulo";

    public PeliculaDAO(Conexion conexion) {
        super(conexion);
    }

    @Override
    public boolean persistir(Pelicula e) {
        Connection con = DataBaseHelper.getConexion(conexion);
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO pelicula ("
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
                    "UPDATE pelicula SET "
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
                    "DELETE FROM pelicula WHERE "
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
                = "SELECT * FROM pelicula;";
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
                = "SELECT * FROM pelicula WHERE "
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
            e = new Pelicula(idPelicula, genero, "genero", estelares, titulo, anioEstreno, director, clasificacion, duracion);
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
