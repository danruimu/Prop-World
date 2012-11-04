import java.util.ArrayList;
import java.util.List;

/**
 * @author Grupo 3: {Khalid Mahyou, Alex Morón, Cristian Pallarés, Marcos Del Amo}
 * @version 1.0
 */
public class Toponimo implements Comparable<Toponimo> {

    /**
     * Crea un toponimo vacio
     */
    public Toponimo() {
        nombresAlternativos = new ArrayList<String>();
    }

    /**
     * Crear un toponimo con la informacion que se le pasa
     * @param nombre Nombre del toponimo
     * @param nombresAlternativos Un listado de nombres alternativos para el toponimo
     * @param tipoZona Tipo de zona que le corresponde al toponimo
     * @param coordenada La coordenada del toponimo
     */
    public Toponimo(String nombre, List<String> nombresAlternativos, String tipoZona, Coordenada coordenada) {
        this.nombre = nombre;
        this.nombresAlternativos = nombresAlternativos;
        this.tipoZona = tipoZona;
        this.coordenada = coordenada;
    }

    /**
     * Crear un toponimo con la informacion que se le pasa
     * @param nombre Nombre del toponimo
     * @param tipoZona Tipo de zona que le corresponde al toponimo
     * @param coordenada La coordenada del toponimo
     */
    public Toponimo(String nombre, String tipoZona, Coordenada coordenada) {
        this.nombre = nombre;
        this.tipoZona = tipoZona;
        this.coordenada = coordenada;
    }
    protected String nombre;
    protected List<String> nombresAlternativos;
    protected String tipoZona;
    protected Coordenada coordenada;

    /**
     * Get the value of coordenada
     *
     * @return the value of coordenada
     */
    public Coordenada getCoordenada() {
        return coordenada;
    }

    /**
     * Set the value of coordenada
     *
     * @param coordenada new value of coordenada
     */
    public boolean setCoordenada(Coordenada coordenada) {
        if (coordenada == null) {
            return false;
        } else {
            this.coordenada = coordenada;
        }
        return true;
    }

    /**
     * Get the value of tipoZona
     *
     * @return the value of tipoZona
     */
    public String getTipoZona() {
        return tipoZona;
    }

    /**
     * Set the value of tipoZona
     *
     * @param tipoZona new value of tipoZona
     */
    public boolean setTipoZona(String tipoZona) {    
        if (tipoZona == null) {
            return false;
        } else {
            this.tipoZona = tipoZona;
        }
        return true;
    }

    /**
     * Get the value of nombresAlternativos
     *
     * @return the value of nombresAlternativos
     */
    public List<String> getNombresAlternativos() {
        return nombresAlternativos;
    }

    /**
     * Set the value of nombresAlternativos
     *
     * @param nombresAlternativos new value of nombresAlternativos
     */
    public boolean setNombresAlternativos(List<String> nombresAlternativos) {
        if (nombresAlternativos == null) {
            return false;
        } else {
            this.nombresAlternativos = nombresAlternativos;
        }
        return true;
    }

    /**
     * Get the value of nombreAscii
     *
     * @return the value of nombreAscii
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombreAscii
     *
     * @param nombreAscii new value of nombreAscii
     */
    public boolean setNombre(String nombre) {
        if (nombre == null) {
            return false;
        }
        else {
            this.nombre = nombre;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre() + " " + getTipoZona() + " " + getCoordenada().getLatitud() + " " +  getCoordenada().getLongitud();
    }

    @Override
    public int compareTo(Toponimo o) {
        return getTipoZona().compareTo(o.getTipoZona());
    }
}