package silordordo.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import silordordo.bo.Conexion;

public abstract class GenericDAO<E, P> {

    protected Conexion conexion;

    public GenericDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    protected static Properties getProperties() {
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

    public abstract boolean persistir(E e);

    public abstract boolean actualizar(E e);

    public abstract boolean eliminar(E e);

    public abstract List<E> buscarTodos();

    public abstract E buscarPorId(P id);

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public Conexion getConexion() {
        return conexion;
    }
}
