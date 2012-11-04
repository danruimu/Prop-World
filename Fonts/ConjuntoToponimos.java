import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Grupo 3: {Khalid Mahyou, Alex Morón, Cristian Pallarés, Marcos Del Amo}
 * @version 1.0
 */
public class ConjuntoToponimos {

    /**
     * Construye un conjunto de toponimos vacio
     * que se ordenadan segun el comparador que se le pasa
     * @param comparador Clase de comparacion segun la cual se ordenan los toponimos
     */
    public ConjuntoToponimos() {
        conjuntoToponimos = new HashSet<Toponimo>();
        numMaxPalabras = 0;
    }

    /**
     * Construye un conjunto de toponimos a partir del listado proporcionado
     * que se ordenadan segun el comparador que se le pasa
     * @param listadoToponimos listado de toponimos a insertar en el conjunto de toponimos
     * @param comparador Clase de comparacion segun la cual se ordenan los toponimos
     */
    public ConjuntoToponimos(List<Toponimo> listadoToponimos) {
        this();

        conjuntoToponimos.addAll(listadoToponimos);
    }
    private HashSet<Toponimo> conjuntoToponimos;
    private int numMaxPalabras;

    /**
     * Get the value of numMaxPalabras
     *
     * @return the value of numMaxPalabras
     */
    public int getNumMaxPalabras() {
        return numMaxPalabras;
    }

    /**
     * Set the value of numMaxPalabras
     *
     * @param numMaxPalabras new value of numMaxPalabras
     */
    public void setNumMaxPalabras(int numMaxPalabras) {
        this.numMaxPalabras = numMaxPalabras;
    }

    /**
     * Get the value of conjuntoToponimos
     *
     * @return the value of conjuntoToponimos
     */
    public HashSet<Toponimo> getConjuntoToponimos() {
        return conjuntoToponimos;
    }

    /**
     * Set the value of conjuntoToponimos
     *
     * @param conjuntoToponimos new value of conjuntoToponimos
     */
    public void setConjuntoToponimos(HashSet<Toponimo> conjuntoToponimos) {
        this.conjuntoToponimos = conjuntoToponimos;
    }

    /**
     * Inserta un toponimo en el conjunto
     * @param toponimo El toponimo que se quiera insertar en el conjunto
     * @return True si el conjunto no contiene el elemento a insertar. False en caso contrario
     */
    public boolean addToponimo(Toponimo toponimo) {
        int maxTopo = calcularNumMaxPalabras(toponimo);
        if (maxTopo > numMaxPalabras) {
            numMaxPalabras = maxTopo;
        }

        return conjuntoToponimos.add(toponimo);
    }

    private int calcularNumMaxPalabras(Toponimo toponimo) {
        int max = new StringTokenizer(toponimo.getNombre(), " ").countTokens();

        if (toponimo.getNombresAlternativos() != null) {
            for (String str : toponimo.getNombresAlternativos()) {
                StringTokenizer st = new StringTokenizer(str, " ");
                
                int le = st.countTokens();
                if (le > max) max = le;
            }
        }

        return max;
    }

    /**
     * Inserta un listado de toponims en el conjunto
     * @param toponimos El listado de toponimos que se quiera insertar en el conjunto
     * @return True si el listado se ha insertado correctamente. False en caso contrario
     */
    public boolean addToponimo(List<Toponimo> toponimos) {
        return conjuntoToponimos.addAll(toponimos);
    }

    /**
     * Devuelve true si el conjunto contiene el toponimo especificado
     * @param toponimo El toponimo que se quiera comprobar si se encuentra en este conjunto
     * @return True si el toponimo se encuentra en este conjunto. False en caso contrario 
     */
    public boolean contains(Toponimo toponimo) {
        return conjuntoToponimos.contains(toponimo);
    }
    
    /**
     * Busca el topónimo con el nombre especificado
     * (el nombre puede ser el principal o cualquier nombre alternativo)
     * y lo devuelve si exsiste. En caso contrario devuelve null.
     * @param nombre El nombre del toponimo a buscar
     * @return El toponimo del nombre especificado o null en qualquier otro caso
     */
    public Toponimo contieneToponimo(String nombre) {
        Iterator<Toponimo> iterator = conjuntoToponimos.iterator();
        
        while (iterator.hasNext()) {
            Toponimo topo = iterator.next();
            if (nombre.equals(topo.getNombre())) return topo;
            else {
                if (topo.getNombresAlternativos() != null) {
                    for (String str : topo.getNombresAlternativos()) {
                        if (nombre.equals(str)) return topo;
                    }
                }
            }
        }
        
        return null;
    }

    /**
     * Devuel el numero de elementos que tien este conjunto
     * @return El numero de elementos que tiene esta conjunto
     */
    public int getSize() {
        return conjuntoToponimos.size();
    }

    @Override
    public String toString() {
        Iterator<Toponimo> i = conjuntoToponimos.iterator();
        String s = "";
        while (i.hasNext()) {
            Toponimo t = i.next();
            s += t.getNombre() + " " + t.getTipoZona() + " " + t.getCoordenada().getLatitud() + " " + t.getCoordenada().getLongitud() + "\n";
        }
        return s;
    }
}