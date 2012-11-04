import java.util.ArrayList;


/**
 * Este controlador se encarga de controlar las vistas y comunicarse con el controlador de la capa de dominio
 * @author Oscar Iglesias
 * @version 1
 * @cluster 6
 * @grupo 2
 */
public class ControllerViews {
	private ControllerDomain 		ctrlDomain;
	private ViewMenuPrincipal 		viewMenuPrincipal 		= null;
	private ViewAddUser 			viewAddUser 			= null;
	private ViewDelUser				viewDelUser				= null;
	private ViewPreferencias		viewPreferencias		= null;
	private ViewRecuperarPass 		viewRecuperarPass 		= null;
	private ViewOpciones			viewOpciones 			= null;
	private ViewSelecPais			viewSelecPais			= null;
	private ViewDrawMap				viewDrawMap				= null;
	private ViewCalcularRuta		viewCalcularRuta		= null;
	private ViewCalcularPuntos		viewCalcularPuntos		= null;
	private ViewResult				viewResult				= null;
	private ViewListarPrincipales	viewListarPrincipales	= null;
	
	// Constructor y metodos de inicializacion
	public ControllerViews() {
	    ctrlDomain = new ControllerDomain();	//Hace petar el programa por exceso de tiempo
	    if (viewMenuPrincipal == null)
	      viewMenuPrincipal = new ViewMenuPrincipal(this);
	}
	
	public void inicializarPresentacion() {
		//TODO: Lanzar una ventana de cargando mientras inicializa el controllerDomain
	    ctrlDomain.initControllerDomain();
		viewMenuPrincipal.hacerVisible();
	}
	
	// Metodos de sincronizacion entre vistas
	public void sincViewMenuPrincipalToViewAddUser() {
		viewMenuPrincipal.desactivar();
	    if (viewAddUser == null)
	    	viewAddUser = new ViewAddUser(this);
	    viewAddUser.hacerVisible();
	}
	
	public void sincViewAddUserToViewMenuPrincipal() {
	    // Se hace invisible la vista secundaria (podria anularse)
		viewAddUser.hacerInvisible();
		viewMenuPrincipal.activar();
	}
	
	public void sincViewAddUserToViewPreferencias(String username) {
		viewAddUser.hacerInvisible();
		if(viewPreferencias == null)
			viewPreferencias = new ViewPreferencias(this);
		viewPreferencias.hacerVisible(username, true);
	}
	
	public void sincViewMenuPrincipalToViewDelUser() {
		viewMenuPrincipal.desactivar();
		if(viewDelUser == null)
			viewDelUser = new ViewDelUser(this);
		viewDelUser.hacerVisible();
	}
	
	public void sincViewDelUserToViewMenuPrincipal() {
		viewDelUser.hacerInvisible();
		viewMenuPrincipal.activar();
	}
	
	public void sincViewPreferenciasTo(boolean b) {
		viewPreferencias.hacerInvisible();
		// Si b es cierto hay que volver al menu principal, si es falso hay que ir al panel de opciones. 
		if(b)	viewMenuPrincipal.activar();
		else{
			if(viewOpciones == null)
				viewOpciones = new ViewOpciones(this);
			viewOpciones.hacerVisible();
		}
	}
	
	public void sincViewMenuPrincipalToViewRecuperarPass(){
		viewMenuPrincipal.desactivar();
		if(viewRecuperarPass == null)
			viewRecuperarPass = new ViewRecuperarPass(this);
		viewRecuperarPass.hacerVisible();
	}
	
	public void sincViewRecuperarPassToViewMenuPrincipal(){
		viewRecuperarPass.hacerInvisible();
		viewMenuPrincipal.activar();
	}
	
	public void sincViewMenuPrincipalToViewOpciones(){
		viewMenuPrincipal.desactivar();
		if(viewOpciones == null)
			viewOpciones = new ViewOpciones(this);
		viewOpciones.hacerVisible();
	}
	
	public void sincViewOpcionesToViewMenuPrincipal(){
		viewOpciones.hacerInvisible();
		viewMenuPrincipal.activar();
	}
	
	public void sincViewOpcionesToViewPreferencias(String username){
		viewOpciones.hacerInvisible();
		if(viewPreferencias == null)
			viewPreferencias = new ViewPreferencias(this);
		viewPreferencias.hacerVisible(username, false);
	}
	
	public void sincViewOpcionesToViewSelecPais(int opt){
		viewOpciones.hacerInvisible();
		if(viewSelecPais == null)
			viewSelecPais = new ViewSelecPais(this);
		System.out.println("Call viewSelectPais con opt: '" + opt + "'");
		viewSelecPais.hacerVisible(opt);
	}
	
	public void sincViewSelectPaisToViewOpciones(){
		viewSelecPais.hacerInvisible();
		if(viewOpciones == null)
			viewOpciones = new ViewOpciones(this);
		viewOpciones.hacerVisible();
	}
	
	public void sincViewListarPrincipalesToViewDrawMap(String nombrePais, String dist, String distTopo){
		viewListarPrincipales.hacerInvisible();
		if(viewDrawMap == null)
			viewDrawMap = new ViewDrawMap(this);
		viewDrawMap.hacerVisible(ctrlDomain.determinarCiudadesCostFrontMapa(nombrePais, dist, distTopo));
	}
	
	public void sincViewOpcionesToViewCalcularRuta(){
		viewOpciones.hacerInvisible();
		if(viewCalcularRuta == null)
			viewCalcularRuta = new ViewCalcularRuta(this);
		viewCalcularRuta.hacerVisible();
	}
	
	public void sincViewOpcionesToViewCalcularPuntos(){
		viewOpciones.hacerInvisible();
		if(viewCalcularPuntos == null)
			viewCalcularPuntos = new ViewCalcularPuntos(this);
		viewCalcularPuntos.hacerVisible();
	}
	
	public void sincViewCalcularPuntosToViewDrawMap(String pais, String ciudad, String distancia){
		viewCalcularPuntos.hacerInvisible();
		//if(viewDrawMap == null)
			viewDrawMap = new ViewDrawMap(this);
		viewDrawMap.hacerVisible(ctrlDomain.buscarToponimosCercanos(pais, ciudad, distancia));
	}
	
	/**
	 * Oculta la vista de pintar mapa y muestra la vista de opciones.
	 */
	public void sincViewDrawMapToViewOpciones(){
		viewDrawMap.hacerInvisible();
		if(viewOpciones == null)
			viewOpciones = new ViewOpciones(this);
		viewOpciones.hacerVisible();
	}
	
	/**
	 * Oculta la vista de calcular puntos y muestra la vista de opciones.
	 */
	public void sincViewCalcularPuntosToViewOpciones(){
		viewCalcularPuntos.hacerInvisible();
		if(viewOpciones == null)
			viewOpciones = new ViewOpciones(this);
		viewOpciones.hacerVisible();
	}
	
	/**
	 * Este metodo oculta la vista de calcular ruta, solicita a la capa de dominio la ruta y muestra la ruta en la vista de dibujar mapa
	 * @param pais Es el nombre del pais donde se quiere calcular la ruta
	 * @param origen Es el nombre de la ciudad origen de la ruta
	 * @param destino Es el nombre de la ciudad destino de la ruta
	 */
	public void sincViewCalcularRutaToViewDrawMap(String pais, String origen, String destino){
		viewCalcularRuta.hacerInvisible();
		//if(viewDrawMap == null)
			viewDrawMap = new ViewDrawMap(this);
		viewDrawMap.hacerVisible(ctrlDomain.calcularRuta(origen, destino, pais));
	}
	
	/**
	 * Oculta la vista de calcular ruta y muestra la vista de opciones
	 */
	public void sincViewCalcularRutaToViewOpciones() {
		viewCalcularRuta.hacerInvisible();
		if(viewOpciones == null)
			viewOpciones = new ViewOpciones(this);
		viewOpciones.hacerVisible();		
	}
	
	/**
	 * 
	 * @param selectedPais
	 * @param opt
	 */
	public void sincViewSelecPaisToViewResult(String selectedPais, int opt) {
		viewSelecPais.hacerInvisible();
		//if(viewResult == null)
			viewResult = new ViewResult(this);
		
		switch(opt){
			case 1: // Calcular Fronteras
				System.out.println("Calculamos y pintamos la longitud de las fronteras de '" + selectedPais + "' con opt: '" + opt + "'");
				viewResult.hacerVisible(ctrlDomain.getLongitudFronteras(selectedPais), opt);
				break;
				
			case 2: // Calcular Costas
				System.out.println("Calculamos y pintamos la longitud de las costas de '" + selectedPais + "' con opt: '" + opt + "'");
				ArrayList<String> list = ctrlDomain.getLongitudCostas(selectedPais);
				System.out.println("Longitud es '" + list.get(0) + "'");
				viewResult.hacerVisible(list, opt);
				break;
				
			default:
				System.out.println("No hay opcion valida (" + opt + ")");
				viewOpciones.hacerVisible();
				break;
		}
	}
	
	public void sincViewResultToViewOpciones() {
		viewResult.hacerInvisible();
		if(viewOpciones == null)
			viewOpciones = new ViewOpciones(this);
		viewOpciones.hacerVisible();
	}
	
	public void sincViewOpcionesToViewListarPrincipales(int opt) {
		viewOpciones.hacerInvisible();
		if(viewListarPrincipales == null)
			viewListarPrincipales = new ViewListarPrincipales(this);
		System.out.println("Mostramos la vista de listar principales");
		viewListarPrincipales.hacerVisible(opt);
	}
	
	public void sincViewListarPrincipalesToViewResult(String selectedPais, String dist, String distTopo) {
		viewListarPrincipales.hacerInvisible();
		viewResult = new ViewResult(this);
		ArrayList<String> list = ctrlDomain.determinarCiudadesCostFront(selectedPais, dist, distTopo);
		viewResult.hacerVisible(list, 3);
		
	}
	
	public void sincViewListarPrincipalesToViewOpciones() {
		viewListarPrincipales.hacerInvisible();
		if(viewOpciones == null)
			viewOpciones = new ViewOpciones(this);
		viewOpciones.hacerVisible();
	}
	
	// Metodos de comunicacion entre vistas y controlador de dominio
	public boolean autenticarUser(String username, String password){
		return ctrlDomain.iniciarSesion(username, password);
	}
	
	public String recuperarPassword(String username){
		//System.out.println("Usuario: '" + username + "'");
		return ctrlDomain.getPass(username);
	}
	
	public boolean addNewUser(String username, String password){
		//System.out.println("Usuario: '" + username + "' Password: '" + password + "'");
		return ctrlDomain.addUser(username, password);
	}
	
	public boolean updatePreferences(String username, Integer numHab, String codTopo){
		//System.out.println("Preferencias actualizadas del usuario: '" + username + "', numHab: '" + numHab + "' y codTopo: '" + codTopo + "'");
		return ctrlDomain.updatePreferences(username, numHab, codTopo);
	}
	
	public boolean delUser(String username, String password){
		return ctrlDomain.delUser(username, password);
	}
	
	public String getUserOnSession(){
		return ctrlDomain.getUserOnSession();
	}
	
	public Integer getUserNumHab(){
		//TODO: Que pasa si el usuario es nuevo y no esta como usuario en sesion?
		return ctrlDomain.getUserNumHab();
	}
	
	public String getUserCodTopo(){
		//TODO: Que pasa si el usuario es nuevo y no esta como usuario en sesion?
		return ctrlDomain.getUserCodTopo();
	}
	
	/**
	 * Dado un pais se le pide a la capa de dominio que busque y devuelva la lista de ciudades del pais dado
	 * @param pais Es el nombre del pais del cual queremos recuperar todas sus ciudades
	 * @return Se devuleve una lista de las ciudades del pais dado
	 */
	public ArrayList<String> getCiudadesOfPais(String pais){
		System.out.println("hago el call al controlador de dominio para que me devuelva las ciudades del pais: " + pais);
		ArrayList<String> list = ctrlDomain.consultarCiudades(pais);
		System.out.println("Ya tengo la lista del domain");
		return list;
	}
	
	public ArrayList<String> getPaises(){
		return ctrlDomain.getPaises();
	}
}
