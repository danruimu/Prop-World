
import java.util.ArrayList;

/**
 * Esta clases se encarga de crear una estructura donde se registran todos los paises y sus paises adyacentes.
 * @author Oscar Iglesias
 * @version 1
 * @cluster 6
 * @grupo 2
 */
public class ConjuntoPaises {
	/**
	 * Es la estructura donde se almacenan todos los paises y sus paises adyacentes. El convenio es que la 
	 * posicion 0 de cada fila es el pais 'objeto' y las siguientes posiciones son sus paises adyacentes.
	 */
	private ArrayList <ArrayList<Pais>> lp;
	
	/**
	 * Crea un conjunto de paises vacio.
	 */
	public ConjuntoPaises(){
		lp = new ArrayList<ArrayList<Pais>>();
	}
	
	/**
	 * Crea un conjunto de paises inicializado con auxlp.
	 * @param auxlp	Es una estructura que contiene una lista de paises y sus adyacentes.
	 */
	public ConjuntoPaises(ArrayList<ArrayList<Pais>> auxlp){
		lp = new ArrayList<ArrayList<Pais>>();
		ArrayList<Pais> aux;
		
		for(int i=0; i<auxlp.size(); ++i){
			aux = new ArrayList<Pais>();
			for(int j=0; j<auxlp.get(i).size(); ++j){
				aux.add(auxlp.get(i).get(j));
			}
			lp.add(i, aux);
		}
	}
	
	/**
	 * Devuelve el objeto 'Pais' con nombre igual al parametro.
	 * @param nombre Es el nombre del pais que queremos recibir.
	 * @return Se retorna el objeto pais si existe en la lista de paises o null si no existe.
	 */
	public Pais getPais(String nombre){
		for(int i=0; i<lp.size(); ++i){
			if(lp.get(i).get(0).getNombre().equals(nombre)) return lp.get(i).get(0);
		}
		
		return null;
	}
	
	/**
	 * Añade el pais a la lista de paises. Junto con el pais que se añade se buscan sus paises 
	 * adyacentes y se guardan junto con el pais. Tambien se añade el pais en las adyacencias 
	 * de sus paises colindantes. 
	 * @param p Es el pais que queremos añadir a la lista de paises.
	 */
	public void addPais(Pais p){
		boolean existe = false;
		
		for(int i=0; i<lp.size() && !existe; ++i){
			if(lp.get(i).get(0).getNombre().equals(p.getNombre()))	existe = true;
		}
		
		if(!existe){

			ArrayList<Pais> aux = new ArrayList<Pais>();
			aux.add(p);
		
			/*
			 * El siguiente codigo esta comentado debido a que ya no hará falta
			 * generar los paises adyacentes debido a cambios en las funcionalidades
			 * extras del programa
			 * 
			ArrayList<Pais> ad = p.getPaisesAdyacentes();	// Recuperamos los paises adyacentes a p
			for(int i=0; i<ad.size(); ++i){
				aux.add(ad.get(i));	// Añadimos el pais adyacente a las adyacencias de p
				// Añadimos al pais adyacente en su lista de paises adyacentes el pais p
				boolean encontrado = false;
				for(int j=0; j<lp.size() && !encontrado; ++j){
					if(lp.get(j).get(0).getNombre().equals(ad.get(i))){
						lp.get(j).add(p);	// Añadimo p a la lista de adyacencia del pais adyacente
						encontrado=true;
					}
				}
			}*/
		
			lp.add(aux);
		}
	}
	
	/**
	 * Devuelve la relación de paises que existen en la lista de paises.
	 * @return Se devuelve una lista que contiene los paises que existen en la lista de paises.
	 */
	public ArrayList<Pais> getPaises(){
		ArrayList<Pais> aux  = new ArrayList<Pais>();
		
		for(int i=0; i<lp.size(); ++i){
			aux.add(lp.get(i).get(0));
		}
		
		return aux;
	}
	
	/**
	 * Dado un Pais el metodo retorna una lista de sus paises adyacentes.
	 * @param p Es el pais del cual se quiere conocer sus paises adyacentes.
	 * @return Se retorna una lista con los paises adyacentes al pais dado.
	 */
	public ArrayList<Pais> getPaisesAd(Pais p){
		ArrayList<Pais> aux = new ArrayList<Pais>();
		
		for(int i=0; i<lp.size(); ++i){
			if(lp.get(i).get(0).getNombre().equals(p.getNombre())){
				for(int j=1; j<lp.get(i).size(); ++j){
					aux.add(lp.get(i).get(j));
				}
				return aux;
			}
		}
		
		return aux;
	}
}
