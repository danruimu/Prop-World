import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.text.Style;
import java.awt.event.MouseMotionAdapter;


public class ViewMenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private ControllerViews ctrlViews;
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JLabel lblCrearrr;
	private JLabel titulo;
	private JLabel usuario;
	private JLabel password;
	private final JLabel mensaje;
	private JButton btnEntrar;
	private JButton recPassword;
	private JLabel imagen;
	private final JLabel borrarUser;
	private final JLabel crearUser;
	private JLabel iconoCrear;
	private JLabel iconoBorrar;
	
	
	/**
	 * Create the frame.
	 * @param controllerViews 
	 */
	public ViewMenuPrincipal(ControllerViews controllerViews) {
		ctrlViews = controllerViews;
		setTitle("Acceso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titulo = new JLabel("¡¡Bienvenido!!");
		titulo.setFont(new Font("Dialog", Font.BOLD, 16));
		titulo.setBackground(SystemColor.menu);
		titulo.setBounds(74, 101, 145, 20);
		contentPane.add(titulo);
		
		usuario = new JLabel("Usuario:");
		usuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		usuario.setBackground(SystemColor.menu);
		usuario.setBounds(94, 155, 56, 20);
		contentPane.add(usuario);
		
		password = new JLabel("Password:");
		password.setFont(new Font("Tahoma", Font.BOLD, 12));
		password.setBackground(SystemColor.menu);
		password.setBounds(94, 186, 72, 17);
		contentPane.add(password);
		
		mensaje = new JLabel("");
		mensaje.setFont(new Font("Calibri", Font.PLAIN, 11));
		mensaje.setBounds(94, 224, 354, 14);
		contentPane.add(mensaje);		
		
		userField = new JTextField();
		userField.setFont(new Font("Calibri", Font.PLAIN, 11));
		userField.setColumns(10);
		userField.setBackground(SystemColor.text);
		userField.setBounds(198, 156, 86, 20);
		contentPane.add(userField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 11));
		passwordField.setBackground(SystemColor.text);
		passwordField.setBounds(198, 185, 86, 20);
		contentPane.add(passwordField);
	
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Comprobar si el usuario existe.
				if (userField.getText().equals("")) {
					userField.setBackground(new Color(255, 153, 102));//rojo claro
					passwordField.setBackground(SystemColor.text);
					mensaje.setText("El usuario es incorrecto");
				}
				else if (passwordField.getPassword().length == 0) {
					userField.setBackground(new Color(204, 255, 153));//verde claro
					passwordField.setBackground(new Color(255, 153, 102));//rojo claro
					mensaje.setText("La contraseña es incorrecta");
				}
				else {
					mensaje.setText("");
					//TODO: Validar usuario. Si el user existe hacer la llamada al controllerViews para que pinte la vista de opciones sino mensaje en label de user no valido
					if(ctrlViews.autenticarUser(userField.getText(), passwordField.getText())){
						userField.setBackground(new Color(204, 255, 153));//verde claro
						passwordField.setBackground(new Color(204, 255, 153));//verde claro
						ctrlViews.sincViewMenuPrincipalToViewOpciones();
					}else{
						userField.setBackground(new Color(255, 153, 102));//rojo claro
						passwordField.setBackground(new Color(255, 153, 102));//rojo claro
						mensaje.setText("Usuario y/o password no valido");
					}
				}
			}
		});
		btnEntrar.setBounds(294, 184, 89, 23);
		contentPane.add(btnEntrar);
		
		recPassword = new JButton("¿Olvidó su password?");
		recPassword.setHorizontalAlignment(SwingConstants.LEFT);
		recPassword.setToolTipText("");
		recPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		recPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlViews.sincViewMenuPrincipalToViewRecuperarPass();
			}
		});
		recPassword.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		recPassword.setBounds(294, 154, 154, 23);
		contentPane.add(recPassword);
		
		/*JButton btnBorrarUsuario = new JButton("Borrar usuario");
		btnBorrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnBorrarUsuario.setBounds(294, 271, 113, 23);
		contentPane.add(btnBorrarUsuario);*/
		
		imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(ViewMenuPrincipal.class.getResource("/Imagenes/mapa-mundi3.png")));
		imagen.setBounds(73, 11, 382, 90);
		contentPane.add(imagen);
		
		borrarUser = new JLabel("Borrar Usuario");
		borrarUser.setForeground(Color.BLUE);
		borrarUser.setFont(new Font("Tahoma", Font.ITALIC, 14));
		borrarUser.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent me) {
				 ctrlViews.sincViewMenuPrincipalToViewDelUser();
			 } 
			@Override
			public void mouseEntered(MouseEvent e) {
				borrarUser.setForeground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				borrarUser.setForeground(Color.BLUE);
			}
		});
		borrarUser.setBounds(328, 274, 97, 15);
		contentPane.add(borrarUser);
		
		crearUser = new JLabel("Crear nuevo usuario");
		/*lblCrearNuevoUsuario.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				lblCrearNuevoUsuario.setForeground(Color.GREEN);
			}
		});*/
		
		crearUser.setForeground(Color.BLUE);
		crearUser.setFont(new Font("Tahoma", Font.ITALIC, 14));
		crearUser.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent me) {
				 ctrlViews.sincViewMenuPrincipalToViewAddUser();
			 } 
			@Override
			public void mouseEntered(MouseEvent arg0) {
				crearUser.setForeground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				crearUser.setForeground(Color.BLUE);
			}
		});
		
		crearUser.setBounds(74, 273, 145, 14);
		contentPane.add(crearUser);
		
		iconoCrear = new JLabel("");
		iconoCrear.setIcon(new ImageIcon(ViewMenuPrincipal.class.getResource("/Imagenes/cara_feliz1.png")));
		iconoCrear.setBounds(206, 267, 28, 28);
		contentPane.add(iconoCrear);
		
		iconoBorrar = new JLabel("");
		iconoBorrar.setIcon(new ImageIcon(ViewMenuPrincipal.class.getResource("/Imagenes/cara_triste1.png")));
		iconoBorrar.setBounds(424, 267, 28, 28);
		contentPane.add(iconoBorrar);
	}
	
	public void hacerVisible(){
		//pack();	// Hacia la ventana muy pequeña y no se veia nada ... raro
		setVisible(true);
	}
	
	public void activar(){
		setEnabled(true);
	}
	
	public void desactivar(){
		setEnabled(false);
	}
}
