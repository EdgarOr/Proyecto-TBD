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
import silordordo.bo.Empleado;
import silordordo.bo.Venta;

public class VentaDAOTest {  
    
    private VentaDAO instance;
    private Venta e;
    
    public VentaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Conexion conexion = new Conexion();
        instance = new VentaDAO(conexion);
        List<Cliente> clientes = new ClienteDAO(conexion).buscarTodos();
        Cliente cliente = clientes.get(clientes.size()-1);
        List<Empleado> empleados = new EmpleadoDAO(conexion).buscarTodos();
        Empleado empleado = empleados.get(empleados.size()-1);
        e = new Venta(cliente, empleado, "now()");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of persistir method, of class VentaDAO.
     */
    @Test
    public void testPersistir() {
        System.out.println("TEST VentaDAO persistir");
        boolean expResult = true;
        boolean result = instance.persistir(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of actualizar method, of class VentaDAO.
     */
    @Test
    public void testActualizar() {
        System.out.println("TEST VentaDAO actualizar");
        boolean expResult = true;
        e.setFechaVenta("now()");
        boolean result = instance.actualizar(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminar method, of class VentaDAO.
     */
    @Test
    public void testEliminar() {
        System.out.println("TEST VentaDAO eliminar");
        boolean expResult = true;
        instance.persistir(e);
        VentaDAO ventaDAO = new VentaDAO(new Conexion());
        List<Venta> lista = ventaDAO.buscarTodos();
        Venta x = lista.get(lista.size()-1);
        boolean result = instance.eliminar(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarTodos method, of class VentaDAO.
     */
    @Test
    public void testBuscarTodos() {
        System.out.println("TEST VentaDAO buscarTodos");
        List<Venta> result = instance.buscarTodos();
        assertNotSame(0, result.size());
    }

    /**
     * Test of buscarPorId method, of class VentaDAO.
     */
    @Test
    public void testBuscarPorId() {
        System.out.println("TEST VentaDAO buscarPorId");
        VentaDAO ventaDAO = new VentaDAO(new Conexion());
        List<Venta> lista = ventaDAO.buscarTodos();
        Venta x = lista.get(lista.size()-1);
        Venta result = instance.buscarPorId(x.getIdVenta());
        assertNotNull(result);
    }
    
}
