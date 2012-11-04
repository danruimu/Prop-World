import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controlador de la Capa de Datos del Programa, tiene las siguientes funcionalidades:<br>
 * - Crear, modificar y leer el fichero de Users<br>
 * - Leer el fichero de Toponimos<br>
 * - Leer el fichero de Ciudades<br>
 * - Leer el fichero de Fronteras<br>
 * @author Daniel Ruiz Muñoz
 * @version 1.5
 * @Cluster 6
 * @Grupo 2
 *
 */
public class ControllerCapaDatos {
	
	/**
	 * Lee el fichero de Toponimos y lo devuelve en forma de matriz de strings con
	 * el siguiente formato: <nombre, lat, long, tipoZona, [poblacion]>
	 * @return Devuelve una matriz de strings donde cada linea de la matriz contiene
	 * los campos necesarios para la inicializacion de un Toponimo
	 */
	public ArrayList<ArrayList<String>> readConjuntoToponimo() {
		ArrayList<ArrayList<String>> toponimos = new ArrayList<ArrayList<String>>();
		File f = new File("./Toponims.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner s = new Scanner(br);
		s.nextLine(); //nos saltamos la linea con el significado de cada columna
		while(s.hasNextLine()) {
			ArrayList<String> aux = new ArrayList<String>();
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s* \\s*");
			aux.add(sl.next());		//añado nombreUTF
			sl.next();				//desecho nombreASCII
			sl.next();				//desecho nombres alternativos
			aux.add(sl.next());		//añado latitud
			aux.add(sl.next());		//añado longitud
			aux.add(sl.next());		//añado codigo toponimo
			String pob = sl.next();
			if(!pob.equals("0")) {
				aux.add(pob);		//Si poblacion diferente de cero añado poblacion
			}
			toponimos.add(aux);
		}
		try {
			s.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toponimos;
	}
	
	/**
	 * Lee el fichero de Fronteras y crea una matriz con los datos ordenados de
	 * la siguiente forma:<br>
	 * - Para cada zona primero viene una linea con el nombre del Pais al que
	 * pertenece la zona y el identificador de la zona<br>
	 * - Seguido vienen todas las coordenadas de la zona en lineas separadas<br>
	 * - Por ultimo despues de la ultima coordenada de la zona se añade una linea
	 * con la palabra NULL para saber que no hay mas coordendas de esa zona
	 * @return Devuelve una matriz de strings con los datos necesarios para inicializar
	 * contornos y paises con la estructura anteriormente descrita
	 */
	public ArrayList<ArrayList<String>> readFronteras() {
		ArrayList<ArrayList<String>> fronteras = new ArrayList<ArrayList<String>>();
		boolean primero = true;
		File f = new File("./Fronteres.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner s = new Scanner(br);
		String nombre = "null";
		String id = "null";
		String nuevaid = "null";
		String latitud = null;
		String longitud = null;
		s.nextLine();
		while(s.hasNextLine()) {
			ArrayList<String> aux = new ArrayList<String>();
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s* \\s*");
			nuevaid = sl.next();
			if(id.equals(nuevaid)) {
				latitud = sl.next();	//cojo latitud
				longitud = sl.next();	//cojo longitud
				aux.add(latitud);
				aux.add(longitud);
				sl.next();				//desecho codigo nombre
				sl.next();				//desecho nombre
			} else {
				id = nuevaid;			//cojo id
				latitud = sl.next();	//cojo latitud
				longitud = sl.next();	//cojo longitud
				sl.next();				//desecho codigo pais
				nombre = sl.next();		//cojo nombre pais
				if(primero) {
					primero = false;
				} else {
					aux.add("NULL");
					fronteras.add(aux);		//añado una linea NULL porque cambio de zona
				}
				aux = new ArrayList<String>();
				aux.add(nombre);
				aux.add(id);
				fronteras.add(aux);		//añado una linea con nombre pais y id de la nueva zona
				aux = new ArrayList<String>();
				aux.add(latitud);
				aux.add(longitud);
			}
			fronteras.add(aux);			//añado coordenadas de la zona a la matriz
		}
		try {
			s.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> aux = new ArrayList<String>();
		aux.add("NULL");
		fronteras.add(aux);
		return fronteras;
	}
	
	/**
	 * Devuelve los usuarios guardados en disco con el siguiente formato:
	 * <
	 * @return Devuelve una lista con todos los usuarios guardados en disco
	 */
	public ArrayList<ArrayList<String>> readUsers() {
		ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();
		File f = new File("./Users.txt");
		if(!f.exists()) {	//si el fichero no existe se devuelve una lista vacia 
			return users;
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner s = new Scanner(br);
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s* \\s*");
			ArrayList<String> aux = new ArrayList<String>();
			while(sl.hasNext()) {
				aux.add(sl.next());
			}
			users.add(aux);
		}
		try {
			s.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Añade al usuario contenido en la lista l (nombre, pass, preferencias)
	 * al fichero de usuarios del programa.
	 * @param l Lista con los datos del usuario <nombre, pass, [codigo, num_hab]>
	 * @return Devuelve cierto si el usuario se ha añadido correctamente y cierto
	 * en caso contrario
	 */
	public boolean crearUsuario(ArrayList<String> l) {
		ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();
		File f = new File("./Users.txt");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				return false;
			}
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			return false;
		}
		Scanner s = new Scanner(br);
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			ArrayList<String> line = new ArrayList<String>();
			while(sl.hasNext()) {
				line.add(sl.next());
				if(line.get(0).equals(l.get(0))) {		//usuario ya existe
					return false;
				}
			}
			users.add(line);
		}
		users.add(l);	//añado el nuevo usuario a la lista de usuario
		try {
			s.close();
			br.close();
		} catch (Exception e) {
			return false;
		}
		/*Ahora solo queda volver a generar el fichero de usuarios*/
		try {
			f.delete();
			f.createNewFile();		//eliminamos y creamos el fichero de nuevo
		} catch (Exception e) {
			return false;
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
		} catch (Exception e) {
			return false;
		}
		for(int i=0; i<users.size(); ++i) {
			for(int j=0; j<users.get(i).size(); ++j) {
				try {
					bw.write(users.get(i).get(j)+" ");
				} catch (Exception e) {
					return false;
				}
			}
			try {
				bw.newLine();
			} catch (Exception e) {
				return false;
			}
		}
		try {
			bw.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Elimina al usuario nombreUser del fichero de usuarios guardado en disco
	 * @param nombreUser Nombre del User a eliminar
	 * @return Devuelve cierto si el usuario se ha eliminado correctamente
	 * y falso en caso contrario
	 */
	public boolean deleteUser(String nombreUser) {
		ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();
		File f = new File("./Users.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			return false;
		}
		Scanner s = new Scanner(br);
		boolean exist = false;
		ArrayList<String> deline = new ArrayList<String>();
		while(s.hasNextLine()) {
			ArrayList<String> line = new ArrayList<String>();
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s* \\s*");
			while(sl.hasNext()) {
				line.add(sl.next());
			}
			if(line.get(0).equals(nombreUser)) {
				exist = true;
				deline = new ArrayList<String>(line);
			}
			users.add(line);
		}
		s.close();
		if(!exist) return false;	//no existe el usuario a eliminar
		else {						//user existe, lo borramos
			users.remove(deline);
		}
		try {
			br.close();				//cerramos el buffer de lectura
		} catch (Exception e) {
			return false;
		}
		/*Ahora solo falta volver a generar el fichero de usuarios*/
		f.delete();	//eliminamos el fichero
		try {
			f.createNewFile();		//lo creamos de nuevo
		} catch (Exception e) {
			return false;
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
		} catch (Exception e) {
			return false;
		}
		for(int i=0; i<users.size(); ++i) {			//escribimos de nuevo el fichero
			for(int j=0; j<users.get(i).size(); ++j) {
				try {
					bw.write(users.get(i).get(j)+" ");
				} catch (Exception e) {
					return false;
				}
			}
			if(i!=users.size()-1) {
				try {
					bw.newLine();
				} catch (Exception e) {
					return false;
				}
			}
		}
		try {
			bw.close();
			br.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Asigna las preferencias o sobreescribre las preferencias del usuario userName con
	 * las preferencias de la lista l
	 * @param userName Nombre del usuario del que se quieren cambiar las preferencias
	 * @param l Lista con las preferencias <cod_top, num_hab>
	 * @return Devuelve cierto si se han cambiado o asignado correctamente las preferencias
	 * de la lista l al usuario userName, en caso contrario devuelve falso
	 */
	public boolean updatePreferencesUser(String userName, ArrayList<String> l) {
		File f = new File("./Users.txt");
		/*No hace falta mirar que el fichero exista ya que si se llama a esta funcion es porque
		 * el usuario existia, entonces, el fichero seguro que existe
		 */
		ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			return false;
		}
		Scanner s = new Scanner(br);
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s* \\s*");
			ArrayList<String> aux = new ArrayList<String>();
			while(sl.hasNext()) {
				aux.add(sl.next());
			}
			users.add(aux);
		}
		try {
			s.close();
		} catch (Exception e) {
			return false;
		}
		for(int i=0; i<users.size(); ++i) {
			if(users.get(i).get(0).equals(userName)) {
				if(users.get(i).size()==4) {
					users.get(i).remove(2);
					users.get(i).remove(2);
				}
				users.get(i).addAll(l);
				i = users.size();
			}
		}
		/*Llegados a este punto ya se ha actualizado el usuario y solo falta escribir
		 * otra vez todos los usuarios en disco*/
		f.delete();	//eliminamos el fichero
		try {
			f.createNewFile();		//lo creamos de nuevo
		} catch (Exception e) {
			return false;
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
		} catch (Exception e) {
			return false;
		}
		for(int i=0; i<users.size(); ++i) {			//escribimos de nuevo el fichero
			for(int j=0; j<users.get(i).size(); ++j) {
				try {
					bw.write(users.get(i).get(j)+" ");
				} catch (Exception e) {
					return false;
				}
			}
			if(i!=users.size()-1) {
				try {
					bw.newLine();
				} catch (Exception e) {
					return false;
				}
			}
		}
		try {
			bw.close();
			br.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Comprueba que el  fichero de adyacencias existe
	 * @return Devuelve cierto si el fichero de adyacencias existe, falso
	 * en caso contrario
	 */
	public boolean existsFileAdyacencias() {
		File f = new File("./Adyacencias.txt");
		return f.exists();
	}
	
	/**
	 * Lee el fichero de adyacencias y devuelve el contenido del fichero
	 * @return Devuelve el contenido del fichero de adyacencias
	 */
	public ArrayList<ArrayList<Integer>> readFileAdyacencias() {
		ArrayList<ArrayList<Integer>> adyacencias = new ArrayList<ArrayList<Integer>>();
		File f = new File("./Adyacencias.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner s = new Scanner(br);
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s* \\s*");
			ArrayList<Integer> aux = new ArrayList<Integer>();
			while(sl.hasNext()) {
				aux.add(Integer.parseInt(sl.next()));
			}
			adyacencias.add(aux);
		}
		try {
			s.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adyacencias;
	}
	
	/**
	 * Metodo que escribe el el contenido del grafo de adyacencias en el fichero
	 * llamado Adyacencias.txt
	 * @param adyacencias Grafo de adyacencias en forma de matriz
	 * @return Devuelve cierto si la escritura se ha realizado correctamente y
	 * falso en caso de error
	 */
	public boolean writeFileAdyacencias(ArrayList<ArrayList<String>> adyacencias) {
		File f = new File("./Adyacencias.txt");
		try {
			f.createNewFile();
		} catch (Exception e) {
			return false;
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
		} catch (Exception e) {
			return false;
		}
		for(int i=0; i<adyacencias.size(); ++i) {
			for(int j=0; j<adyacencias.get(i).size(); ++j) {
				try {
					bw.write(adyacencias.get(i).get(j)+" ");
				} catch (Exception e) {
					return false;
				}
			}
			try {
				bw.write("\n");
			} catch (Exception e) {
				return false;
			}
		}
		try {
			bw.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Funcion que lee el fichero de fronteras y devuelve las coordendas del
	 * contorno con identificador igual a id 
	 * @param id Identificador del contorno del que se quieren obtener las
	 * coordenadas
	 * @return Devuelve las coordendas (una por linea en formato <latitud,longitud>)
	 * del contorno con identificador igual a id
	 */
	public ArrayList<ArrayList<String>> getContorno(String id) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		File f = new File("./Fronteres.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner s = new Scanner(br);
		boolean encontrado = false;
		s.nextLine();	//nos saltamos la linea con la descripcion de las columnas
		while(!encontrado) {
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s* \\s*");
			while(s.hasNextLine() && sl.next().equals(id)) {
				encontrado = true;
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(sl.next());	//obtengo latitud
				aux.add(sl.next());	//obtengo longitud
				res.add(aux);
				linea = s.nextLine();
				sl = new Scanner(linea);
				sl.useDelimiter("\\s* \\s*");
			}
		}
		try {
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Metodo que devuelve una lista con los codigos de los toponimos, sin contar
	 * ciudades, que tenemos en nuestro fichero de toponimos
	 * @return Devuelve una lista con los codigo de toponimos de nuestro
	 * fichero de toponimos, sin incluir los codigos de ciudades
	 */
	public ArrayList<String> getTopoCodes() {
		ArrayList<String> res = new ArrayList<String>();
		File f = new File("./FeatureCodes.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		int cont = 0;
		Scanner s = new Scanner(br);
		while(s.hasNextLine() && cont<6) {
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\t");
			String codigo = "NULL";
			if(sl.hasNext()) {
				codigo = new String(sl.next());
			}
			if(!codigo.equals("NULL")) {
				if(codigo.equals("AIRP")) {
					res.add(codigo);
					++cont;
				} else if(codigo.equals("HLL")) {
					res.add(codigo);
					++cont;
				} else if(codigo.equals("LK")) {
					res.add(codigo);
					++cont;
				} else if(codigo.equals("MTS")) {
					res.add(codigo);
					++cont;
				} else if(codigo.equals("MT")) {
					res.add(codigo);
					++cont;
				} else if(codigo.equals("PK")) {
					res.add(codigo);
					++cont;
				}
			}
		}
		try {
			s.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Devuelve las descripcion de los toponimos utilizados
	 * @return Devuelve una lista con las descripciones de cada uno
	 * de los toponimos utilizados
	 */
	public ArrayList<String> getFeatureCodes() {
		ArrayList<String> res = new ArrayList<String>();
		File f = new File("./FeatureCodes.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		int cont = 0;
		Scanner s = new Scanner(br);
		while(s.hasNextLine() && cont<6) {
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\t");
			String codigo = "NULL";
			if(sl.hasNext()) {
				codigo = new String(sl.next());
			}
			if(!codigo.equals("NULL")) {
				if(codigo.equals("AIRP")) {
					res.add(linea);
					++cont;
				} else if(codigo.equals("HLL")) {
					res.add(linea);
					++cont;
				} else if(codigo.equals("LK")) {
					res.add(linea);
					++cont;
				} else if(codigo.equals("MTS")) {
					res.add(linea);
					++cont;
				} else if(codigo.equals("MT")) {
					res.add(linea);
					++cont;
				} else if(codigo.equals("PK")) {
					res.add(linea);
					++cont;
				}
			}
		}
		try {
			s.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}