
/**
 * Esta clase es la clase inicial al arranca la aplicación y su tarea es la de instanciar el controlador de Vistas e iniciar la presentacion grafica
 * @author Oscar Iglesias
 * @version 1
 * @cluster 6
 * @grupo 2
 */
public class Main {
	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater (
			new Runnable() {
				public void run() {
					System.out.println("Aplicación arrancada");
					ControllerViews ctrlViews = new ControllerViews();
					System.out.println("Inicializamos el ControllerViews");
					ctrlViews.inicializarPresentacion();
					System.out.println("Toma el control el controlador de las vistas");
				}
			}
		); 
	}
}