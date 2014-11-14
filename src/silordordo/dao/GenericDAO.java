package silordordo.dao;

import java.util.List;
import silordordo.bo.Conexion;

public abstract class GenericDAO<E,P> {
    
    protected Conexion conexion;
    
    public GenericDAO(Conexion conexion){
        this.conexion = conexion;
    }
    
    public abstract boolean persistir(E e);
    
    public abstract boolean actualizar(E e);
    
    public abstract boolean eliminar(E e);
    
    public abstract List<E> buscarTodos();
    
    public abstract E buscarPorId(P id);
    
    public void setConexion(Conexion conexion){
        this.conexion = conexion;
    }
    
    public Conexion getConexion(){
        return conexion;
    }
}