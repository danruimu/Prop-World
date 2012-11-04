/**
 * Esta clase representa cada uno de los usuarios de la aplicación.
 * @author Fernando Pocino Pasías
 * @Cluster 6
 * @Grupo 2
 * @version 1.0
 */
public class Usuario {
//Atributos...
	/**
	 * Identifica al usuario.
	 */
	private String username;
	
	/**
	 * Contraseña del usuario.
	 */
	private String password;
	
	/**
	 * Preferencias del usuario.
	 */
	private Preferencias pref;

//Operaciones
	/**
	 * Crea un usuario vacío.
	 */
	public Usuario() {
		username = new String();
		password = new String();
		pref = new Preferencias();
	}

	/**
	 * Crea un usuario con nombre de usuario user y contraseña pswd.
	 */
	public Usuario(String user,String pswd) {
		username = user;
		password = pswd;
		pref = new Preferencias();
	}

	/**
	 * Devuelve la contraseña del usuario.
	 */
	public String getPass() {
		return password;
	}

	/**
	 * Devuelve el nombre de usuario.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Cambia la contraseña del usuario.
	 */
	public void changePass(String pswd) {
		password = pswd;
	}

	/**
	 * Crea una preferencia con un número mínimo de habitantes de numhab.
	 */
	public void setNumHab(int numhab) {
		pref.setNumHabitantes(numhab);
	}

	/**
	 * Crea una preferencia con código  de topónimo top.
	 */
	public void setCodTop(String top) {
		pref.setCodigo(top);
	}

	/**
	 * Devuelve su preferencia de número mínimo de habitantes.
	 */
	public int getNumHab() {
		return pref.getNumHabitantes();
	}

	/**
	 * Devuelve su preferencia de topónimo "principal".
	 */
	public String getCodTop() {
		return pref.getCodigo();
	}
}
