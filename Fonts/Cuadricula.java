import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class Cuadricula {

    private Coordenada supi;
    private Coordenada supd;
    private Coordenada infi;
    private Coordenada infd;
    private int i;
    private ArrayList<Toponimo> toponimos;
    private ArrayList<Contorno> contornos;

    public Cuadricula() {
        supi = new Coordenada();
        supd = new Coordenada();
        infi = new Coordenada();
        infd = new Coordenada();
        toponimos = new ArrayList<Toponimo>();
        contornos = new ArrayList<Contorno>();
    }

    /**
     * 
     * @param supi Coordenada superior izquierda
     * @param supd Coordenada superior derecha
     * @param infi Coordenada inferior izquierda
     * @param infd Coordenada inferior derecha
     */
    public Cuadricula(Coordenada supi, Coordenada supd, Coordenada infi, Coordenada infd) {
        this.supi = supi;
        this.supd = supd;
        this.infi = infi;
        this.infd = infd;
        this.toponimos = new ArrayList<Toponimo>();
        this.contornos = new ArrayList<Contorno>();
    }

    /**
     * Añade el toponimo a la lista de toponimos
     * @param t El parametro que quieres añadir a la lista
     * @return Devuelve cierto si añade correctamente el toponimo a la lista
     */
    public boolean addToponimo(Toponimo t) {
        return toponimos.add(t);
    }

    /**
     * Añade un contorno a los contornos que contienie la cuadricula
     * @param c El parametro que quieres añadir a la lista
     * @return Devuelve cierto si añade correctamente el contorno a la lista
     */
    public boolean addContorno(Contorno c) {
        return contornos.add(c);
    }

    /**
     * 
     * @return Devuelve el arraylist de toponimos
     */
    public ArrayList<Toponimo> getToponimos() {
        return toponimos;
    }

    /**
     * 
     * @return Devuelve el arraylist de contornos
     */
    public ArrayList<Contorno> getContornos() {
        return contornos;
    }

    /**
     * 
     * @param x Indica la posicion de la coordenada que le vas a pasar
     * @param c Coordenada
     * @return Devuelve cierto si se produce la insercion y falso en caso contrario
     */
    public boolean setEsquina(int x, Coordenada c) {
        switch (x) {
            case 0:
                supi = c;
                return true;
            case 1:
                supd = c;
                return true;
            case 2:
                infd = c;
                return true;
            case 3:
                infi = c;
                return true;
        }
        return false;
    }

    /**
     * 
     * @param a id de la coordenada
     * @param correcto Booleano que indica si la operacion se ha realizado correctamente
     */
    public boolean setId(int a) {
        i = a;
        if (i >= 0) return true;
        else return false;
    }

    /**
     * Devuelve la id de la coordenada
     * @return integer identificador
     */
    public int getId() {
        return i;
    }

    /**
     * Devuelve una esquina dependiendo del valor x y -1-1 en caso contrario
     * @param x indica la esquina que quieres
     * @return Devuelve una coordenada
     */
    public Coordenada getEsquina(int x) {
        switch (x) {
            case 0:
                return supi;
            case 1:
                return supd;
            case 2:
                return infd;
            case 3:
                return infi;
        }
        Coordenada n = new Coordenada();
        n.setLatitud(-1);
        n.setLongitud(-1);
        return n;
    }

    /**
     * Devuelve una lista de topónimos de la cuadricula con tipoZona igual tipoZona.
     * @param tipoZona
     * @return Devuelve una lista con los toponimos que tienen tipoZona
     */
    public List<Toponimo> calculaToponimos(String tipoZona) {
        List<Toponimo> r = new ArrayList<Toponimo>();
        for (Toponimo t : toponimos) {
            if (t.getTipoZona().equals(tipoZona)) {
                r.add(t);
            }
        }
        return r;
    }

    /**
     * 
     * @return Devuelve la esquina superior inferior
     */
    public Coordenada getSupi() {
        return supi;
    }

    /**
     * 
     * @return Devuelve la esquina superior derecha
     */
    public Coordenada getSupd() {
        return supd;
    }

    /**
     * 
     * @return Devuelve la esquina inferior izquierda
     */
    public Coordenada getinfi() {
        return infi;
    }

    /**
     * 
     * @return Devuelve la esquina inferior derecha
     */
    public Coordenada getinfd() {
        return infd;
    }
}