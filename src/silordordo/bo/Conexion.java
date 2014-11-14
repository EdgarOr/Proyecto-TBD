package silordordo.bo;

public class Conexion {
    
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://";
    private String host = "localhost";
    private String port = "5432";
    private String baseDatos = "LOSI";
    private String user = "postgres";
    private String password = "1";

    public Conexion() {
    }

    
    public Conexion(String host, String port, String baseDatos, String user, String password) {
        this.host = host;
        this.port = port;
        this.baseDatos = baseDatos;
        this.user = user;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}