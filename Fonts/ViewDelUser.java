import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ViewDelUser extends JDialog {

	private ControllerViews ctrlViews;
	private final JPanel contentPanel = new JPanel();
	private JTextField usuarioField;
	private JTextField passwordField;
	private JLabel mensaje;
	private JLabel usuario;
	private JLabel password;
	private JLabel titulo;
	private JButton btnBorrar;
	private JLabel imagen;

	/**
	 * Create the dialog.
	 */
	public ViewDelUser(ControllerViews controllerViews) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ctrlViews.sincViewDelUserToViewMenuPrincipal();
			}
		});
		ctrlViews = controllerViews;
		setTitle("Borrar Usuario");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		usuarioField = new JTextField();
		usuarioField.setBounds(145, 134, 86, 20);
		contentPanel.add(usuarioField);
		usuarioField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(145, 179, 86, 20);
		contentPanel.add(passwordField);
		passwordField.setColumns(10);
		
		usuario = new JLabel("Usuario:");
		usuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		usuario.setBounds(54, 134, 67, 17);
		contentPanel.add(usuario);
		
		password = new JLabel("Password:");
		password.setFont(new Font("Tahoma", Font.BOLD, 12));
		password.setBounds(54, 180, 67, 14);
		contentPanel.add(password);
		
		titulo = new JLabel("Borrar Usuario");
		titulo.setFont(new Font("Dialog", Font.BOLD, 16));
		titulo.setBounds(54, 86, 162, 27);
		contentPanel.add(titulo);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrlViews.delUser(usuarioField.getText(), passwordField.getText())){
					mensaje.setText("El usuario se a eliminado correctamente");
				}else{
					mensaje.setText("El usuario no se a podido eliminar");
				}
			}
		});
		btnBorrar.setBounds(257, 178, 89, 23);
		contentPanel.add(btnBorrar);
		
		imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(ViewDelUser.class.getResource("/Imagenes/mapa-mundi3.png")));
		imagen.setBounds(54, 11, 310, 69);
		contentPanel.add(imagen);
		
		mensaje = new JLabel("");
		mensaje.setBounds(54, 230, 337, 16);
		contentPanel.add(mensaje);
	}
	
	public void hacerVisible(){
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
}
