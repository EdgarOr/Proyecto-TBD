package silordordo.bo;

import java.util.ArrayList;

public class Venta {
    
    private long idVenta;
    private Cliente cliente;
    private Empleado empleado;
    private String fechaVenta;
    private double netoVenta;
    private final ArrayList<CopiaPelicula> detalleVenta;

    public Venta() {
        detalleVenta = new ArrayList<>();
    }

    public Venta(long idVenta, Cliente cliente, Empleado empleado, String fechaVenta, double netoVenta) {
        this(cliente, empleado, fechaVenta);
        this.idVenta = idVenta;
        this.netoVenta = netoVenta;
    }
    
    
    
    public Venta(long idVenta, Cliente cliente, Empleado empleado, String fechaVenta){
        this(cliente, empleado, fechaVenta);
        this.idVenta = idVenta;
    }
    
    public Venta(Cliente cliente, Empleado empleado, String fechaVenta) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaVenta = fechaVenta;
        netoVenta = 0;
        detalleVenta = new ArrayList<>();
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }
       
    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getNetoVenta() {
        return netoVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void calculaNetoVenta() {
        detalleVenta.stream().forEach((copia) -> {
            netoVenta += copia.getPrecio();
        });
    }

    public void addCopiaPelicula(CopiaPelicula copia) {
        this.detalleVenta.add(copia);
    }
}
