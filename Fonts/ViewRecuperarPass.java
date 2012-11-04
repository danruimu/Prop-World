import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ViewRecuperarPass extends JDialog {

	private ControllerViews ctrlViews;
	private JPanel contentPane;
	private JTextField userAdminField;
	private JTextField userField;
	private JTextField passField;
	private JPasswordField passwordAdminField;
	private MouseListener l;
	private JLabel titulo;
	private JLabel userAdmin;
	private JLabel passAdmin;
	private JLabel user;
	private JLabel password;
	private final JLabel mensaje;
	private JButton btnSolicitarPass;

	/**
	 * Create the frame.
	 */
	public ViewRecuperarPass(ControllerViews controllerViews) {
		ctrlViews = controllerViews;
		setTitle("Recuperar password");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titulo = new JLabel("Recuperar password");
		titulo.setBackground(SystemColor.menu);
		titulo.setFont(new Font("Dialog", Font.BOLD, 16));
		titulo.setBounds(56, 11, 207, 20);
		contentPane.add(titulo);
		
		userAdmin = new JLabel("User Admin");
		userAdmin.setBackground(SystemColor.menu);
		userAdmin.setFont(new Font("Tahoma", Font.BOLD, 12));
		userAdmin.setBounds(69, 57, 94, 20);
		contentPane.add(userAdmin);
		
		passAdmin = new JLabel("Pass Admin");
		passAdmin.setFont(new Font("Tahoma", Font.BOLD, 12));
		passAdmin.setBackground(SystemColor.menu);
		passAdmin.setBounds(69, 94, 94, 20);
		contentPane.add(passAdmin);
		
		user = new JLabel("Usuario");
		user.setFont(new Font("Tahoma", Font.BOLD, 12));
		user.setBackground(SystemColor.menu);
		user.setBounds(69, 131, 94, 20);
		contentPane.add(user);
		
		password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.BOLD, 12));
		password.setBackground(SystemColor.menu);
		password.setBounds(69, 217, 94, 20);
		contentPane.add(password);
		
		userAdminField = new JTextField();
		userAdminField.setFont(new Font("Calibri", Font.PLAIN, 11));
		userAdminField.setBackground(SystemColor.text);
		userAdminField.setBounds(173, 57, 110, 20);
		userAdminField.addMouseListener(l);
		contentPane.add(userAdminField);
		userAdminField.setColumns(10);
		
		passwordAdminField = new JPasswordField();
		passwordAdminField.setFont(new Font("Calibri", Font.PLAIN, 11));
		passwordAdminField.setBackground(SystemColor.text);
		passwordAdminField.setBounds(173, 94, 110, 20);
		contentPane.add(passwordAdminField);
		
		userField = new JTextField();
		userField.setFont(new Font("Calibri", Font.PLAIN, 11));
		userField.setBackground(SystemColor.text);
		userField.setBounds(173, 131, 110, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passField = new JTextField();
		passField.setFont(new Font("Calibri", Font.PLAIN, 11));
		passField.setBackground(SystemColor.menu);
		passField.setEditable(false);
		passField.setBounds(173, 217, 110, 20);
		contentPane.add(passField);
		passField.setColumns(10);
		
		mensaje = new JLabel("");
		mensaje.setBackground(SystemColor.menu);
		mensaje.setFont(new Font("Calibri", Font.PLAIN, 11));
		mensaje.setBounds(79, 192, 315, 14);
		contentPane.add(mensaje);
		
		btnSolicitarPass = new JButton("Solicitar Password");
		btnSolicitarPass.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		btnSolicitarPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!userAdminField.getText().equals("admin")) {
					userAdminField.setBackground(new Color(255, 153, 102));//rojo claro
					passwordAdminField.setBackground(SystemColor.text);
					userField.setBackground(SystemColor.text);
					passField.setBackground(SystemColor.menu);
					passField.setText("");
					mensaje.setText("El nombre del administrador es incorrecto");
				}
				
				else if (!admin(passwordAdminField.getPassword())) {
					userAdminField.setBackground(new Color(204, 255, 153));//verde claro
					passwordAdminField.setBackground(new Color(255, 153, 102));//rojo claro
					userField.setBackground(SystemColor.text);
					passField.setBackground(SystemColor.menu);
					passField.setText("");
					mensaje.setText("El password del administrador es incorrecto");
				}
				
				else if (userField.getText().equals("")) {
					userAdminField.setBackground(new Color(204, 255, 153));//verde claro
					passwordAdminField.setBackground(new Color(204, 255, 153));//verde claro
					userField.setBackground(new Color(255, 153, 102));//rojo claro
					passField.setBackground(SystemColor.menu);
					passField.setText("");
					mensaje.setText("El usuario no existe");
				}
				else {
					userAdminField.setBackground(new Color(204, 255, 153));//verde claro
					passwordAdminField.setBackground(new Color(204, 255, 153));//verde claro
					userField.setBackground(new Color(204, 255, 153));//verde claro
					passField.setBackground(new Color(255, 255, 204));
					String pass = ctrlViews.recuperarPassword(userField.getText());
					passField.setText(pass);
					mensaje.setText("La clave de " + userField.getText() + " es: '" + pass + "'");
				}
				
			}
		});
		btnSolicitarPass.setBounds(69, 158, 170, 23);
		contentPane.add(btnSolicitarPass);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ctrlViews.sincViewRecuperarPassToViewMenuPrincipal();
			}
		});
	}
	
	private boolean admin(char[] c1) {
		char[] c2 = {'a','d','m','i','n'} ;
		if (c1.length !=5) return false;
		else {
			for(int i = 0; i < 5;++i) {
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
