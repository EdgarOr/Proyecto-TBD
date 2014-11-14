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
import silordordo.bo.Cliente;
import silordordo.bo.Conexion;

/**
 *
 * @author VREBO
 */
public class ClienteDAOTest {
    
    private ClienteDAO instance;
    private Cliente e;
    
    public ClienteDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ClienteDAO(new Conexion());
        e = new Cliente("AMT", "Alan", "Mathison", "Turing", "1980-02-15", "now()");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of persistir method, of class ClienteDAO.
     */
    @Test
    public void testPersistir() {
        System.out.println("TEST ClienteDAO persistir");
        boolean expResult = true;
        boolean result = instance.persistir(e);
        instance.eliminar(e);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of buscarPorId method, of class ClienteDAO.
     */
    @Test
    public void testBuscarPorId() {
        System.out.println("TEST ClienteDAO buscarPorId");
        instance.persistir(e);
        Cliente result = instance.buscarPorId(e.getIdCliente());        
        assertNotNull(result);
        instance.eliminar(e);
    }

    /**
     * Test of actualizar method, of class ClienteDAO.
     */
    @Test
    public void testActualizar() {
        System.out.println("TEST ClienteDAO actualizar");
        boolean expResult = true;
        instance.persistir(e);
        boolean result = instance.actualizar(e);
        assertEquals(expResult, result);
        instance.eliminar(e);
    }

    /**
     * Test of eliminar method, of class ClienteDAO.
     */
    @Test
    public void testEliminar() {
        System.out.println("TEST ClienteDAO eliminar");
        instance.persistir(e);
        boolean expResult = true;
        boolean result = instance.eliminar(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarTodos method, of class ClienteDAO.
     */
    @Test
    public void testBuscarTodos() {
        System.out.println("TEST ClienteDAO buscarTodos");
        int expResult = 0;
        instance.persistir(e);
        List<Cliente> result = instance.buscarTodos();
        assertNotSame(expResult, result.size());
        instance.eliminar(e);
    }
    
}
