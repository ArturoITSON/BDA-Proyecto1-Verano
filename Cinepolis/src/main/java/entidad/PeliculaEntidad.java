/**
 * 
 */
package entidad;

/**
 *
 * @author/(s): Daniel Alejandro Castro Félix - 235294.
 *              René Ezequiel Figueroa López - 228691.
 *              Sergio Arturo García Ramírez - 233316.
 */
public class PeliculaEntidad {
    private int idPelicula;
    private String tituloPelicula;
    private float duracion;
    private String sinopsis;
    private String trailer;
    private int paisOrigen;
    private int generoPelicula;
    private int clasificacionPelicula;
    private String linkImagen;

    public PeliculaEntidad(int idPelicula, String tituloPelicula, float duracion, String sinopsis, String trailer, int paisOrigen, int generoPelicula, int clasificacionPelicula) {
        this.idPelicula = idPelicula;
        this.tituloPelicula = tituloPelicula;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.trailer = trailer;
        this.paisOrigen = paisOrigen;
        this.generoPelicula = generoPelicula;
        this.clasificacionPelicula = clasificacionPelicula;
    }

    public PeliculaEntidad() {
    }

    public PeliculaEntidad(int idPelicula, String tituloPelicula, float duracion, String sinopsis, String trailer, int paisOrigen, int generoPelicula, int clasificacionPelicula, String linkImagen) {
        this.idPelicula = idPelicula;
        this.tituloPelicula = tituloPelicula;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.trailer = trailer;
        this.paisOrigen = paisOrigen;
        this.generoPelicula = generoPelicula;
        this.clasificacionPelicula = clasificacionPelicula;
        this.linkImagen = linkImagen;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }
    
    

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(int paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(int generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public int getClasificacionPelicula() {
        return clasificacionPelicula;
    }

    public void setClasificacionPelicula(int clasificacionPelicula) {
        this.clasificacionPelicula = clasificacionPelicula;
    }
    
    
}
