package silordordo.bo;

public class Cliente extends Persona{
    private String idCliente;

    public Cliente(){}
    
    public Cliente(String idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String fechaRegistro) {
        super(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);
        this.fechaRegistro = fechaRegistro;
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        validaStrings(idCliente, "El id del cliente está vacio.");
        this.idCliente = idCliente;
    }
}