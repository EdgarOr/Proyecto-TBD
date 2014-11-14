package silordordo.bo;


public abstract class Persona {
    
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String fechaNacimiento;
    protected String fechaRegistro;

    public Persona(){}
    
    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void validaStrings(String string, String msjError){
        if(string.isEmpty()){
            throw new IllegalArgumentException(msjError);
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validaStrings(nombre, "El nombre es necesario.");
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        validaStrings(apellidoPaterno, "El apellido paterno es necesario.");
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        validaStrings(apellidoMaterno, "El apellido paterno es necesario.");
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        validaStrings(fechaNacimiento, "La fecha de nacimiento está vacía.");
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        validaStrings(fechaRegistro, "La fecha de registro está vacía.");
        this.fechaRegistro = fechaRegistro;
    }
    
    
}
