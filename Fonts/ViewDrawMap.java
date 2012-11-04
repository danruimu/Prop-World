import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class ViewDrawMap extends JDialog {

	private ControllerViews ctrlViews;
	//private JPanel			contentMapa;

	/**
	 * Create the dialog.
	 * @param controllerViews 
	 */
	public ViewDrawMap(ControllerViews controllerViews) {
		ctrlViews = controllerViews;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				ctrlViews.sincViewDrawMapToViewOpciones();
			}
		});
		setTitle("Mapa");
		setBounds(100, 100, 970, 705);
		getContentPane().setLayout(null);
		
		/*contentMapa = new JPanel();
		contentMapa.setBounds(6, 6, 438, 266);
		getContentPane().add(contentMapa);*/
	}
	
	public void hacerVisible(JPanel jPanel){
		//contentMapa = new JPanel();
		if(getContentPane().getComponentCount()>0){
			getContentPane().remove(0);
		}
		getContentPane().add(jPanel);
		//contentMapa = jPanel;
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
}
