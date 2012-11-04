import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;


public class ViewAddUser extends JDialog {

	private ControllerViews ctrlViews;
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;
	private JLabel titulo;
	private JLabel usuario;	
	private JLabel password;
	private JLabel rePassword;
	private final JLabel mensaje;
	private JButton btnCrearUser;
	private JLabel imagen;
	

	/**
	 * Create the frame.
	 */
	public ViewAddUser(ControllerViews controllerViews) {
		ctrlViews = controllerViews;
		setResizable(false);
		setTitle("Crear usuario");
		setModal(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titulo = new JLabel("Crear Usuario Nuevo");
		titulo.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		titulo.setBounds(54, 96, 182, 16);
		contentPane.add(titulo);
		
		usuario = new JLabel("Username:");
		usuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		usuario.setBounds(54, 141, 93, 19);
		contentPane.add(usuario);
		
		password = new JLabel("Password:");
		password.setFont(new Font("Tahoma", Font.BOLD, 12));
		password.setBounds(54, 179, 93, 19);
		contentPane.add(password);
		
		rePassword = new JLabel("Re-Password:");
		rePassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		rePassword.setBounds(54, 215, 93, 19);
		contentPane.add(rePassword);
		
		userField = new JTextField();
		userField.setBackground(SystemColor.text);
		userField.setBounds(157, 141, 110, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 179, 110, 20);
		contentPane.add(passwordField);
		
		rePasswordField = new JPasswordField();
		rePasswordField.setBounds(157, 215, 110, 20);
		contentPane.add(rePasswordField);
		
		mensaje = new JLabel("");
		mensaje.setFont(new Font("Calibri", Font.PLAIN, 11));
		mensaje.setBounds(54, 280, 317, 16);
		contentPane.add(mensaje);
		
		btnCrearUser = new JButton("Crear Usuario");
		btnCrearUser.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		btnCrearUser.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("_" + userNameTextField.getText() + "_");
				if (userField.getText().equals("")) {
					userField.setBackground(new Color(255, 153, 102));//rojo claro
					passwordField.setBackground(SystemColor.text);
					rePasswordField.setBackground(SystemColor.text);
					mensaje.setText("Nombre de usuario no válido.");
				}
				else if (passwordField.getPassword().length == 0) {
					userField.setBackground(new Color(204, 255, 153));//verde claro
					passwordField.setBackground(new Color(255, 153, 102));//rojo claro
					rePasswordField.setBackground(SystemColor.text);
					mensaje.setText("Password incorrecto.");
				}
				else if (!iguales(passwordField.getPassword(),rePasswordField.getPassword())) {
					userField.setBackground(new Color(204, 255, 153));//verde claro
					passwordField.setBackground(new Color(204, 255, 153));//verde claro
					rePasswordField.setBackground(new Color(255, 153, 102));//rojo claro
					mensaje.setText("Passwords no coinciden.");
				}
				else {
					userField.setBackground(new Color(204, 255, 153));//verde claro
					passwordField.setBackground(new Color(204, 255, 153));//verde claro
					rePasswordField.setBackground(new Color(204, 255, 153));//verde claro
					if(ctrlViews.addNewUser(userField.getText(), passwordField.getText())){
						//msjErrorLabel.setText("Usuario creado correctamente.");
						ctrlViews.sincViewAddUserToViewPreferencias(userField.getText());
					}else
						mensaje.setText("El usuario no se a podido crear");
				}
				
			}
		});
		btnCrearUser.setBounds(157, 246, 110, 23);
		contentPane.add(btnCrearUser);
		
		imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(ViewAddUser.class.getResource("/Imagenes/mapa-mundi3.png")));
		imagen.setBounds(54, 11, 353, 67);
		contentPane.add(imagen);
		
		addWindowListener(new WindowAdapter() {
			/**
			 * Cuando se va ha cerrar la ventana se llama al controlador de vistas para que asuma el control y active la vista que toca
			 */
			public void windowClosing(WindowEvent e) {
				ctrlViews.sincViewAddUserToViewMenuPrincipal();
			}
		});
	}

	private boolean iguales(char[] c1, char[] c2) {
		if (c1.length != c2.length) return false;
		else {
			for(int i = 0; i < c1.length;++i) {
				if (c1[i] != c2[i]) return false;
			}
			return true;
		}
	}
	
	public void hacerVisible(){
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
	
	public void activar(){
		setEnabled(true);
	}
	
	public void desactivar(){
		setEnabled(false);
	}
}
