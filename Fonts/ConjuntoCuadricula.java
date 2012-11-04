import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;
//import tools.Timer;

/**
 *
 * @author Cristian
 */
public class ConjuntoCuadricula {

    private ArrayList<ArrayList<Cuadricula>> conjuntoCuadricula;
    private int nCuadriculasLatitud, nCuadriculasLongitud;
    private double tamanoCuadricula;

    public ConjuntoCuadricula() {
        // Establecer la definicion de cuadricula
        this.nCuadriculasLatitud = 500;
        this.nCuadriculasLongitud = this.nCuadriculasLatitud * 2;
        this.tamanoCuadricula = 360.0 / (double) this.nCuadriculasLongitud;

        // Crear las cuadriculas
//        Timer timer = new Timer();
        this.conjuntoCuadricula = new ArrayList<ArrayList<Cuadricula>>();
        for (int i = 0; i < this.nCuadriculasLongitud; ++i) {
            ArrayList<Cuadricula> array = new ArrayList<Cuadricula>();
            for (int j = 0; j < this.nCuadriculasLatitud; ++j) {
                Cuadricula cuadricula = new Cuadricula();
                cuadricula.setEsquina(0, new Coordenada(i * this.tamanoCuadricula - 180, j * this.tamanoCuadricula - 90));
                cuadricula.setEsquina(1, new Coordenada((i + 1) * this.tamanoCuadricula - 180, j * this.tamanoCuadricula - 90));
                cuadricula.setEsquina(2, new Coordenada((i + 1) * this.tamanoCuadricula - 180, (j + 1) * this.tamanoCuadricula - 90));
                cuadricula.setEsquina(3, new Coordenada(i * this.tamanoCuadricula - 180, (j + 1) * this.tamanoCuadricula - 90));
                array.add(cuadricula);
            }
            this.conjuntoCuadricula.add(array);
        }
//        timer.print("Crear cuadriculas");
    }

    /**
     * Devuelve la cuadricula i, j (en posicion local)
     * @param i
     * @param j
     * @return      Cuadricula i, j.
     */
    public Cuadricula getCuadricula(int i, int j) {
        return (i >= 0 && j >= 0)
                ? this.conjuntoCuadricula.get(i%this.nCuadriculasLongitud)
                .get(j%this.nCuadriculasLatitud) : null;
    }

    /**
     * Devuelve la cuadricula que contiene c (en posicion global)
     * @param c     Coordenada
     * @return      Cuadricula que contiene la coordenada c
     */
    public Cuadricula getCuadricula(Coordenada c) {
        int i = (int) ((c.getLongitud() + 180.0) / this.tamanoCuadricula);
        int j = (int) ((c.getLatitud() + 90.0) / this.tamanoCuadricula);
        return this.getCuadricula(i, j);
    }

    /**
     * 
     * @param c
     * @return 
     */
    public double getTamanoCuadricula() {
        return tamanoCuadricula;
    }

    public ArrayList<Contorno> calculaPosiblesContornos(Coordenada c) {
        int x = (int) Math.floor(
                ((c.getLongitud() + 180) % 180) / this.tamanoCuadricula);
        int y = (int) Math.floor(
                ((c.getLatitud() + 90) % 90) / this.tamanoCuadricula);
        return this.conjuntoCuadricula.get(x).get(y).getContornos();
    }

    /**
     *
     * @param contorno
     */
    public void addContorno(Contorno contorno) {
        // Conseguimos los punto a, b (bounding box del contorno)
        Coordenada a = null, b = null;
        {
            Iterator<Coordenada> it = contorno.getCoordenadas().iterator();
            while (it.hasNext()) {
                Coordenada c = it.next();
                if (a == null || b == null) {
                    a = c.clone();
                    b = c.clone();
                } else {
                    if (a.getLatitud() > c.getLatitud()) {
                        a.setLatitud(c.getLatitud());
                    }
                    if (a.getLongitud() > c.getLongitud()) {
                        a.setLongitud(c.getLongitud());
                    }
                    if (b.getLatitud() < c.getLatitud()) {
                        b.setLatitud(c.getLatitud());
                    }
                    if (b.getLongitud() < c.getLongitud()) {
                        b.setLongitud(c.getLongitud());
                    }
                }
            }
            // Normalizamos las coordenadas
//            a.setLatitud(a.getLatitud()+90);
//            a.setLongitud(b.getLongitud()+180);
//            b.setLatitud(a.getLatitud()+90);
//            b.setLongitud(b.getLongitud()+180);
//            System.out.println("Bounding box: " + a + ", " + b);
        }

        // Añadimos las cuadriculas a las que el bounding box pertenece
        int ai = (int) ((a.getLongitud() + 180.0) / this.tamanoCuadricula);
        int aj = (int) ((a.getLatitud() + 90.0) / this.tamanoCuadricula);
        int bi = (int) ((b.getLongitud() + 180.0) / this.tamanoCuadricula);
        int bj = (int) ((b.getLatitud() + 90.0) / this.tamanoCuadricula);
//        System.out.println("Bounding box local: (" + ai + ", " + aj + "), ("
//                + bi + ", " + bj +")");
        for (int i = ai; i <= bi; ++i) {
            for (int j = aj; j <= bj; ++j) {
                //System.out.println("Contorno " + contorno + " añadido en (" +
                //        i + ", " + j + ")");
                contorno.addCuadricula(this.getCuadricula(i, j));
                this.getCuadricula(i, j).addContorno(contorno);
            }
        }
    }

    public void addToponimo(Toponimo toponimo) {
        this.getCuadricula(toponimo.getCoordenada()).addToponimo(toponimo);
    }
}