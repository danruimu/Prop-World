import java.util.List;

/**
 * @author Grupo 3: {Khalid Mahyou, Alex Morón, Cristian Pallarés, Marcos Del Amo}
 * @version 1.0
 */
public class Ciudad extends Toponimo {

    /**
     * Crea una ciudad vacia
     */
    public Ciudad() {
        super();
    }

    /**
     * Crear una ciudad con la informacion que se le pasa
     * @param nombre Nombre de la ciudad
     * @param nombresAlternativos Un listado de nombres alternativos para la ciudad
     * @param tipoZona Tipo de zona que le corresponde a la ciudad
     * @param coordenada La coordenada de la ciudad
     * @param poblacion la poblacion de la ciudad
     */
    public Ciudad(String nombre, List<String> nombresAlternativos, String tipoZona, Coordenada coordenada, int poblacion) {
        super(nombre, nombresAlternativos, tipoZona, coordenada);
        this.poblacion = poblacion;
    }

    /**
     * Crear una ciudad con la informacion que se le pasa
     * @param nombre Nombre de la ciudad
     * @param tipoZona Tipo de zona que le corresponde a la ciudad
     * @param coordenada La coordenada de la ciudad
     * @param poblacion la población de la ciudad
     */
    public Ciudad(String nombre, String tipoZona, Coordenada coordenada, int poblacion) {
        super(nombre, tipoZona, coordenada);
        this.poblacion = poblacion;
    }

    private int poblacion;

    /**
     * Get the value of poblacion
     *
     * @return the value of poblacion
     */
    public int getPoblacion() {
        return poblacion;
    }

    /**
     * Set the value of poblacion
     *
     * @param poblacion new value of poblacion
     */
    public boolean setPoblacion(int poblacion) {
        this.poblacion = poblacion;
        return true;
    }
}