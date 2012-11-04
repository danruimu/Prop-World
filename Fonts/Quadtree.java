
import java.util.ArrayList;

/**
 * Quadtree es una estructura de datos en 2D, cuya propiedad común es que están basados en el principio de 
 * descomposición recursiva del espacio. En un QuadTree de puntos, el centro de una subdivisión está siempre 
 * en un punto. Al insertar un nuevo elemento, el espacio queda divido en cuatro cuadrantes. Al repetir el 
 * proceso, el cuadrante se divide de nuevo en cuatro cuadrantes, y así sucesivamente.
 * @author Victor Cayuela Narbona y Fernando Pocino Pasías
 * @cluster 6
 * @grupo 2
 * @version 1.0
   */
public class Quadtree {
	
	private class nodo {
		int cant = 0;
		boolean lleno = false;
		ArrayList<Coordenada> lista_puntos;
		double x_min, x_max, y_min, y_max;
		nodo I, II,III, IV;   // cuatro cuadrantes
		
		public nodo(double x_min,double x_max,double y_min,double y_max){
			lista_puntos = new ArrayList<Coordenada>();
			this.x_min = x_min;
			this.x_max = x_max;
			this.y_min = y_min;
			this.y_max = y_max;
		}
	}
	
	/**
	 * Identifica el cuadrante del Quadtree
	 */
	private nodo a;
	
	/**
	 * Numero de coordenadas maxima que se pueden agrupar en cada nodo(cuadrante)
	 */
	int capacmax;
	
	/**
	 * Distancia minima a una coordenada de entre todas las coordenadas del quadtree
	 */
	double distance;
	
	/**
	 * Calcula la distancia que hay entre dos coordenadas
	 * @param c1 Es la coordenada Origen
	 * @param c2 Es la coordenada Destino
	 * @return Retorna la distancia que hay entre c1 y c2
	 */
	
	/**
	 * Crea un Quadtree con las coordenadas del vector que recibe
	 * @param vec El es vector de coordenadas 
	 */
	public Quadtree(ArrayList<Coordenada> vec, int cap){
		capacmax = cap;
		a = new nodo(0,0,0,0);
		a.lleno = true;
		if (vec.size() > 0){
			a.x_min= vec.get(0).getLongitud();
			a.x_max = vec.get(0).getLongitud();
			a.y_min= vec.get(0).getLatitud();
			a.y_max = vec.get(0).getLatitud();
			for(int i= 1; i< vec.size(); ++i){
				if (vec.get(i).getLongitud() > a.x_max) a.x_max = vec.get(i).getLongitud();
				else if (vec.get(i).getLongitud() < a.x_min) a.x_min = vec.get(i).getLongitud();
				if (vec.get(i).getLatitud() > a.y_max) a.y_max = vec.get(i).getLatitud();
				else if (vec.get(i).getLatitud() < a.y_min) a.y_min = vec.get(i).getLatitud();
			}		
			crearParticiones(a, vec);
		}
	}	
	
	/**
	 * Crea las particiones del Quadtree
	 * @param no Es el nodo(cuadrante) al que se le hace la particion
	 * @param vec Es el vector de coordenadas que queremos distribuir en el Quadtree
	 */
	private  void crearParticiones(nodo no, ArrayList<Coordenada> vec){
		double x_med=(no.x_max+no.x_min)/2;
		double y_med= (no.y_max+no.y_min)/2;
		no.I = new nodo(no.x_min,x_med,y_med,no.y_max);
		no.II = new nodo(x_med,no.x_max,y_med,no.y_max);
		no.III = new nodo(x_med,no.x_max,no.y_min,y_med);
		no.IV = new nodo(no.x_min,x_med,no.y_min,y_med);
		for (int i=0; i < vec.size(); ++i){
			addnodo(no, vec.get(i));
		}
		
	}
	
	/**
	 * Inserta la coordenada en el cuadrante que le corresponde
	 * @param a nodo(cuadrante) al que se le inserta una coordenada
	 * @param c Coordenada que queremos insertar en un nodo(cuadrante)
	 */
	private void addnodo(nodo a, Coordenada c){
		if (c.getLongitud() >= a.II.x_min && c.getLatitud() >= a.II.y_min){ //pertenece al cuadrante II
			if (a.II.lleno) addnodo(a.II, c); // si esta lleno significa q en esta particion tengo mas particiones
			else {
				if (a.II.cant == capacmax){ // si se acaba de llenar creamos otras 4 particiones dentro de esta particion
					a.II.lleno = true;
					crearParticiones(a.II, a.II.lista_puntos);
					addnodo(a.II,c);  //anadimos la coordenada "101"...
					a.II.lista_puntos.clear();  //limpiamos la lista...
				}
				else {
					a.II.lista_puntos.add(c); // introducimos la coordenada en particion II
					++a.II.cant;
				}
			}
		}
		else if (c.getLongitud() >= a.II.x_min && c.getLatitud() < a.II.y_min){ //pertenece al cuadrante III
			if (a.III.lleno) addnodo(a.III, c); // si esta lleno significa que en esta particion tengo mas particiones
			else {
				if (a.III.cant == capacmax){ // si se acaba de llenar creamos otras 4 particiones dentro de esta particion
					a.III.lleno = true;
					crearParticiones(a.III, a.III.lista_puntos);
					addnodo(a.III,c);  // anadimos la coordenada "101"
					a.III.lista_puntos.clear(); // limpiamos la lista
				}
				else {
					a.III.lista_puntos.add(c); // introducimos la coordenada en particion III
					++a.III.cant;
				}
			}
		}
		else if (c.getLongitud() < a.II.x_min && c.getLatitud() >= a.I.y_min){ //pertenece al cuadrante I
			if (a.I.lleno) addnodo(a.I, c); // si esta lleno significa q en esta particion tengo mas particiones
			else {
				if (a.I.cant == capacmax){ // si se acaba de llenar creamos otras 4 particiones dentro de esta particion
					a.I.lleno = true;
					crearParticiones(a.I, a.I.lista_puntos);
					addnodo(a.I,c);  // anadimos la coordenada "101"
					a.I.lista_puntos.clear();  // limpiamos la lista
				}
				else {
					a.I.lista_puntos.add(c); // introducimos la coordenada en particion I
					++a.I.cant;
				}
			}
		}
		else { //pertenece al cuadrante IV
			if (a.IV.lleno) addnodo(a.IV, c); // si esta lleno significa q en esta particion tengo mas particiones
			else {
				if (a.IV.cant == capacmax){ // si se acaba de llenar creamos otras 4 particiones dentro de esta particion
					a.IV.lleno = true;
					crearParticiones(a.IV, a.IV.lista_puntos);
					addnodo(a.IV,c);  // anadimos la coordenada "101"
					a.IV.lista_puntos.clear();  // limpiamos la lista
				}
				else {
					a.IV.lista_puntos.add(c); // introducimos la coordenada en particion IV
					++a.IV.cant;
				}
			}
		}
	}
	
	/**
	 * Devuelve las coordenadas que estan una distancia menor o igual a una coordenada dada 
	 * @param c Es la coordenada de la cual queremos saber sus coordenadas cercanas
	 * @param km Distancia maxima que es considerada como coordenada cercana
	 * @return Retorna un vector con las coordenadas cercanas a la coordenada dada 
	 */
	public ArrayList<Coordenada> puntosCercanos(Coordenada c, double km){
		ArrayList<Coordenada> vec = new ArrayList<Coordenada>();
		puntosCercanos(c,km,a,vec);
		return vec;
	}	
	
	/**
	 * Inserta en el vector, las coordenadas cercanas a la coordenada dada
	 * @param c Es la coordenada de la cual queremos saber sus coordenadas cercanas
	 * @param km Distancia maxima que es considerada como coordenada cercana
	 * @param h nodo(cuadrante) donde estan situadas las coordenadas cercanas
	 * @param vec Vector con las coordenadas cercanas a la coordenada dada
	 */
	private void puntosCercanos(Coordenada c, double km, nodo h,ArrayList<Coordenada> vec){
		boolean cuad1 = false;
		boolean cuad2 = false;
		boolean cuad3 = false;
		boolean cuad4 = false;
		if (!h.lleno){ // caso base: IMPORTANTE!!
			for(int i = 0; i < h.lista_puntos.size();++i){
				if (c.calcularDistancia(h.lista_puntos.get(i)) <= km){
					vec.add(h.lista_puntos.get(i));
				}
			}
		}
		else {
			if (h.I.x_min <= c.getLongitud() && c.getLongitud() < h.I.x_max && h.I.y_min <= c.getLatitud() && c.getLatitud() <= h.I.y_max){ // si estamos en el cuadrante 1
				cuad1 = true;
				puntosCercanos(c,km,h.I,vec);
			}
			else if (h.II.x_min <= c.getLongitud() && c.getLongitud() <= h.II.x_max && h.II.y_min <= c.getLatitud() && c.getLatitud() <= h.II.y_max){ // si estamos en el cuadrante 2
				cuad2 = true;
				puntosCercanos(c,km,h.II,vec);
			}
			else if (h.III.x_min <= c.getLongitud() && c.getLongitud() <= h.III.x_max && h.III.y_min <= c.getLatitud() && c.getLatitud() < h.III.y_max){ // si estamos en el cuadrante 3
				cuad3 = true;
				puntosCercanos(c,km,h.III,vec);
			}
			else if (h.IV.x_min <= c.getLongitud() && c.getLongitud() < h.IV.x_max && h.IV.y_min <= c.getLatitud() && c.getLatitud() < h.IV.y_max){ // si estamos en el cuadrante 4
				cuad4 = true;
				puntosCercanos(c,km,h.IV,vec);
			}
			// Hasta aqui miraremos en que cuadrante esta...
			//Miramos si corta con la vertical de algun cuadrante...
			if (h.I.x_min <= c.getLongitud() && c.getLongitud() < h.I.x_max && !cuad1){ // Si corta la vertical con el cuadrante I
				cuad1 = true;
				Coordenada aux;
				if (c.getLatitud() < h.I.y_min)  aux = new Coordenada(c.getLongitud(),h.I.y_min);
				else aux = new Coordenada(c.getLongitud(),h.I.y_max);
				if (aux.calcularDistancia(c) <= km){
					puntosCercanos(c,km,h.I,vec); // Si el cuadrante I esta a menos distancia de la indicada entraremos
				}
			}
			if (h.II.x_min <= c.getLongitud() && c.getLongitud() <= h.II.x_max && !cuad2){ // Si corta la vertical con el cuadrante II
				cuad2 = true;
				Coordenada aux;
				if (c.getLatitud() < h.II.y_min)  aux = new Coordenada(c.getLongitud(),h.II.y_min);
				else aux = new Coordenada(c.getLongitud(),h.II.y_max);
				if (aux.calcularDistancia(c) <= km){
					puntosCercanos(c,km,h.II,vec); // Si el cuadrante II esta a menos distancia de la indicada entraremos
				}
			}
			if (h.III.x_min <= c.getLongitud() && c.getLongitud() <= h.III.x_max && !cuad3){ // Si corta la vertical con el cuadrante III
				cuad3 = true;
				Coordenada aux;
				if (c.getLatitud() < h.III.y_min)  aux = new Coordenada(c.getLongitud(),h.III.y_min);
				else aux = new Coordenada(c.getLongitud(),h.III.y_max);
				if (aux.calcularDistancia(c) <= km){
					puntosCercanos(c,km,h.III,vec); // Si el cuadrante III esta a menos distancia de la indicada entraremos
				}
			}
			if (h.IV.x_min <= c.getLongitud() && c.getLongitud() < h.IV.x_max && !cuad4){ // Si corta la vertical con el cuadrante IV
				cuad4 = true;
				Coordenada aux;
				if (c.getLatitud() < h.IV.y_min)  aux = new Coordenada(c.getLongitud(),h.IV.y_min);
				else aux = new Coordenada(c.getLongitud(),h.IV.y_max);
				if (aux.calcularDistancia(c) <= km){
					puntosCercanos(c,km,h.IV,vec); // Si el cuadrante IV esta a menos distancia de la indicada entraremos
				}
			}
			//Hasta aqui el corte vertical
			//Miramos si corta con la horizontal de algun cuadrante...
			if (h.I.y_min <= c.getLatitud() && c.getLatitud() <= h.I.y_max && !cuad1){ // Si corta la horizontal con el cuadrante I
				Coordenada aux;
				if (c.getLongitud() < h.I.x_min)  aux = new Coordenada(h.I.x_min,c.getLatitud());
				else aux = new Coordenada(h.I.x_max,c.getLatitud());
				if (aux.calcularDistancia(c) <= km){
					cuad1 = true;
					puntosCercanos(c,km,h.I,vec); // Si el cuadrante I esta a menos distancia de la indicada entraremos
				}
			}
			if (h.II.y_min <= c.getLatitud() && c.getLatitud() <= h.II.y_max && !cuad2){ // Si corta la horizontal con el cuadrante II
				Coordenada aux;
				if (c.getLongitud() < h.II.x_min)  aux = new Coordenada(h.II.x_min,c.getLatitud());
				else aux = new Coordenada(h.II.x_max,c.getLatitud());
				if (aux.calcularDistancia(c) <= km){
					cuad2 = true;
					puntosCercanos(c,km,h.II,vec); // Si el cuadrante II esta a menos distancia de la indicada entraremos
				}
			}
			if (h.III.y_min <= c.getLatitud() && c.getLatitud() <= h.III.y_max && !cuad3){ // Si corta la horizontal con el cuadrante III
				Coordenada aux;
				if (c.getLongitud() < h.III.x_min)  aux = new Coordenada(h.III.x_min,c.getLatitud());
				else aux = new Coordenada(h.III.x_max,c.getLatitud());
				if (aux.calcularDistancia(c) <= km){
					cuad3 = true;
					puntosCercanos(c,km,h.III,vec); // Si el cuadrante III esta a menos distancia de la indicada entraremos
				}
			}
			if (h.IV.y_min <= c.getLatitud() && c.getLatitud() <= h.IV.y_max && !cuad4){ // Si corta la horizontal con el cuadrante IV
				Coordenada aux;
				if (c.getLongitud() < h.IV.x_min)  aux = new Coordenada(h.IV.x_min,c.getLatitud());
				else aux = new Coordenada(h.IV.x_max,c.getLatitud());
				if (aux.calcularDistancia(c) <= km){
					cuad4 = true;
					puntosCercanos(c,km,h.IV,vec); // Si el cuadrante I esta a menos distancia de la indicada entraremos
				}
			}
			//Hasta aqui el corte horizontal
			//Miramos las diagonales...
			if (!cuad1){
				Coordenada aux;
				if (c.getLongitud() < h.I.x_min && c.getLatitud() < h.I.y_min) aux = new Coordenada(h.I.x_min,h.I.y_min);
				else if (c.getLongitud() < h.I.x_min && c.getLatitud() > h.I.y_max) aux = new Coordenada(h.I.x_min,h.I.y_max);
				else if (c.getLongitud() > h.I.x_max && c.getLatitud() > h.I.y_max) aux = new Coordenada(h.I.x_max,h.I.y_max);
				else aux = new Coordenada(h.I.x_max,h.I.y_min);
				
				if (aux.calcularDistancia(c) <= km){
					cuad1 = true;
					puntosCercanos(c,km,h.I,vec); // Si el cuadrante I esta a menos distancia de la indicada entraremos
				}
			}
			if (!cuad2){
				Coordenada aux;
				if (c.getLongitud() < h.II.x_min && c.getLatitud() < h.II.y_min) aux = new Coordenada(h.II.x_min,h.II.y_min);
				else if (c.getLongitud() < h.II.x_min && c.getLatitud() > h.II.y_max) aux = new Coordenada(h.II.x_min,h.II.y_max);
				else if (c.getLongitud() > h.II.x_max && c.getLatitud() > h.II.y_max) aux = new Coordenada(h.II.x_max,h.II.y_max);
				else aux = new Coordenada(h.II.x_max,h.II.y_min);
				
				if (aux.calcularDistancia(c) <= km){
					cuad2 = true;
					puntosCercanos(c,km,h.II,vec); // Si el cuadrante II esta a menos distancia de la indicada entraremos
				}
			}
			if (!cuad3){
				Coordenada aux;
				if (c.getLongitud() < h.III.x_min && c.getLatitud() < h.III.y_min) aux = new Coordenada(h.III.x_min,h.III.y_min);
				else if (c.getLongitud() < h.III.x_min && c.getLatitud() > h.III.y_max) aux = new Coordenada(h.III.x_min,h.III.y_max);
				else if (c.getLongitud() > h.III.x_max && c.getLatitud() > h.III.y_max) aux = new Coordenada(h.III.x_max,h.III.y_max);
				else aux = new Coordenada(h.III.x_max,h.III.y_min);
				
				if (aux.calcularDistancia(c) <= km){
					cuad3 = true;
					puntosCercanos(c,km,h.III,vec); // Si el cuadrante III esta a menos distancia de la indicada entraremos
				}
			}
			if (!cuad4){
				Coordenada aux;
				if (c.getLongitud() < h.IV.x_min && c.getLatitud() < h.IV.y_min) aux = new Coordenada(h.IV.x_min,h.IV.y_min);
				else if (c.getLongitud() < h.IV.x_min && c.getLatitud() > h.IV.y_max) aux = new Coordenada(h.IV.x_min,h.IV.y_max);
				else if (c.getLongitud() > h.IV.x_max && c.getLatitud() > h.IV.y_max) aux = new Coordenada(h.IV.x_max,h.IV.y_max);
				else aux = new Coordenada(h.IV.x_max,h.IV.y_min);
				
				if (aux.calcularDistancia(c) <= km){
					cuad4 = true;
					puntosCercanos(c,km,h.IV,vec); // Si el cuadrante IV esta a menos distancia de la indicada entraremos
				}
			}
			//Fin de las diagonales...
		}
	}
	
	/**
	 * Calcula la distancia entre una coordenada dada y su coordenada mas cercana
	 * @param c Coordenada de la cual queremos saber la distancia que hay entre ella y su coordenada mas cercana 
	 * @return Retorna la distancia que hay c y su coordenada mas cercana
	 */
	public double getDistanciaMinima(Coordenada c){
		distance = -1;
		distanciaMinimaAux(c, a);
		return distance;
	}
	
	/**
	 * Compara las distancias de las coordenadas mas cercanas a una coordenada dada de entre los diferentes nodos(quadrantes). 
	 * @param c Coordenada de la cual queremos saber cual es la coordenada mas cercana a esta
	 * @param h Diferentes nodos(cuadrantes) del quadtree por donde estan distribuidas las coordenadas 
	 */
	private void distanciaMinimaAux(Coordenada c, nodo h){
		boolean cuad1 = false;
		boolean cuad2 = false;
		boolean cuad3 = false;
		boolean cuad4 = false;
		if (!h.lleno){	// caso base: IMPORTANTE!!
			if (h.lista_puntos.size() > 0 && distance == -1){
				distance = c.calcularDistancia(h.lista_puntos.get(0));
			}
			for(int i = 0; i < h.lista_puntos.size();++i){
				double distaux = c.calcularDistancia(h.lista_puntos.get(i));
				if (distance > distaux) distance = distaux;
			}
		}
		else {
			if (h.I.x_min <= c.getLongitud() && c.getLongitud() < h.I.x_max && h.I.y_min <= c.getLatitud() && c.getLatitud() <= h.I.y_max){ // si estamos en el cuadrante 1
				cuad1 = true;
				distanciaMinimaAux(c, h.I);
			}
			else if (h.II.x_min <= c.getLongitud() && c.getLongitud() <= h.II.x_max && h.II.y_min <= c.getLatitud() && c.getLatitud() <= h.II.y_max){ // si estamos en el cuadrante 2
				cuad2 = true;
				distanciaMinimaAux(c, h.II);
			}
			else if (h.III.x_min <= c.getLongitud() && c.getLongitud() <= h.III.x_max && h.III.y_min <= c.getLatitud() && c.getLatitud() < h.III.y_max){ // si estamos en el cuadrante 3
				cuad3 = true;
				distanciaMinimaAux(c, h.III);
			}
			else if (h.IV.x_min <= c.getLongitud() && c.getLongitud() < h.IV.x_max && h.IV.y_min <= c.getLatitud() && c.getLatitud() < h.IV.y_max){ // si estamos en el cuadrante 4
				cuad4 = true;
				distanciaMinimaAux(c, h.IV);
			}
			//Miramos si corta con la vertical de algun cuadrante...
			if (h.I.x_min <= c.getLongitud() && c.getLongitud() < h.I.x_max && !cuad1){ // Si corta la vertical con el cuadrante I
				Coordenada aux;
				if (c.getLatitud() < h.I.y_min)  aux = new Coordenada(c.getLongitud(),h.I.y_min);
				else aux = new Coordenada(c.getLongitud(),h.I.y_max);
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante I esta a menos distancia que el minimo
						cuad1 = true;
						distanciaMinimaAux(c,h.I); 
					}
				}
				else distanciaMinimaAux(c,h.I); // si aun no se ha enontrado un punto minimo
			}
			if (h.II.x_min <= c.getLongitud() && c.getLongitud() <= h.II.x_max && !cuad2){ // Si corta la vertical con el cuadrante II
				Coordenada aux;
				if (c.getLatitud() < h.II.y_min)  aux = new Coordenada(c.getLongitud(),h.II.y_min);
				else aux = new Coordenada(c.getLongitud(),h.II.y_max);
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante II esta a menos distancia que el minimo
						cuad2 = true;
						distanciaMinimaAux(c,h.II); 
					}
				}
				else distanciaMinimaAux(c,h.II); // si aun no se ha enontrado un punto minimo
			}
			if (h.III.x_min <= c.getLongitud() && c.getLongitud() <= h.III.x_max && !cuad3){ // Si corta la vertical con el cuadrante III
				Coordenada aux;
				if (c.getLatitud() < h.III.y_min)  aux = new Coordenada(c.getLongitud(),h.III.y_min);
				else aux = new Coordenada(c.getLongitud(),h.III.y_max);
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante III esta a menos distancia que el minimo
						cuad3 = true;
						distanciaMinimaAux(c,h.III); 
					}
				}
				else distanciaMinimaAux(c,h.III); // si aun no se ha enontrado un punto minimo
			}
			if (h.IV.x_min <= c.getLongitud() && c.getLongitud() < h.IV.x_max && !cuad4){ // Si corta la vertical con el cuadrante IV
				Coordenada aux;
				if (c.getLatitud() < h.IV.y_min)  aux = new Coordenada(c.getLongitud(),h.IV.y_min);
				else aux = new Coordenada(c.getLongitud(),h.IV.y_max);
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante IV esta a menos distancia que el minimo
						cuad4 = true;
						distanciaMinimaAux(c,h.IV); 
					}
				}
				else distanciaMinimaAux(c,h.IV); // si aun no se ha enontrado un punto minimo
			}
			//Hasta aqui el corte vertical
			//Miramos si corta con la horizontal de algun cuadrante...
			if (h.I.y_min <= c.getLatitud() && c.getLatitud() < h.I.y_max && !cuad1){ // Si corta la horizontal con el cuadrante I
				Coordenada aux;
				if (c.getLongitud() < h.I.x_min)  aux = new Coordenada(h.I.x_min,c.getLatitud());
				else aux = new Coordenada(h.I.x_max,c.getLatitud());
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante I esta a menos distancia que el minimo
						cuad1 = true;
						distanciaMinimaAux(c,h.I); 
					}
				}
				else distanciaMinimaAux(c,h.I); // si aun no se ha enontrado un punto minimo
			}
			if (h.II.y_min <= c.getLatitud() && c.getLatitud() <= h.II.y_max && !cuad2){ // Si corta la horizontal con el cuadrante II
				Coordenada aux;
				if (c.getLongitud() < h.II.x_min)  aux = new Coordenada(h.II.x_min,c.getLatitud());
				else aux = new Coordenada(h.II.x_max,c.getLatitud());
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante II esta a menos distancia que el minimo
						cuad2 = true;
						distanciaMinimaAux(c,h.II); 
					}
				}
				else distanciaMinimaAux(c,h.II); // si aun no se ha enontrado un punto minimo
			}
			if (h.III.y_min <= c.getLatitud() && c.getLatitud() <= h.III.y_max && !cuad3){ // Si corta la horizontal con el cuadrante III
				Coordenada aux;
				if (c.getLongitud() < h.III.x_min)  aux = new Coordenada(h.III.x_min,c.getLatitud());
				else aux = new Coordenada(h.III.x_max,c.getLatitud());
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante III esta a menos distancia que el minimo
						cuad3 = true;
						distanciaMinimaAux(c,h.III); 
					}
				}
				else distanciaMinimaAux(c,h.III); // si aun no se ha enontrado un punto minimo
			}
			if (h.IV.y_min <= c.getLatitud() && c.getLatitud() <= h.IV.y_max && !cuad1){ // Si corta la horizontal con el cuadrante IV
				Coordenada aux;
				if (c.getLongitud() < h.IV.x_min)  aux = new Coordenada(h.IV.x_min,c.getLatitud());
				else aux = new Coordenada(h.IV.x_max,c.getLatitud());
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante IV esta a menos distancia que el minimo
						cuad4 = true;
						distanciaMinimaAux(c,h.IV); 
					}
				}
				else distanciaMinimaAux(c,h.IV); // si aun no se ha enontrado un punto minimo
			}
			//Hasta aqui el corte horizontal
			//Miramos las diagonales...
			if (!cuad1){
				Coordenada aux;
				if (c.getLongitud() < h.I.x_min && c.getLatitud() < h.I.y_min) aux = new Coordenada(h.I.x_min,h.I.y_min);
				else if (c.getLongitud() < h.I.x_min && c.getLatitud() > h.I.y_max) aux = new Coordenada(h.I.x_min,h.I.y_max);
				else if (c.getLongitud() > h.I.x_max && c.getLatitud() > h.I.y_max) aux = new Coordenada(h.I.x_max,h.I.y_max);
				else aux = new Coordenada(h.I.x_max,h.I.y_min);
				cuad1 = true;
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante III esta a menos distancia que el minimo
						distanciaMinimaAux(c,h.I); 
					}
				}
				else distanciaMinimaAux(c,h.I); // si aun no se ha enontrado un punto minimo
			}
			if (!cuad2){
				Coordenada aux;
				if (c.getLongitud() < h.II.x_min && c.getLatitud() < h.II.y_min) aux = new Coordenada(h.II.x_min,h.II.y_min);
				else if (c.getLongitud() < h.II.x_min && c.getLatitud() > h.II.y_max) aux = new Coordenada(h.II.x_min,h.II.y_max);
				else if (c.getLongitud() > h.II.x_max && c.getLatitud() > h.II.y_max) aux = new Coordenada(h.II.x_max,h.II.y_max);
				else aux = new Coordenada(h.II.x_max,h.II.y_min);
				cuad2 = true;
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante IV esta a menos distancia que el minimo
						distanciaMinimaAux(c,h.II); 
					}
				}
				else distanciaMinimaAux(c,h.II); // si aun no se ha enontrado un punto minimo
			}
			if (!cuad3){
				Coordenada aux;
				if (c.getLongitud() < h.III.x_min && c.getLatitud() < h.III.y_min) aux = new Coordenada(h.III.x_min,h.III.y_min);
				else if (c.getLongitud() < h.III.x_min && c.getLatitud() > h.III.y_max) aux = new Coordenada(h.III.x_min,h.III.y_max);
				else if (c.getLongitud() > h.III.x_max && c.getLatitud() > h.III.y_max) aux = new Coordenada(h.III.x_max,h.III.y_max);
				else aux = new Coordenada(h.III.x_max,h.III.y_min);
				cuad3 = true;
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante III esta a menos distancia que el minimo
						distanciaMinimaAux(c,h.III); 
					}
				}
				else distanciaMinimaAux(c,h.III); // si aun no se ha enontrado un punto minimo
			}
			if (!cuad4){
				Coordenada aux;
				if (c.getLongitud() < h.IV.x_min && c.getLatitud() < h.IV.y_min) aux = new Coordenada(h.IV.x_min,h.IV.y_min);
				else if (c.getLongitud() < h.IV.x_min && c.getLatitud() > h.IV.y_max) aux = new Coordenada(h.IV.x_min,h.IV.y_max);
				else if (c.getLongitud() > h.IV.x_max && c.getLatitud() > h.IV.y_max) aux = new Coordenada(h.IV.x_max,h.IV.y_max);
				else aux = new Coordenada(h.IV.x_max,h.IV.y_min);
				if (distance != -1){ // si ya tenemos un punto minimo
					if (aux.calcularDistancia(c) < distance){ // Si el cuadrante II esta a menos distancia que el minimo
						cuad4 = true;
						distanciaMinimaAux(c,h.IV); 
					}
				}
				else distanciaMinimaAux(c,h.IV); // si aun no se ha enontrado un punto minimo
			}
			//Fin de las diagonales...
		}
	}
	
	/**
	 * Calcula una ruta entre un conjunto de topónimos con un origen y un destino.
	 * @param ori Coordenada de origen para calcular la ruta.
	 * @param des Coordenada de destino para calcular la ruta.
	 * @return Retorna una lista de coordenadas ordenadas desde el origen ori al destino des (si hay camino 
	 * en saltos de menos de 100km).
	 */
	public ArrayList<Coordenada> calcularRuta(Coordenada ori, Coordenada des){
		ArrayList<Coordenada> res = new ArrayList<Coordenada>();
		res.add(ori);
		calcularRuta(ori,des,res);
		return res;
	}
	
	/**
	 * Calcula una ruta entre un conjunto de topónimos con un origen y un destino.
	 * @param ori Coordenada de origen para calcular la ruta.
	 * @param des Coordenada de destino para calcular la ruta.
	 * @param res Lista de las coordenadas de la ruta ordenadas desde el origen ori al destino des (si hay camino
	 * en saltos de menos de 100km).
	 */
	private void calcularRuta(Coordenada ori,Coordenada des,ArrayList<Coordenada> res){
		if (ori.getLatitud() != des.getLatitud() || ori.getLongitud() != des.getLongitud()) {
			ArrayList<Coordenada> aux = new ArrayList<Coordenada>();
			aux = puntosCercanos(ori, 200);
			double dis = ori.calcularDistancia(des);
			Coordenada c = new Coordenada();
			c = ori;
			for(int i = 0; i < aux.size();++i) {
				double dis2 = aux.get(i).calcularDistancia(des);
				if (dis > dis2) {
					dis = dis2;
					c = aux.get(i);	
				}
			}
			if (c.getLatitud() == ori.getLatitud() && c.getLongitud() == ori.getLongitud()) {
				res.clear();
			}
			else {
				res.add(c);
				calcularRuta(c, des,res);
			}
		}
	}
	
	/**
	 * Calcula si la Coordenada c esta en el quadtree o no.
	 * @param c Coordenada a comprobar su pertenencia al quadtree.
	 * @return Retorna si está o no.
	 */
	public boolean contiene(Coordenada c) {
		return contiene(c,a);
	}
	
	/**
	 * Calcula si la Coordenada c esta en el nodo h o no.
	 * @param c Coordenada a comprobar su pertenencia al nodo h.
	 * @param h Nodo al cual mirar si está la Coordenada c.
	 * @return Retorna si está o no.
	 */
	private boolean contiene(Coordenada c,nodo h){
		if (!h.lleno){ // caso base: IMPORTANTE!!
			for(int i = 0; i < h.lista_puntos.size();++i){
				if (h.lista_puntos.get(i).getLatitud() == c.getLatitud() && h.lista_puntos.get(i).getLongitud() == c.getLongitud()) {
					return true;
				}
			}
			return false;
		}
		else {
			if (h.I.x_min <= c.getLongitud() && c.getLongitud() < h.I.x_max && h.I.y_min <= c.getLatitud() && c.getLatitud() <= h.I.y_max){ // si estamos en el cuadrante 1
				return contiene(c,h.I);
			}
			else if (h.II.x_min <= c.getLongitud() && c.getLongitud() <= h.II.x_max && h.II.y_min <= c.getLatitud() && c.getLatitud() <= h.II.y_max){ // si estamos en el cuadrante 2
				return contiene(c,h.II);
			}
			else if (h.III.x_min <= c.getLongitud() && c.getLongitud() <= h.III.x_max && h.III.y_min <= c.getLatitud() && c.getLatitud() < h.III.y_max){ // si estamos en el cuadrante 3
				return contiene(c,h.III);
			}
			else if (h.IV.x_min <= c.getLongitud() && c.getLongitud() < h.IV.x_max && h.IV.y_min <= c.getLatitud() && c.getLatitud() < h.IV.y_max){ // si estamos en el cuadrante 4
				return contiene(c,h.IV);
			}
		}
		return false;
	}
}
