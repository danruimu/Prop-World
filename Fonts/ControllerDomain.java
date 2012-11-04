import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Controlador de la capa de domino del programa. Gestiona todas las operaciones
 * y estructuras de datos de la capa de domino necesarios para los procesos
 * y funcionalidades principales del programa.
 * @author Daniel Ruiz Muñoz
 * @version 2.0
 * @Cluster 6
 * @Grupo 2
 */
public class ControllerDomain {
	
	/**
	 * Lista de los usuarios que guarda el sistema
	 */
	private ArrayList<Usuario> listaUsers;
	
	/**
	 * Usuario que representa al usuario que ha iniciado sesion
	 */
	private Usuario user;
	
	/**
	 * Lista con todos los contornos que guarda el sistema
	 */
	private ArrayList<Contorno> conjuntoContorno;
	
	/**
	 * Conjunto de todos los paises que guarda el sistema
	 */
	private ConjuntoPaises conjuntoPais;
	
	/**
	 * Conjunto de todas las cuadriculas que guarda el sistema
	 */
	private ConjuntoCuadricula conjuntoCuadricula;
	
	/**
	 * Controlador de la capa de datos necesario para obtener datos de disco
	 */
	private ControllerCapaDatos contCapaDatos;
	
	/**
	 * Grafo con forma de matriz de adyacencias
	 */
	private ArrayList<ArrayList<Integer>> adyacencias;
	
	/**
	 * Lista que contiene todas las ciudades del mundo
	 */
	private ArrayList<Ciudad> ciudades;
	
	/**
	 * Creadora por defecto.
	 */
	public ControllerDomain() {
		listaUsers = new ArrayList<Usuario>();
		user = new Usuario();
		conjuntoContorno = new ArrayList<Contorno>();
		conjuntoPais = new ConjuntoPaises();
		conjuntoCuadricula = new ConjuntoCuadricula();
		contCapaDatos = new ControllerCapaDatos();
		adyacencias = new ArrayList<ArrayList<Integer>>();
		ciudades = new ArrayList<Ciudad>();
	}
	
	/**
	 * Metodo que inicializa las estructuras de datos necesarias para la ejecucion del
	 * programa.
	 */
	public void initControllerDomain() {
		listaUsers = new ArrayList<Usuario>();
		user = new Usuario();
		conjuntoContorno = new ArrayList<Contorno>();
		conjuntoPais = new ConjuntoPaises();
		conjuntoCuadricula = new ConjuntoCuadricula();
		contCapaDatos = new ControllerCapaDatos();
		adyacencias = new ArrayList<ArrayList<Integer>>();
		this.ciudades = new ArrayList<Ciudad>();
		/*Inicializar las adyacencias de los contornos*/
		if(!contCapaDatos.existsFileAdyacencias()) {
			ArrayList<ArrayList<String>> contornos = new ArrayList<ArrayList<String>>(contCapaDatos.readFronteras());
			for(int i=0; i<contornos.size(); ++i) {
				String nombre = contornos.get(i).get(0);
				int idContorno = Integer.parseInt(contornos.get(i).get(1));
				ArrayList<Coordenada> aux = new ArrayList<Coordenada>();
				++i;
				while(!contornos.get(i).get(0).equals("NULL")) {
					Coordenada caux = new Coordenada(Double.parseDouble(contornos.get(i).get(1)), Double.parseDouble(contornos.get(i).get(0)));
					aux.add(caux);
					++i;
				}
				Pais p = new Pais();
				p.setNombre(nombre);
				Contorno c = new Contorno(aux, idContorno, new ArrayList<Toponimo>(), p);
				conjuntoCuadricula.addContorno(c);
				conjuntoContorno.add(c);
			}
			/*
			 * Aqui ya tengo los contornos con coordenadas creados, ahora se calcularan
			 * las adyacencias
			 */
			ArrayList<ArrayList<String>> ad = new ArrayList<ArrayList<String>>();
			for(Contorno c : conjuntoContorno) {
				ArrayList<Contorno> aux = new ArrayList<Contorno>(c.getContornosAdyacentes());
				ArrayList<String> res = new ArrayList<String>();
				res.add(String.valueOf(c.getId()));
				for(Contorno c2 : aux) {
					res.add(String.valueOf(c2.getId()));
				}
				ad.add(res);
			}
			contCapaDatos.writeFileAdyacencias(ad);
			conjuntoCuadricula = new ConjuntoCuadricula();
			conjuntoContorno = new ArrayList<Contorno>();
		}
		adyacencias = new ArrayList<ArrayList<Integer>>(contCapaDatos.readFileAdyacencias());
		
		/*
		 * Ahora se deben crear los paises
		 */
		ArrayList<ArrayList<String>> aux = new ArrayList<ArrayList<String>>(contCapaDatos.readFronteras());
		for(int i=0; i<aux.size(); ++i) {
			String nombre = aux.get(i).get(0);
			int id = Integer.parseInt(aux.get(i).get(1));
			++i;
			Contorno c = new Contorno();
			c.setId(id);
			c.setPais(new Pais(nombre));
			while(!aux.get(i).get(0).equals("NULL")) {
				++i;
			}
			conjuntoContorno.add(c);
		}
		/*
		 * conjuntoContorno contiene toda la lista de contornos con un pais dentro solo con el nombre,
		 * queda crear los paises con el nombre y los contornos solo con identificador
		 */
		for(int i = 0; i<conjuntoContorno.size(); ++i) {
			Pais p = new Pais(conjuntoContorno.get(i).getPais().getNombre());
			while(i<conjuntoContorno.size() && conjuntoContorno.get(i).getPais().getNombre().equals(p.getNombre())) {
				p.addContorno(conjuntoContorno.get(i));
				++i;
			}
			--i;
			boolean existe = false;
			for(int j=0; j<conjuntoPais.getPaises().size(); ++j) {
				if(conjuntoPais.getPaises().get(j).getNombre().equals(p.getNombre())) {
					for(int l=0; l<p.getNContornos(); ++l) {
						conjuntoPais.getPaises().get(j).addContorno(p.getContorno(l));
					}
					j = conjuntoPais.getPaises().size();
					existe = true;
				}
			}
			if(!existe) {
				conjuntoPais.addPais(p);
			}
		}
		conjuntoContorno = new ArrayList<Contorno>();	//eliminas el arraylist puesto que ya no lo usaremos mas
		
		/*Inicializar los toponimos*/
		ArrayList<ArrayList<String>> toponimos = new ArrayList<ArrayList<String>>(contCapaDatos.readConjuntoToponimo());
		for(int i=0; i<toponimos.size(); ++i) {
			Coordenada c = new Coordenada(Double.parseDouble(toponimos.get(i).get(2)), Double.parseDouble(toponimos.get(i).get(1)));
			Toponimo t = new Toponimo();
			Ciudad ci = new Ciudad();
			if(toponimos.get(i).size()==4) {
				t = new Toponimo(toponimos.get(i).get(0), toponimos.get(i).get(3), c);
				conjuntoCuadricula.addToponimo(t);
			} else if(toponimos.get(i).size()==5) {
				ci = new Ciudad(toponimos.get(i).get(0), toponimos.get(i).get(3), c, Integer.parseInt(toponimos.get(i).get(4)));
				this.ciudades.add(ci);
			}
			
		}
		
		/*Recuperar usuarios*/
		ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>(contCapaDatos.readUsers());
		for(int i=0; i<users.size(); ++i) {
			Usuario user = new Usuario(users.get(i).get(0), users.get(i).get(1));
			user.setCodTop(users.get(i).get(2));
			user.setNumHab(Integer.parseInt(users.get(i).get(3)));
			listaUsers.add(user);
		}
		
	}
	
	/**
	 * Devuelve el nombre del usuario activo
	 * @return Devuelve el nombre del usuario activo
	 */
	public String getUserOnSession() {
		return user.getUsername();
	}
	
	/**
	 * Devuelve el password del usuario activo
	 * @return Devuelve el password del usuario activo
	 */
	public String getPassUserOnSession() {
		return user.getPass();
	}
	
	/**
	 * Devuelve el valor de la preferencia numero habitantes del usuario
	 * activo Devuelve el valor de la preferencia numero habitantes del usuario
	 * activo
	 * @return
	 */
	public int getUserNumHab() {
		return user.getNumHab();
	}
	
	/**
	 * Devuelve el codigo de toponimo prefereido del usuario activo
	 * @return Devuelve el codigo de toponimo prefereido del usuario activo
	 */
	public String getUserCodTopo() {
		return user.getCodTop();
	}
	
	/**
	 * Funcion que retorna el password del usuario deseado
	 * @param username Nombre del usuario del que se desea recuperar el password
	 * @return Devuelve el password del usuario username
	 */
	public String getPass(String username) {
		String res = null;
		for(Usuario u : listaUsers) {
			if(u.getUsername().equals(username)) {
				return u.getPass();
			}
		}
		return res;
	}
	
	/**
	 * Inicia sesion con el usuario con nombre igual a nameUser
	 * @param nameUser Nombre del usuario con el que se quiere iniciar sesion
	 * @return Devuelve cierto si se inicia sesion exito, en caso contrario
	 * se devuelve falso
	 */
	public boolean iniciarSesion(String nameUser, String passUser) {
		user = new Usuario();
		for(int i=0; i<listaUsers.size(); ++i) {
			if(listaUsers.get(i).getUsername().equals(nameUser) && listaUsers.get(i).getPass().equals(passUser)) {
				try {
					user = listaUsers.get(i);
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Dado un userName y un password se genera un usuario nuevo con estos datos
	 * @param userName
	 * @param password
	 * @return Se retorna true si la operación a tenido exito. En caso de usuario repetido se retorna falso porque no se ha hecho el add
	 */
	public boolean addUser(String userName, String password) {
		for(int i=0; i<listaUsers.size(); ++i){
			if (listaUsers.get(i).getUsername().equals(userName)) return false;
		}
		try {
			listaUsers.add(new Usuario(userName, password));
		} catch (Exception e) {
			return false;
		}
		ArrayList<String> l = new ArrayList<String>();
		l.add(userName);
		l.add(password);
		l.add("MT");		//codigo por defecto (montaña)
		l.add("1");			//num_hab por defecto
		if(!contCapaDatos.crearUsuario(l)) {
			return false;
		}
		return true;	
	}
	
	/**
	 * Dado un username se busca en la lista de usuarios y se elimina de ella
	 * @param userName Nombre del usuario que se quiere eliminar
	 * @param password Contraseña del usuario para comprobar que un usuario no
	 * borre otro que no sea de su propiedad
	 * @return Se devuelve cierto si hemos encontrado el usuario y lo hemos podido eliminar. En caso contrario retornamos false
	 */
	public boolean delUser(String userName, String password) {
		for(int i=0; i<listaUsers.size(); ++i){
			if (listaUsers.get(i).getUsername().equals(userName) && listaUsers.get(i).getPass().equals(password)){
				try {
					listaUsers.remove(i);
					if(!contCapaDatos.deleteUser(userName)) {
						return false;
					}
				} catch (Exception e) {
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Dado un userName se actualizan sus preferencias de numero de habitantes (numHab) y su codigo de toponimos (codTopo)
	 * @param userName
	 * @param numHab
	 * @param codTopo
	 * @return Devuelve true si la operación a tenido exito. En caso de no encontrar el usuario retornaremos false
	 */
	public boolean updatePreferences(String userName, Integer numHab, String codTopo){
		for(int i=0; i<listaUsers.size(); ++i){
			if (listaUsers.get(i).getUsername().equals(userName)) {
				listaUsers.get(i).setNumHab(numHab);
				listaUsers.get(i).setCodTop(codTopo);
				ArrayList<String> l = new ArrayList<String>();
				l.add(listaUsers.get(i).getCodTop());
				l.add(String.valueOf(listaUsers.get(i).getNumHab()));
				if(!contCapaDatos.updatePreferencesUser(userName, l)) {
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Dado un pais p se calcula la longitud de las fronteras/costas de p
	 * @param p
	 * @return La longitud del perimetro/s del pais p
	 */
	public ArrayList<String> getLongitudFronteras(String namePais) {
		Pais p = conjuntoPais.getPais(namePais);
		double longitud = 0;
		for(int i=0; i<p.getNContornos(); ++i) {
			Contorno cont = p.getContorno(i);
			ArrayList<Coordenada> listCoord = new ArrayList<Coordenada>(getCoordenadasAdyacentes(cont.getId()));
			if(listCoord.size()>0) {
				Quadtree q = new Quadtree(listCoord, 500);
				ArrayList<Coordenada> coordCont = new ArrayList<Coordenada>();
				ArrayList<ArrayList<String>> cc = new ArrayList<ArrayList<String>>(contCapaDatos.getContorno(String.valueOf(cont.getId())));
				for(int j=0; j<cc.size(); ++j) {
					coordCont.add(new Coordenada(Double.parseDouble(cc.get(j).get(1)), Double.parseDouble(cc.get(j).get(0))));
				}
				for(int j=0; j<coordCont.size(); ++j) {
					if(j<coordCont.size()-1) {
						if(q.contiene(coordCont.get(j)) && q.contiene(coordCont.get(j+1))) {
							longitud += coordCont.get(j).calcularDistancia(coordCont.get(j+1));
						}
					} else {
						if(q.contiene(coordCont.get(j)) && q.contiene(coordCont.get(0))) {
							longitud += coordCont.get(j).calcularDistancia(coordCont.get(0));
						}
					}
					
				}
			}
		}
		ArrayList<String> valorRes = new ArrayList<String>();
		valorRes.add(String.valueOf(longitud));
		return valorRes;
	}
	
	/**
	 * Metodo que dado un identificador obtiene las coordenadas de los contornos
	 * adyacentes al contorno con identificador igual a id de la capa de datos
	 * @param id Identificador del contorno del que se quieren obtener las coordendas
	 * @return Devuelve una lista con las coordenadas de los contornos adyacentes al
	 * contorno con identificador igual a id
	 */
	private ArrayList<Coordenada> getCoordenadasAdyacentes(int id) {
		ArrayList<Coordenada> res = new ArrayList<Coordenada>();
		for(int i=1; i<adyacencias.get(id).size(); ++i) {
			int idady = adyacencias.get(id).get(i);
			ArrayList<ArrayList<String>> aux = new ArrayList<ArrayList<String>>(contCapaDatos.getContorno(String.valueOf(idady)));
			for(int j=0; j<aux.size(); ++j) {
				res.add(new Coordenada(Double.parseDouble(aux.get(j).get(1)), Double.parseDouble(aux.get(j).get(0))));
			}
		}
		return res;
	}

	/**
	 * Dado un pais p se calcula la longitud del perimetro costero de p
	 * @param p
	 * @return La longitud de las costas del Pais p
	 */
	public ArrayList<String> getLongitudCostas(String namePais) {
		Pais p = conjuntoPais.getPais(namePais);
		double longitud = 0;
		for(int i=0; i<p.getNContornos(); ++i) {
			Contorno cont = p.getContorno(i);
			ArrayList<Coordenada> listCoord = new ArrayList<Coordenada>(getCoordenadasAdyacentes(cont.getId()));
			if(listCoord.size()>0) {
				Quadtree q = new Quadtree(listCoord, 500);
				ArrayList<Coordenada> coordCont = new ArrayList<Coordenada>();
				ArrayList<ArrayList<String>> cc = new ArrayList<ArrayList<String>>(contCapaDatos.getContorno(String.valueOf(cont.getId())));
				for(int j=0; j<cc.size(); ++j) {
					coordCont.add(new Coordenada(Double.parseDouble(cc.get(j).get(1)), Double.parseDouble(cc.get(j).get(0))));
				}
				for(int j=0; j<coordCont.size(); ++j) {
					if(j<coordCont.size()-1) {
						if(!q.contiene(coordCont.get(j)) || !q.contiene(coordCont.get(j+1))) {
							longitud += coordCont.get(j).calcularDistancia(coordCont.get(j+1));
						}
					} else {
						if(!q.contiene(coordCont.get(j)) || !q.contiene(coordCont.get(0))) {
							longitud += coordCont.get(j).calcularDistancia(coordCont.get(0));
						}
					}
					
				}
			}
		}
		ArrayList<String> valorRes = new ArrayList<String>();
		valorRes.add(String.valueOf(longitud));
		return valorRes;
	}
	
	/**
	 * Metodo que calcula las principales ciudades costeras y fronterizas de un pais
	 * @param namePais Nombre del Pais que se desea buscar las ciudades principales
	 * @param dist Distancia maxima de la costa/frontera que puede estar una ciudad
	 * costera/fronteriza
	 * @param distTopo Distancia maxima del toponimo con tipo igual al tipo de toponimo
	 * establecido en las preferencias del usuario del cual puede estar una ciudad para
	 * ser una ciudad principal
	 * @return Devuelve una lista con las ciudades principales de un pais
	 */
	private ArrayList<Ciudad> getCiudadesCostFront(String namePais, String dist, String distTopo) {
		Pais p = conjuntoPais.getPais(namePais);
		double km = Double.parseDouble(dist);
		double kmTopo = Double.parseDouble(distTopo);
		ArrayList<Ciudad> res = new ArrayList<Ciudad>();
		for(int i=0; i<p.getNContornos(); ++i) {
			Contorno c = new Contorno();
			c = p.getContorno(i);
			ArrayList<ArrayList<String>> saux = new ArrayList<ArrayList<String>>(contCapaDatos.getContorno(String.valueOf(c.getId())));
			ArrayList<Coordenada> coordCont = new ArrayList<Coordenada>();
			for(ArrayList<String> r : saux) {
				coordCont.add(new Coordenada(Double.parseDouble(r.get(1)), Double.parseDouble(r.get(0))));
			}
			c.setCoordenadas(coordCont);
			Quadtree q = new Quadtree(coordCont, 500);
			ArrayList<Coordenada> taux = new ArrayList<Coordenada>(); //coordenadas de toponimos
			ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
			conjuntoCuadricula.addContorno(c);
			ArrayList<Toponimo> lt = new ArrayList<Toponimo>(c.getCjtToponimos());
			for(int j=0; j<lt.size(); ++j) {
				if(lt.get(j).getTipoZona().equals(user.getCodTop())) {
					taux.add(lt.get(j).getCoordenada());
				}
			}
			//aqui ya tengo todas los toponimos
			for(int j=0; j<this.ciudades.size(); ++j) {
				if(c.puntoDentroContorno(this.ciudades.get(j).getCoordenada()) && this.ciudades.get(j).getPoblacion()>=user.getNumHab()) {
					ciudades.add(this.ciudades.get(j));
				}
			}
			//aqui ya tengo todas las ciudades del contorno con poblacion mayor que la definida por el usuario
			if(taux.size()>0) {
				Quadtree q2 = new Quadtree(taux, 500);
				for(int j=0; j<ciudades.size(); ++j) {
					if (km>q.getDistanciaMinima(ciudades.get(j).getCoordenada())) {
						if(kmTopo>q2.getDistanciaMinima(ciudades.get(j).getCoordenada())) {
							res.add(ciudades.get(j));
						}
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * Dado un pais p se determinan que ciudades de p son fronterizas o
	 * costeras, entendiendo que costera o fronteriza es estar a una distancia
	 * minima de la frontera/costa menor que km.
	 * @param p	Pais del que se quiere determinar las ciudades costeras/fronterizas
	 * @param km Distancia mínima de la costa/frontera a en la cual se considera que
	 * una ciudad es costera/fronteriza
	 * @return Se devuelve una lista con todas las ciudades costeras y fronterizas
	 * (que se encuentran a una distancia minima de la costa/frontera menor que km)
	 * del Pais p.
	 */
	public ArrayList<String> determinarCiudadesCostFront(String namePais, String dist, String distTopo) {
		ArrayList<Ciudad> res2 = new ArrayList<Ciudad>(getCiudadesCostFront(namePais, dist, distTopo));
		ArrayList<String> res = new ArrayList<String>();
		for(Ciudad c : res2) {
			res.add(c.getNombre());
		}
		return res;
	}
	
	/**
	 * Dado un nombre de un pais y una distancia maxima hasta la costa o frontera y una
	 * distancia maxima hasta el toponimo del tipo establecido en las preferencias del usuario
	 * con sesion activa, se calcula las ciudades principales costeras y fronterizas y
	 * se pintan en un mapa 
	 * @param namePais Nombre del Pais
	 * @param dist Distancia maxima hasta la costa o frontera
	 * @param distTopo Distancia maxima hasta el toponimo mas cercano del tipo establecido
	 * @return Se devuelve un JPanel con el mapa preparado para mostrar
	 */
	public JPanel determinarCiudadesCostFrontMapa(String namePais, String dist, String distTopo) {
		ArrayList<Ciudad> res = new ArrayList<Ciudad>(getCiudadesCostFront(namePais, dist, distTopo));
		/*Llegados a este punto ya tenemos en res todas las principales ciudades costeras
		 * y fronterizas, faltara pintarlas en el mapa
		 */
		Mapa m = new Mapa();
		Pais p = new Pais();
		p = conjuntoPais.getPais(namePais);
		for(int i=0; i<p.getNContornos(); ++i) {
			ArrayList<ArrayList<String>> coord = new ArrayList<ArrayList<String>>(contCapaDatos.getContorno(String.valueOf(p.getContorno(i).getId())));
			ArrayList<Coordenada> aux = new ArrayList<Coordenada>();
			for(ArrayList<String> y : coord) {
				aux.add(new Coordenada(Double.parseDouble(y.get(1)), Double.parseDouble(y.get(0))));
			}
			PreMapa pm = new PreMapa(aux, 0, "orange", p.getNombre(), false);
			m.addPreMapa(pm);
		}
		for(int i=0; i<res.size(); ++i) {
			ArrayList<Coordenada> coordRes = new ArrayList<Coordenada>();
			coordRes.add(res.get(i).getCoordenada());
			PreMapa mp = new PreMapa(coordRes, 2, "blue", res.get(i).getNombre(), true);
			m.addPreMapa(mp);
		}
		m.setProyeccion(1);
		return m.mostrarMapa();
	}
	
	/*
	 * Dado un pais y una ciudad y una distancia maxima se determinan los toponimos
	 * de tipo igual al codigo del usuario activo a una distancia maxima de dist de la
	 * la ciudad nameCiudad y se muestran por pantalla en un mapa
	 */
	public JPanel buscarToponimosCercanos(String namePais, String nameCiudad, String dist) {
		Pais p = conjuntoPais.getPais(namePais);
		Ciudad c = new Ciudad();
		Contorno cont = new Contorno();
		for(int i=0; i<p.getNContornos(); ++i) {
			cont = new Contorno();
			cont = p.getContorno(i);
			ArrayList<ArrayList<String>> aux = new ArrayList<ArrayList<String>>(contCapaDatos.getContorno(String.valueOf(cont.getId())));
			ArrayList<Coordenada> coordCont = new ArrayList<Coordenada>();
			for(ArrayList<String> y : aux) {
				coordCont.add(new Coordenada(Double.parseDouble(y.get(1)), Double.parseDouble(y.get(0))));
			}
			cont.setCoordenadas(coordCont);
			for(int j=0; j<this.ciudades.size(); ++j) {
				if(this.ciudades.get(j).getNombre().equals(nameCiudad) && cont.puntoDentroContorno(this.ciudades.get(j).getCoordenada())) {
					c = this.ciudades.get(j);
					j = this.ciudades.size();
					i = p.getNContornos();
				}
			}
		}
		//aqui en cont tengo el contorno al que pertenece la ciudad
		conjuntoCuadricula.addContorno(cont);
		//ahora deberia tener los toponimos metidos en el contorno
		ArrayList<Toponimo> lt = new ArrayList<Toponimo>(cont.getCjtToponimos());
		ArrayList<Coordenada> coordTopos = new ArrayList<Coordenada>();
		ArrayList<Toponimo> ltZona = new ArrayList<Toponimo>();
		for(Toponimo t : lt) {
			if(t.getTipoZona().equals(user.getCodTop())) {
				coordTopos.add(t.getCoordenada());
				ltZona.add(t);
			}
		}
		Quadtree q = new Quadtree(coordTopos, 500);
		coordTopos = new ArrayList<Coordenada>(q.puntosCercanos(c.getCoordenada(), Double.parseDouble(dist)));
		//en coordTopos tengo las coordenadas de los Toponimos cercanos a la ciudad c
		ArrayList<Toponimo> res = new ArrayList<Toponimo>();
		for(Toponimo y : ltZona) {
			if(coordTopos.contains(y.getCoordenada())) {
				res.add(y);
			}
		}
		/*Llegados a este punto res tenemos todos los toponimos y en coordTopos sus coordenadas
		 * a menos de dist distancia de un toponimo de tipo establecido en las preferencias
		 * del usuario a la ciudad, solo queda pasarlos al mapa y pintarlo*/
		Mapa m = new Mapa();
		m.setProyeccion(0);
		PreMapa pm = new PreMapa(cont.getCoordenadas(), 0, "orange", p.getNombre(), true);
		m.addPreMapa(pm);
		PreMapa mp = new PreMapa();	//añado los toponimos al mapa
		ArrayList<Coordenada> coordRes = new ArrayList<Coordenada>();
		for(int i=0; i<res.size(); ++i) {
			coordRes = new ArrayList<Coordenada>();
			coordRes.add(res.get(i).getCoordenada());
			mp = new PreMapa(coordRes, 2, "red", res.get(i).getNombre(), true);
			m.addPreMapa(mp);
		}
		coordRes = new ArrayList<Coordenada>();
		coordRes.add(c.getCoordenada());
		mp = new PreMapa(coordRes, 2, "blue", c.getNombre(), true);		//añado ciudad al mapa
		m.addPreMapa(mp);
		JPanel mapaRet = new JPanel();
		mapaRet = m.mostrarMapa();
		return mapaRet;
	}
	
	/**
	 * Dada una ciudad origen y una ciudad destino, si estas se encuentran en el mismo
	 * Contorno se calcula una ruta entre ciudades para llegar a de la ciudad origen a
	 * la ciudad destino y las pinta en un mapa
	 * @param origen Ciudad origen
	 * @param destino Ciudad destino
	 * @pre Las dos ciudades deben pertenecer al mismo contorno y existir
	 */
	public JPanel calcularRuta(String ciudadOrigen, String ciudadDestino, String namePais) {
		Pais p = conjuntoPais.getPais(namePais);
		Ciudad origen = new Ciudad();
		Ciudad destino = new Ciudad();
		Contorno local = new Contorno();
		ArrayList<Ciudad> ciudadesLocal = new ArrayList<Ciudad>();
		ArrayList<Coordenada> coordCiudades = new ArrayList<Coordenada>();
		for(int i=0; i<p.getNContornos(); ++i) {
			local = new Contorno();
			local.setId(p.getContorno(i).getId());
			ArrayList<ArrayList<String>> aux = new ArrayList<ArrayList<String>>(contCapaDatos.getContorno(String.valueOf(local.getId())));
			ArrayList<Coordenada> coordCont = new ArrayList<Coordenada>();
			for(ArrayList<String> y : aux) {
				coordCont.add(new Coordenada(Double.parseDouble(y.get(1)), Double.parseDouble(y.get(0))));
			}
			local.setCoordenadas(coordCont);
			int cont = 0;
			for(int j=0; j<this.ciudades.size(); ++j) {
				if(this.ciudades.get(j).getNombre().equals(ciudadOrigen) && local.puntoDentroContorno(this.ciudades.get(j).getCoordenada()) && cont<2) {
					origen = this.ciudades.get(j);
					++cont;
				} else if(this.ciudades.get(j).getNombre().equals(ciudadDestino) && local.puntoDentroContorno(this.ciudades.get(j).getCoordenada()) && cont<2) {
					destino = this.ciudades.get(j);
					++cont;
				} else if(local.puntoDentroContorno(this.ciudades.get(j).getCoordenada())) {
					ciudadesLocal.add(this.ciudades.get(j));
					coordCiudades.add(this.ciudades.get(j).getCoordenada());
				} else if(cont==2) {
					i = p.getNContornos();
				}
			}
		}
		//ahora en local tengo el contorno al que pertenecen las ciudades
		/*
		 * Llegados a este punto en local se tiene el contorno (id + coordenadas) donde estan las dos ciudades
		 * y en ciudadesLocal todas las ciudades del contorno local menos la ciudad origen y destino
		 */
		Mapa m = new Mapa();
		Quadtree q = new Quadtree(coordCiudades, 500);
		ArrayList<Coordenada> coordRuta = new ArrayList<Coordenada>(q.calcularRuta(origen.getCoordenada(), destino.getCoordenada()));
		if(coordRuta.size()<1 || (coordRuta.get(coordRuta.size()-1).getLatitud()!=destino.getCoordenada().getLatitud() && coordRuta.get(coordRuta.size()-1).getLongitud()!=destino.getCoordenada().getLongitud())) {
			PreMapa mp = new PreMapa(local.getCoordenadas(), 0, "orange", p.getNombre(), true);
			m.addPreMapa(mp);	//añado coordenadas contorno
			ArrayList<Coordenada> aux = new ArrayList<Coordenada>();
			aux.add(origen.getCoordenada());
			aux.add(destino.getCoordenada());
			mp = new PreMapa(aux, 1, "blue", "No se ha encontrado una ruta", true);
		} else {
			ArrayList<Ciudad> res = new ArrayList<Ciudad>();
			for(int i=0; i<coordCiudades.size(); ++i) {
				if(coordRuta.contains(coordCiudades.get(i))) {
					res.add(ciudadesLocal.get(i));
				}
			}
			/*Llegados a este punto se tienen en res todas las ciudades por las que 
			 * se pasan
			 */
			PreMapa mp = new PreMapa(local.getCoordenadas(), 0, "orange", p.getNombre(), true);
			m.addPreMapa(mp);	//añado coordenadas contorno
			for(Ciudad y : res) {
				ArrayList<Coordenada> aux = new ArrayList<Coordenada>();
				aux.add(y.getCoordenada());
				mp = new PreMapa(aux, 2, "red", y.getNombre(), true);
				m.addPreMapa(mp);
			}
			//añadidas las coordenadas de las ciudades
			mp = new PreMapa(coordRuta, 1, "blue", null, false);	//añado la ruta como si fuera un rio
			m.addPreMapa(mp);
		}
		m.setProyeccion(1);
		return m.mostrarMapa();
	}
	
	/**
	 * Funcion que dado el nombre de un pais devuelve una lista con los nombres
	 * de todas las ciudades del pais con nombre namePais
	 * @param namePais Nombre del Pais
	 * @return Devuelve una lista con los nombres de las ciudades del Pais
	 * namePais
	 */
	public ArrayList<String> consultarCiudades(String namePais) {
		ArrayList<String> res = new ArrayList<String>();
		Pais p = new Pais();
		p = conjuntoPais.getPais(namePais);
		for(int i=0; i<p.getNContornos(); ++i) {
			ArrayList<ArrayList<String>> coordCont = contCapaDatos.getContorno(String.valueOf(p.getContorno(i).getId()));
			ArrayList<Coordenada> lc = new ArrayList<Coordenada>();
			for(ArrayList<String> aux : coordCont) {
				lc.add(new Coordenada(Double.parseDouble(aux.get(1)), Double.parseDouble(aux.get(0))));
			}
			Contorno c = new Contorno(); 
			c.setId(p.getContorno(i).getId());
			c.setCoordenadas(lc);
			for(Ciudad y : this.ciudades) {
				if(c.puntoDentroContorno(y.getCoordenada())) {
					res.add(y.getNombre());
				}
			}
		}
		return res;
	}
	
	/**
	 * Funcion que devuelve la lista de todos los paises del mundo
	 * @return Devuelve todos los paises del mundo en una lista
	 */
	public ArrayList<String> getListPais() {
		ArrayList<String> listPais = new ArrayList<String>();
		ArrayList<Pais> aux = new ArrayList<Pais>(conjuntoPais.getPaises());
		for(Pais y : aux) {
			listPais.add(y.getNombre());
		}
		return listPais;
	}
	
	/**
	 * Devuelve una lista con los codigo de toponimos que se usan en nuestro
	 * programa
	 * @return Devuelve una lista con los codigo de toponimos que se usan en nuestro
	 * programa
	 */
	public ArrayList<String> getTopoCodes() {
		return contCapaDatos.getTopoCodes();
	}
	
	public ArrayList<String> getFeatureCodes() {
		return contCapaDatos.getFeatureCodes();
	}
	
	public ArrayList<String> getPaises() {
		ArrayList<String> res = new ArrayList<String>();
		for(Pais p : conjuntoPais.getPaises()) {
			res.add(p.getNombre());
		}
		return res;
	}
}