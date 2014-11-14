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
import silordordo.bo.Empleado;

/**
 *
 * @author VREBO
 */
public class EmpleadoDAOTest {
    
    private EmpleadoDAO instance;
    private Empleado e;
    public EmpleadoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new EmpleadoDAO(new Conexion());
        e = new Empleado("E","7:00", "17:00","TRABAJANDO", "GERENTE", "Alan", "Turing", "Algo", "04-09-1994", "now()");
    }
        
    @After
    public void tearDown() {
        instance.eliminar(e);
    }

    /**
     * Test of persistir method, of class EmpleadoDAO.
     */
    @Test
    public void testPersistir() {
        System.out.println("TEST EmpleadoDAO persistir");
        boolean result = instance.persistir(e);
        assertTrue(result);
        instance.eliminar(e);
    }

    /**
     * Test of actualizar method, of class EmpleadoDAO.
     */
    @Test
    public void testActualizar() {
        System.out.println("TEST EmpleadoDAO actualizar");
        instance.persistir(e);
        e.setEstado("INCAPACITADO");
        boolean result = instance.actualizar(e);
        instance.eliminar(e);
        assertTrue(result);
    }

    /**
     * Test of eliminar method, of class EmpleadoDAO.
     */
    @Test
    public void testEliminar() {
        System.out.println("TEST EmpleadoDAO eliminar");
        instance.persistir(e);
        boolean expResult = true;
        boolean result = instance.eliminar(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarTodos method, of class EmpleadoDAO.
     */
    @Test
    public void testBuscarTodos() {
        System.out.println("TEST EmpleadoDAO buscarTodos");
        int expResult = 0;
        instance.persistir(e);
        List<Empleado> result = instance.buscarTodos();
        assertNotSame(expResult, result.size());
    }

    /**
     * Test of buscarPorId method, of class EmpleadoDAO.
     */
    @Test
    public void testBuscarPorId() {
        System.out.println("TEST EmpleadoDAO buscarPorId");
        instance.persistir(e);
        Empleado result = instance.buscarPorId(e.getIdEmpleado());
        assertNotNull(result);
    }
    
}
