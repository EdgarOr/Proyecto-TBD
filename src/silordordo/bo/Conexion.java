package silordordo.bo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import silordordo.dao.ClienteDAO;

public class Conexion {
    
    private Properties propiedades;
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://";
    private String host = "localhost";
    private String port;
    private String baseDatos;
    private String user;
    private String password;

    public Conexion() {
        propiedades = getProperties();
        host = propiedades.getProperty("host");
        port = propiedades.getProperty("puerto");
        baseDatos = propiedades.getProperty("nombre-bd");
        user = propiedades.getProperty("usuario");
        password = propiedades.getProperty("password");
    }
    
    private Properties getProperties() {
        Properties defaultProps = new Properties();
        Properties props = null;
        FileInputStream fis;
        try {
            fis = new FileInputStream("res/default-properties/defaultconfig.properties");
            defaultProps.load(fis);
            props = new Properties(defaultProps);
            fis = new FileInputStream("res/properties/config.properties");
            props.load(fis);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
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