/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package silordordo.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import silordordo.bo.Conexion;
import silordordo.bo.CopiaPelicula;
import silordordo.bo.Pelicula;

/**
 *
 * @author VREBO
 */
public class CopiaPeliculaDAOTest {
    
    private CopiaPeliculaDAO instance;
    private CopiaPelicula e;
    
    public CopiaPeliculaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        PeliculaDAO peliculaDAO = new PeliculaDAO(new Conexion());
        Pelicula pelicula = peliculaDAO.buscarPorId(1L);
        e = new CopiaPelicula(pelicula, "1234", "DVD", "now()", 36.43, "EN-STOCK", "");
        instance = new CopiaPeliculaDAO(new Conexion());
    }

    /**
     * Test of persistir method, of class CopiaPeliculaDAO.
     */
    @Test
    public void testPersistir() {
        System.out.println("TEST CopiaPeliculaDAO persistir");
        boolean expResult = true;
        boolean result = instance.persistir(e);
        assertEquals(expResult, result);
        instance.eliminar(e);
    }

    /**
     * Test of actualizar method, of class CopiaPeliculaDAO.
     */
    @Test
    public void testActualizar() {
        System.out.println("TEST CopiaPeliculaDAO actualizar");
        List<CopiaPelicula> x = instance.buscarTodos();
        CopiaPelicula copiaBorrada = x.get(x.size()-1);
        boolean expResult = true;
        boolean result = instance.actualizar(copiaBorrada);
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminar method, of class CopiaPeliculaDAO.
     */
    @Test
    public void testEliminar() {
        System.out.println("TEST CopiaPeliculaDAO eliminar");
        instance.persistir(e);
        List<CopiaPelicula> x = instance.buscarTodos();
        CopiaPelicula copiaBorrada = x.get(x.size()-1);
        boolean expResult = true;
        boolean result = instance.eliminar(copiaBorrada);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarTodos method, of class CopiaPeliculaDAO.
     */
    @Test
    public void testBuscarTodos() {
        System.out.println("TEST CopiaPeliculaDAO buscarTodos");
        List<CopiaPelicula> result = instance.buscarTodos();
        assertNotSame(0, result.size());
    }

    /**
     * Test of buscarPorId method, of class CopiaPeliculaDAO.
     */
    @Test
    public void testBuscarPorId() {
        System.out.println("TEST CopiaPeliculaDAO buscarPorId");
        List<CopiaPelicula> x = instance.buscarTodos();
        CopiaPelicula copiaBuscada = x.get(x.size()-1);
        CopiaPelicula result = instance.buscarPorId(copiaBuscada.getIdCopiaPelicula());
        assertNotNull(result);
    }
    
}
