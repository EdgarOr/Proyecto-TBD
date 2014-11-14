package silordordo.bo;

import java.io.File;

public class Pelicula {
    
    private long idPelicula;
    private Genero genero;
    private File portada;
    private String estelares;
    private String titulo;
    private String anioEstreno;
    private String director;
    private String clasificacion;
    private String duracion;

    public Pelicula() {
    }

    public Pelicula(Genero genero, String portada, String estelares, String titulo, String anioEstreno, String director, String clasificacion, String duracion) {
        this.genero = genero;
        this.portada = new File(portada);
        this.estelares = estelares;
        this.titulo = titulo;
        this.anioEstreno = anioEstreno;
        this.director = director;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
    }

    public Pelicula(long idPelicula, Genero genero, String portada, String estelares, String titulo, String anioEstreno, String director, String clasificacion, String duracion) {
        this(genero, portada, estelares, titulo, anioEstreno, director, clasificacion, duracion);
        this.idPelicula = idPelicula;
    }

    public long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public File getPortada() {
        return portada;
    }

    public void setPortada(File portada) {
        this.portada = portada;
    }

    public String getEstelares() {
        return estelares;
    }

    public void setEstelares(String estelares) {
        this.estelares = estelares;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(String anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    
}