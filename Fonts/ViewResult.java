import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import java.awt.TextArea;


public class ViewResult extends JDialog {
	
	private ControllerViews ctrlViews;
	private JLabel labelResultado;
	private JTextArea resultado;
	private JLabel imagen;
	
	private boolean ciudprinc;
	private TextArea textAreaResultado;

	/**
	 * Create the dialog.
	 */
	public ViewResult(ControllerViews controllerViews) {
		setTitle("Resultados");
		ctrlViews = controllerViews;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				ctrlViews.sincViewResultToViewOpciones();
			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		labelResultado = new JLabel("El resultado es:");
		labelResultado.setBounds(10, 101, 438, 16);
		getContentPane().add(labelResultado);
		
		/*resultado = new JTextArea();
		textAreaResultado.setLineWrap(true);
		textAreaResultado.setBounds(181, 134, 111, 66);
		getContentPane().add(textAreaResultado);*/
		
		
		imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(ViewResult.class.getResource("/Imagenes/mapa-mundi3.png")));
		imagen.setBounds(29, 11, 334, 96);
		getContentPane().add(imagen);
		
		textAreaResultado = new TextArea();
		textAreaResultado.setEditable(false);
		textAreaResultado.setBounds(20, 121, 343, 131);
		getContentPane().add(textAreaResultado);
	}
	
	
	public void hacerVisible(ArrayList<String> list, int opt){
		
		switch(opt){
		case 1:
			labelResultado.setText("La longitud de la/s fronteras en kilometros es: ");
			textAreaResultado.setText(list.get(0));
			break;
		case 2:
			labelResultado.setText("La longitud de la/s costas en kilometros es: ");
			textAreaResultado.setText(list.get(0));
			break;
		case 3:
			labelResultado.setText("Las ciudades principales son: ");
			for(int i=0; i<list.size(); ++i){
				textAreaResultado.setText(textAreaResultado.getText() + "\n" + list.get(i));
				
			}
				
			break;
		}
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
}
