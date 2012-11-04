import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Esta clase representa cada una de las zonas de un Pais
 * @author Daniel Ruiz Muñoz
 * @version 2.1
 * @Cluster 6
 * @Grupo 2
 */
public class Contorno {
//Atributos
		
		/**
		 * Identifica el Contorno
		 */
		private int id;
		
		/**
		 * Lista de Coordenadas del Contorno
		 */
		private ArrayList<Coordenada> lc;
		
		/**
		 * Lista con todos los contornos adyacentes del Contorno
		 */
		private ArrayList<Contorno> contornosadyacentes;
		
		/**
		 * Conjunto de Toponimos que pertenecen al Contorno
		 */
		private ArrayList<Toponimo> cjttoponimos;
		
		/**
		 * Pais al que pertenece al Contorno
		 */
		private Pais p;
		
		/**
		 * Lista de las Cuadriculas a las cuales pertenece el Contorno
		 */
		private ArrayList<Cuadricula> cjtCuadricula;
		
		/**
		 * Lista de las coordenadas de la frontera ordenadas por longitud (x) de menor a mayor
		 * y despues por latitud (y) de mayor a menor
		 */
		private ArrayList<Coordenada> fronteraOrdenada;
		
		/**
		 * Booleano que indica si la lista de fronteraOrdenada esta inicializada
		 */
		private boolean frontOrd;
		
		/**
		 * Booleano que indica si la lista de contornos adyacentes esta inicializada
		 */
		private boolean contAdy;

		/**
		 * Comparador que ordena las coordenadas por longitud de menor a mayor y en caso
		 * de igual por latitud de mayor a menor
		 * @author Daniel Ruiz Muñoz
		 * @version 1.0
		 */
		class ComparadorCoordenadasLongitud implements Comparator<Coordenada>{
			   
		    public int compare(Coordenada c1, Coordenada c2){
		       
		        double c1x = c1.getLongitud();
		        double c2x = c2.getLongitud();
		        double c1y = c1.getLatitud();
		        double c2y = c2.getLatitud();
		       
		        if(c1x < c2x || (c1x == c2x && c1y > c2y)) {
		            return -1;
		        } else {
		            return 1;
		    	}
		    }
		   
		}
		
		/**
		 * Comparador que ordena Coordenadas por latitud de menor a mayor y en caso
		 * de igualdad, por longitud de mayor a menor
		 * @author Daniel Ruiz Muñoz
		 * @version 1.0
		 */
		class ComparadorCoordenadasLatitud implements Comparator<Coordenada>{
			   
		    public int compare(Coordenada c1, Coordenada c2){
		       
		        double c1x = c1.getLongitud();
		        double c2x = c2.getLongitud();
		        double c1y = c1.getLatitud();
		        double c2y = c2.getLatitud();
		       
		        if(c1y < c2y || (c1y == c2y && c1x > c2x)) {
		            return -1;
		        } else {
		            return 1;
		    	}
		    }
		   
		}
//Operaciones
		
		/**
		 * Crea un Contorno vacío
		 */
		public Contorno() {
			id = -1;
			lc = new ArrayList<Coordenada>();
			contornosadyacentes = new ArrayList<Contorno>();
			contAdy = false;
			cjttoponimos = new ArrayList<Toponimo>();
			p = new Pais();
			cjtCuadricula = new ArrayList<Cuadricula>();
			fronteraOrdenada = new ArrayList<Coordenada>();
			frontOrd = false;
		}
		
		/**
		 * Crea un Contorno con conjunto de coordenadas igual a lc, id igual a id, cjttoponimos
		 * igual a lt y pais igual a p.
		 * @param lc Lista de Coordenadas de inicializacion del Contorno
		 * @param id Identificador de inicializacion del Contorno
		 * @param lt Lista de Toponimos de inicializacion del Contorno
		 * @param p Pais de inicializacion al que pertenecera el Contorno
		 */
		public Contorno(ArrayList<Coordenada> lc, int id, ArrayList<Toponimo> lt, Pais p) {
			cjttoponimos = new ArrayList<Toponimo>(lt);
			this.id = id;
			this.lc = new ArrayList<Coordenada>();
			this.lc.addAll(lc);
			this.p = p;
			this.cjtCuadricula = new ArrayList<Cuadricula>();
			this.contornosadyacentes = new ArrayList<Contorno>();
			contAdy = false;
			fronteraOrdenada = new ArrayList<Coordenada>();
			frontOrd = false;
		}

		/**
		 * Devuelve el id del contorno
		 * @return Devuelve el identificador del Contorno
		 */
		public int getId() {
			return this.id;
		}

		/**
		 * Devuelve las coordenadas del contorno
		 * @return Devuelve una lista con todas las coordenadas
		 */
		public ArrayList<Coordenada> getCoordenadas() {
			return this.lc;
		}

		/**
		 * Devuelve los contornos adyacentes al contorno
		 * @return Devuelve una lista con todos los contornos adyacentes
		 * al Contorno
		 */	
		public ArrayList<Contorno> getContornosAdyacentes() {
			if(!contAdy) {
				contAdy = true;
				for(int i=0; i<cjtCuadricula.size(); ++i) {
					ArrayList<Contorno> aux = new ArrayList<Contorno>(cjtCuadricula.get(i).getContornos());
					aux.remove(this);
					for(Contorno y : contornosadyacentes) {
						if(aux.contains(y)) {
							aux.remove(y);
						}
					}
					for(int j=0; j<aux.size(); ++j) {
						ArrayList<Coordenada> coordPaisesAdyacentes = new ArrayList<Coordenada>(aux.get(j).getCoordenadas());
						Quadtree q = new Quadtree(coordPaisesAdyacentes, 500);
						for(int l=0; l<lc.size(); ++l) {
							if(q.contiene(lc.get(l))) {
								contornosadyacentes.add(aux.get(j));
								l = lc.size();
							}
						}
					}
				}
				
			}
			return this.contornosadyacentes;
		}

		/**
		 * Descripción: Devuelve el ConjuntoToponimos perteneciente al Contorno
		 * @return Devuelve una lista con todos los Toponimos que pertenecen al Contorno
		 */
		public ArrayList<Toponimo> getCjtToponimos() {
			return this.cjttoponimos;
		}

		/**
		 * Dada una coordenada p la función devolverá cierto si el punto se encuentra
		 * dentro del contorno y falso en caso contrario.
		 * Se supone que la frontera del Contorno es de minimo 3 coordenadas.
		 * @param p Coordenada a comprobar
		 * @return Devuelve cierto si la Coordenada p se encuentra dentro del Contorno
		 * y falso en caso contrario.
		 */
		public boolean puntoDentroContorno(Coordenada p){
			if(!frontOrd) {
				frontOrd = true;
				this.fronteraOrdenada = new ArrayList<Coordenada>(this.lc);
				Collections.sort(fronteraOrdenada, new ComparadorCoordenadasLongitud());
			}
			
			if(p.getLongitud()<fronteraOrdenada.get(0).getLongitud() || p.getLongitud()>fronteraOrdenada.get(fronteraOrdenada.size()-1).getLongitud()) {
				return false;
				/*Esto podemos suponerlo ya que la lista esta ordenada por longitudes de 
				 * menor a mayor, entonces, si la longitud de p es menor o mayor que la 
				 * lontigud minima o maxima del contorno, directamente el punto esta 
				 * fuera sin comprobar nada mas
				 */
			}
			ArrayList<Coordenada> frontOrdLati = new ArrayList<Coordenada>(lc);
			Collections.sort(frontOrdLati, new ComparadorCoordenadasLatitud());
			if(p.getLatitud()<frontOrdLati.get(0).getLatitud() || p.getLatitud()>frontOrdLati.get(frontOrdLati.size()-1).getLatitud()) {
				return false;
				/*Esto podemos suponerlo ya que la lista esta ordenada por longitudes de 
				 * menor a mayor, entonces, si la longitud de p es menor o mayor que la 
				 * lontigud minima o maxima del contorno, directamente el punto esta 
				 * fuera sin comprobar nada mas
				 */
			}
			int cont = 0;
			for(int i=0; i<lc.size()-1; ++i) {
				if(lc.get(i).getLatitud()>=p.getLatitud()) {
					if(lc.get(i).getLongitud()<=p.getLongitud() && lc.get(i+1).getLongitud()>=p.getLongitud()) {
						++cont;
					} else if(lc.get(i).getLongitud()>=p.getLongitud() && lc.get(i+1).getLongitud()<=p.getLongitud()) {
						++cont;
					}
				}
			}
			if(cont%2==0) {
				return false;
			}
			return true;
		}
		
		/**
		 * Devuelve el Pais al que pertenece el Contorno
		 * @return Devuelve el Pais al que pertenece el Contorno
		 */
		public Pais getPais() {
			return p;
		}

		/**
		 * Devuelve las coordenadas cercanas a una coordenada
		 * @param c Coordenada a partir de la cual se buscaran las coordenadas mas cercanas
		 * @param listCoord Lista con las coordenadas de las cuales se quieren obtener las mas
		 * cercanas a la coordenada c
		 * @param km Distancia maxima de la Coordenada c donde se buscaran Coordenadas cercanas
		 * @return Devuelve una lista con las coordenadas de lc a menos de km kilometros
		 * de la coordenada c
		 */
		public ArrayList<Coordenada> getCoordenadasCercanas(Coordenada c, ArrayList<Coordenada> listCoord, double km) {
			ArrayList<Coordenada> listcoor = new ArrayList<Coordenada>();
			Quadtree q = new Quadtree(listCoord,500);
			listcoor.addAll(q.puntosCercanos(c, km));
			return listcoor;
		}
		
		/**
		 * Calcula el contorno al que pertenece la coordenada c de
		 * entre los contornos de la lista lc
		 * @param c
		 * @param lc
		 * @return Devuelve el Contorno al que pertenece la coordenada c de entre
		 * la lista de contornos lc
		 */
		public Contorno calcularContorno(Coordenada c, ArrayList<Contorno> lc) {
			Contorno con = new Contorno();
			for(int i=0; i<lc.size(); ++i) {
				if (lc.get(i).puntoDentroContorno(c)) {
					con = lc.get(i);
					i = lc.size();
				}
			}
			return con;
		}
		
		/**
		 * Set del atributo id del Contorno
		 * @param id Identificador nuevo del Contorno
		 * @return Devuelve cierto si el set ha sido correcto, en caso de error
		 * se devuelve falso
		 */
		public boolean setId(int id) {
			try {
				this.id = id;
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		/**
		 * Set de la lista de Toponimos del Contorno
		 * @param lt Lista con los Toponimos que pasará a tener el Contorno
		 * @return Devuelve cierto si el set es correcto, y false si ha habido
		 * algun error
		 */
		public boolean setCjtToponimos(ArrayList<Toponimo> lt) {
			try {
				this.cjttoponimos = new ArrayList<Toponimo>(lt);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		/**
		 * Set de las Coordenadas del Contorno
		 * @param lc Lista de Coordenadas
		 * @return Devuelve cierto si la Lista con las Coordenadas del Contorno
		 * pasa a ser lc, falso en caso de algún error
		 */
		public boolean setCoordenadas(ArrayList<Coordenada> lc) {
			try {
				this.lc = new ArrayList<Coordenada>(lc);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		/**
		 * Set del Pais de Contorno
		 * @param p Pais
		 * @return Devuelve cierto si el Pais del Contorno pasa a ser p y falso 
		 * en caso de error
		 */
		public boolean setPais(Pais p) {
			try {
				this.p = new Pais();
				this.p = p;
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		/**
		 * Set de la lista de Cuadricula del Contorno
		 * @param cc Lista de Cuadricula
		 * @return Devuelve cierto si la lista de las cuadriculas a las que 
		 * pertenece el Contorno pasa a ser cc, en caso de error devuelve 
		 * falso
		 */
		public boolean setCjtCuadricula(ArrayList<Cuadricula> cc) {
			try {
				this.cjtCuadricula = new ArrayList<Cuadricula>(cc);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		/**
		 * Set de los Contornos Adyacentes del Contorno
		 * @param lc Lista de Contornos adyacentes al Contorno implicito
		 * @return Devuelve cierto si la lista de contornos adyacentes del Contorno
		 * pasa a ser lc, en caso de error devuelve falso
		 */
		public boolean setContornosAdyacentes(ArrayList<Contorno> lc) {
			try {
				this.contornosadyacentes = new ArrayList<Contorno>(lc);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		/**
		 * Añade la cuadricula c a la lista de cuadriculas a las que pertenece el Contorno
		 * @param c Cuadricula
		 * @return Devuelve cierto si se ha añadido correctamente la cuadricula a
		 * la lista de cuadriculas a las que pertenece el Contorno, en caso de error
		 * devuelve falso
		 */
		public boolean addCuadricula(Cuadricula c) {
			try {
				this.cjtCuadricula.add(c);
				calcularToponimos(c);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
		/**
		 * Calcula los Toponimos que son del Contorno de los Toponimos
		 * de la Cuadricula c
		 * @param c
		 */
		private void calcularToponimos(Cuadricula c) {
            for(Toponimo t : c.getToponimos()) {
                if(!cjttoponimos.contains(t) && puntoDentroContorno(t.getCoordenada()))
                    cjttoponimos.add(t);
            }
		}
		
		/**
		 * Funcion que retorna una lista con las Cuadriculas a las que
		 * pertenece el contorno
		 * @return Devuelve un ArrayList con las cuadriculas a las que pertenece
		 * el Contorno
		 */
		public ArrayList<Cuadricula> getConjuntoCadricula() {
			return cjtCuadricula;
		}
}