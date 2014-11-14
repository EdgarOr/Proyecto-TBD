package silordordo.dao;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import silordordo.bo.Conexion;

/**
 *
 * @author VREBO
 */
public class DataBaseHelperTest {
    
    public DataBaseHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConexion method, of class DataBaseHelper.
     */
    @Test
    public void testGetConexion() {
        System.out.println("TEST DataBaseHelper getConexion");
        Conexion conexion = new Conexion();
        Connection result = DataBaseHelper.getConexion(conexion);
        assertNotNull(result);
    }

    /**
     * Test of testConexion method, of class DataBaseHelper.
     */
    @Test
    public void testTestConexion() {
        System.out.println("TEST DataBaseHelper testConexion");
        Conexion conexion = new Conexion();
        boolean expResult = true;
        boolean result = DataBaseHelper.testConexion(conexion);
        assertEquals(expResult, result);
    }
    
}
