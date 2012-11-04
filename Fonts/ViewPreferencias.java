import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ViewPreferencias extends JDialog {

	private ControllerViews ctrlViews;
	private JPanel contentPane;
	private JTextField numHabField;
	private boolean isNewUser; 	// Es cierto si venimos de la vista de addUser.
	private String username;	// Es el nombre de usuario sobre el cual vamos a modificar sus preferencias.
	private JLabel titulo;
	private JLabel numeroHab;
	private JLabel codigotoponimo;
	private final JComboBox listatoponimo;
	private final JLabel mensaje;
	private JButton btnGuardar;
	private JButton btnAyuda;
	
	private boolean isNumeric(String numHab) {
		if (numHab.length() == 0) return false;
		else {
			for(int i = 0; i < numHab.length(); i++) {
				if(numHab.charAt(i) < '0' || numHab.charAt(i) > '9')
					return false;
			}
			return true;
		}
	}

	/**
	 * Create the frame.
	 * @param controllerViews 
	 */
	public ViewPreferencias(ControllerViews controllerViews) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ctrlViews.sincViewPreferenciasTo(isNewUser);
			}
		});
		ctrlViews = controllerViews;
		setTitle("Preferencias");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 447, 255);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titulo = new JLabel("Preferencias");
		titulo.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 16));
		titulo.setBackground(SystemColor.menu);
		titulo.setBounds(53, 11, 144, 20);
		contentPane.add(titulo);
		
		numeroHab = new JLabel("N\u00FAmero de habitantes:");
		numeroHab.setBackground(SystemColor.menu);
		numeroHab.setFont(new Font("Tahoma", Font.BOLD, 12));
		numeroHab.setBounds(68, 57, 148, 20);
		contentPane.add(numeroHab);
		
		codigotoponimo = new JLabel("C\u00F3digo de top\u00F3nimo:");
		codigotoponimo.setFont(new Font("Tahoma", Font.BOLD, 12));
		codigotoponimo.setBackground(SystemColor.menu);
		codigotoponimo.setBounds(68, 99, 148, 20);
		contentPane.add(codigotoponimo);
		
		numHabField = new JTextField();
		numHabField.setBackground(SystemColor.text);
		numHabField.setBounds(226, 57, 130, 20);
		contentPane.add(numHabField);
		numHabField.setColumns(10);
		
		listatoponimo = new JComboBox();
		listatoponimo.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		listatoponimo.setMaximumRowCount(10);
		listatoponimo.setModel(new DefaultComboBoxModel(new String[] {
				"AIRP",
				"HLL",
				"LK",
				"MT",
				"MTS",
				"PK",
		}));
		listatoponimo.setBounds(226, 100, 130, 20);
		contentPane.add(listatoponimo);
		
		mensaje = new JLabel("");
		mensaje.setFont(new Font("Calibri", Font.PLAIN, 11));
		mensaje.setBackground(SystemColor.menu);
		mensaje.setBounds(69, 170, 275, 14);
		contentPane.add(mensaje);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isNumeric(numHabField.getText())) {
					//int num = Integer.parseInt(numHabTextField.getText());
					numHabField.setBackground(new Color(255, 153, 102)); //rojo claro
					listatoponimo.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
					mensaje.setText("El número de habitantes introducido es incorrecto");
				}
				else {
					numHabField.setBackground(new Color(204, 255, 153)); //verde claro
					listatoponimo.setBackground(new Color(204, 255, 153));
					Integer numHab = new Integer(numHabField.getText());
					if(ctrlViews.updatePreferences(username, numHab, listatoponimo.getSelectedItem().toString()))
						ctrlViews.sincViewPreferenciasTo(isNewUser);
					else
						mensaje.setText("No se han podido actualizar las preferencias");
				}
			}
		});
		btnGuardar.setBounds(105, 136, 89, 23);
		contentPane.add(btnGuardar);
		
		btnAyuda = new JButton("Ayuda");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Preguntar al profe si esto estaria bien aqui o tendria que estar en CntrlrViews
				ViewAyuda va = new ViewAyuda();
				va.setVisible(true);
			}
		});
		btnAyuda.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		btnAyuda.setBounds(226, 136, 89, 23);
		contentPane.add(btnAyuda);
	}
	
	public void hacerVisible(String user, boolean b){
		isNewUser = b;
		username = user;
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
}
