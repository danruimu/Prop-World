import java.util.ArrayList;

/**
 * @author Maria Josep Rodriguez Franco
 * @cluster 6
 * @grupo 1
 */
public class PreMapa {
	private ArrayList<Coordenada> conjuntoCoordenadas;
	private int tipoPoligono; // el tipo de poligono a pintar ( 0=pais, 1=rio, 2=toponimo)
	private String color; //el color del poligono
	private String nombre; // nombre del poligono
	private Boolean escribirNombre; // flag para indicar si el nombre se muestra o no
	
	/**
	 * Constructora de la clase PreMapa, se indican los valores de sus atributos.
	 * @param v vector de coordenadas del premapa.
	 * @param tipoP tipo de poligono del premapa, un 0 representa un pais, un 1 representa un rio y un 2 representa un toponimo.
	 * @param col a escoger entre los predefinidos en java.
	 * @param nom nombre del premapa.
	 * @param escribirNo un 0 representara que no se ha de escribir su nombre en el mapa un 1 representa lo contrario.
	 */
	public PreMapa(ArrayList<Coordenada> v, int tipoP, String col, String nom, Boolean escribirNo) {
		conjuntoCoordenadas = v;
		tipoPoligono = tipoP;
		color = col;
		nombre = nom;
		escribirNombre = escribirNo;
	}
	
	/**
	 * Constructor generico de la clase PreMapa.
	 */
	public PreMapa() {}

	/**
	 * Devuelve un vector de coordenadas, que forman parte del mapa
	 * @return vector de coordenadas de un mapa
	 */
	public ArrayList<Coordenada> getConjuntoCoordenadas() {
		return conjuntoCoordenadas;
	}

	/**
	 * Devuelve el tipo de poligono de un objeto de la clase PreMapa.
	 * @return tipo poligono de un objeto de la clase PreMapa.
	 */
	public int getTipoPoligono() {
		return tipoPoligono;
	}

	/**
	 * Devuelve el color del poligono.
	 * @return el color del poligono.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Devuelve el nombre del poligono.
	 * @return el nombre del poligono.
	 */
	public String getNombre() {
		return nombre;
	}	
		
	/**
	 * Devuelve cierto o falso dependiendo si el nombre del poligono se ha de mostrar o no.
	 * @return cierto o falso dependiendo si se ha de mostrar el nombre del poligono o no.
	 */
	public Boolean getEscribirNombre() {
		return escribirNombre;
	}

	/**
	 * Asigna un conjunto de coordenadas al premapa.
	 * @param conjunto conjunto de coordenadas.
	 * @return cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setConjuntoCoordenadas(ArrayList<Coordenada> conjunto) {
		Boolean boo = true;
		try {
			conjuntoCoordenadas = conjunto;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}

	/**
	 * Asigna un tipo de poligono  al premapa.
	 * @param tipo determina el tipo de poligono.
	 * @return cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setTipoPoligono(int tipo) {
		Boolean boo = true;
		try {
			if (tipo < 0 || tipo > 2) boo = false;
			else tipoPoligono = tipo;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}

	/**
	 * Asigna un color al poligono del premapa.
	 * @param col determina el color del poligono.
	 * @return cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setColor(String col) {
		Boolean boo = true;
		try {
			if (col.equals("black") || col.equals("blue") || col.equals("darkGray")  
				||	col.equals("cyan") || col.equals("gray") || col.equals("green")
				|| col.equals("lightGray") || col.equals("magenta") || col.equals("orange") 
				|| col.equals("pink") || col.equals("red") || col.equals("white") || col.equals("yellow")) color = col;
			else boo = false;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}

	/**
	 * Asigna un nombre al poligono del premapa.
	 * @param nom determina el nombre del poligono.
	 * @return cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setNombre(String nom) {
		Boolean boo = true;
		try {
			nombre = nom;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}
		
	/**
	 * Asigna un valor de 1 o 0 al booleano que indica si se ha de escribir o no el nombre del poligono.
	 * @param escribir es un 1 si se ha de mostrar el nombre del poligono, 0 en caso contrario.
	 * @return cierto o falso dependiendo de si se ha hecho la asignacion correctamente o no.
	 */
	public Boolean setEscribirNombre(Boolean escribir) {
		Boolean boo = true;
		try {
			escribirNombre = escribir;
		}
		catch (Exception e) {
			boo = false;
		}
		return boo;
	}
	
	public PreMapa clone() {
		PreMapa ret = new PreMapa();
		ret.tipoPoligono = tipoPoligono;
		ret.color = color;
		ret.nombre = nombre;
		ret.escribirNombre = escribirNombre;
		ArrayList<Coordenada> aux = new ArrayList<Coordenada>();
		for (Coordenada c: conjuntoCoordenadas) aux.add(c.clone());
		ret.setConjuntoCoordenadas(aux);
		return ret;
	}
}