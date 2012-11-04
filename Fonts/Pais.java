import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author xxxx
 */
public class Pais {

    private ArrayList<Contorno> conjuntoContornos;
    private String nombre;
    private double superficie;
    private int poblacion;
    private ArrayList<Ciudad> ciudades;

    /**
     * Crea un Pais vacio
     */
    public Pais() {
        this.conjuntoContornos = new ArrayList<Contorno>();
        this.nombre = new String();
        this.superficie = 0;
        this.poblacion = 0;
        this.ciudades = new ArrayList<Ciudad>();
    }

    /**
     * Crea un pais con la inforamcion que se le pasa
     * @param nombre El nombre del pais
     */
    public Pais(String nombre) {
        this.conjuntoContornos = new ArrayList<Contorno>();
        this.nombre = nombre;
        this.superficie = 0;
        this.poblacion = 0;
        this.ciudades = new ArrayList<Ciudad>();
    }

    /**
     * Crea un pais con la inforamcion que se le pasa
     * @param conjuntoContornos el conjunto de contornos que forma el pais
     * @param nombre el nombre del pais
     */
    public Pais(ArrayList<Contorno> conjuntoContornos, String nombre) {
        this.conjuntoContornos = conjuntoContornos;
        this.nombre = nombre;
        this.superficie = 0;
        this.poblacion = 0;
        this.ciudades = new ArrayList<Ciudad>();
    }

    /**
     * Añade un contorno al pais
     * @param c El contorno que se quiere añadir
     */
    public boolean addContorno(Contorno c) {
        this.conjuntoContornos.add(c);
        return true;
    }

    /**
     * Devuelve el Contorno i del conjunto  
     * @param i El contorno que quieres
     * @return Devulve el contorno i del conjunto
     */
    public Contorno getContorno(int i) {
        return conjuntoContornos.get(i);
    }

    /**
     * Devuelve la cantidad de contornos que forman el pais
     * @return 
     */
    public int getNContornos() {
        return conjuntoContornos.size();
    }

    /**
     * Añade la ciudad al pais
     * @param c la ciudad que se quiere añadir
     */
    public void addCiudad(Ciudad c) {
        this.ciudades.add(c);
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    /**
     * Deuvelve la ciudad i del conjunto de ciudades que tiene el pais
     * @param i el numero de la ciudad que se quiere conseguir
     * @return 
     */
    public Ciudad getCiudad(int i) {
        return this.ciudades.get(i);
    }

    /**
     * Devuelve el numero de ciudades que tiene el pais
     * @return 
     */
    public int getNCiudad() {
        return this.ciudades.size();
    }

    /**
     * devuelve el nombre del pais
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * devuelve las ciudades con poblacion = 0
     * @return 
     */
    public int getPoblacion() {
        return poblacion;
    }


    /**
     * Obtiene la superficie del pais
     * @return 
     */
    public double getSuperficie() {
        return superficie;
    }

    /**
     * Retorna una lista de paises adyacentes al pais implicito.
     * @return Se retorna la lista lp que contiene los paises adyacentes al pais implicito.
     */
    public ArrayList<Pais> getPaisesAdyacentes() {
        ArrayList<Pais> lp = new ArrayList<Pais>();

        for (int ic = 0; ic < conjuntoContornos.size(); ++ic) {
            ArrayList<Contorno> lc = conjuntoContornos.get(ic).getContornosAdyacentes();
            for (int icc = 0; icc < lc.size(); ++icc) {
                Pais p = lc.get(icc).getPais();
                if (!lp.contains(p)) {
                    lp.add(p);
                }
            }
        }

        return lp;
    }

    /**
     * Nombra al pais como el parametro
     * @param nombre Nombre que se le quiere poner al pais
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Nueva poblacion que tendra el pais
     * @param poblacion Poblacion que se le quiere dar al pais
     */
    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Nueva superficie del pais
     * @param superficie la nueva superficie que quieres que tenga el pais 
     */
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

}