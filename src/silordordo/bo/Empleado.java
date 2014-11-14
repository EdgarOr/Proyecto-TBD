package silordordo.bo;

public class Empleado extends Persona {

    private String idEmpleado;
    private String horaEntrada;
    private String horaSalida;
    private String estado;
    private String puesto;

    public Empleado(){}
    
    public Empleado(String idEmpleado, String horaEntrada, String horaSalida, String estado, String puesto, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String fechaRegistro) {
        this(horaEntrada, horaSalida, estado, puesto, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro);
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String horaEntrada, String horaSalida, String estado, String puesto, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String fechaRegistro) {
        super(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.puesto = puesto;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    
    
}
