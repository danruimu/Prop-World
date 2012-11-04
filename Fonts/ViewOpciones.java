import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.Box;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewOpciones extends JDialog {

	private ControllerViews ctrlViews;
	private JPanel contentPane;  
	private JButton btnOpc1;
	private JButton btnOpc2;
	private JButton btnOpc3;
	private JButton btnOpc4;
	private JButton btnOpc5;
	private JButton btnOpc6;
	private JLabel imgOpc1;
	private JLabel imgOpc2;
	private JLabel imgOpc3;
	private JLabel imgOpc4;
	private JLabel imgOpc5;
	private JLabel imgOpc6;
	private JButton btnModificarPreferencias;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public ViewOpciones(ControllerViews controllerViews) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				ctrlViews.sincViewOpcionesToViewMenuPrincipal();
			}
		});
		ctrlViews = controllerViews;
		setModal(true);
		setTitle("Elegir opciones");
		setResizable(false);
		setBounds(100, 100, 685, 538);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOpc1 = new JButton("1.Fronteras");
		btnOpc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlViews.sincViewOpcionesToViewSelecPais(1);
			}
		});
		btnOpc1.setBounds(57, 168, 150, 23);
		contentPane.add(btnOpc1);
		
		btnOpc2 = new JButton("2.Costas");
		btnOpc2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlViews.sincViewOpcionesToViewSelecPais(2);
			}
		});
		btnOpc2.setBounds(239, 168, 150, 23);
		contentPane.add(btnOpc2);
		
		btnOpc3 = new JButton("3.Ciudades principales");
		btnOpc3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlViews.sincViewOpcionesToViewListarPrincipales(3);
			}
		});
		btnOpc3.setBounds(423, 168, 150, 23);
		contentPane.add(btnOpc3);
		
		btnOpc4 = new JButton("4.Pintar Ciudades princ");
		btnOpc4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("call a sincViewOpcionesToViewListarPrincipales");
				ctrlViews.sincViewOpcionesToViewListarPrincipales(4);
			}
		});
		btnOpc4.setBounds(67, 365, 150, 23);
		contentPane.add(btnOpc4);
		
		btnOpc5 = new JButton("5.Calcular ruta");
		btnOpc5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlViews.sincViewOpcionesToViewCalcularRuta();
			}
		});
		btnOpc5.setBounds(249, 365, 140, 23);
		contentPane.add(btnOpc5);
		
		
		/*para escalar
	    ImageIcon icono = new ImageIcon(ViewOpciones.class.getResource("/Imagenes/frontera1.png"));
		Image escalado = icono.getImage().getScaledInstance(150,120,icono.getIconHeight());
		icono = new ImageIcon(escalado);
		JLabel lblNewLabel = new JLabel(""); 
		lblNewLabel.setIcon(icono);
		lblNewLabel.setBounds(47, 35, 150, 122);
		contentPane.add(lblNewLabel);*/
		
		imgOpc1 = new JLabel("");
		imgOpc1.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent me) {
					ctrlViews.sincViewOpcionesToViewSelecPais(1);
			 } 
		});
		imgOpc1.setIcon(new ImageIcon(ViewOpciones.class.getResource("/Imagenes/frontera1.png")));
		imgOpc1.setBounds(57, 44, 150, 112);
		contentPane.add(imgOpc1);
		
		imgOpc2 = new JLabel("");
		imgOpc2.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent me) {
					ctrlViews.sincViewOpcionesToViewSelecPais(2);
			 } 
		});
		imgOpc2.setIcon(new ImageIcon(ViewOpciones.class.getResource("/Imagenes/costas1.png")));
		imgOpc2.setBounds(239, 44, 150, 112);
		contentPane.add(imgOpc2);
		
		imgOpc4 = new JLabel("");
		imgOpc4.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent me) {
					ctrlViews.sincViewOpcionesToViewListarPrincipales(4);
			 } 
		});
		imgOpc4.setIcon(new ImageIcon(ViewOpciones.class.getResource("/Imagenes/ciudades1.png")));
		imgOpc4.setBounds(57, 242, 150, 112);
		contentPane.add(imgOpc4);
		
		imgOpc5 = new JLabel("");
		imgOpc5.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent me) {
				 ctrlViews.sincViewOpcionesToViewCalcularRuta();
			 } 
		});
		imgOpc5.setIcon(new ImageIcon(ViewOpciones.class.getResource("/Imagenes/ruta1.png")));
		imgOpc5.setBounds(239, 242, 150, 112);
		contentPane.add(imgOpc5);
		
		btnModificarPreferencias = new JButton("Modificar preferencias");
		btnModificarPreferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlViews.sincViewOpcionesToViewPreferencias(ctrlViews.getUserOnSession());
			}
		});
		btnModificarPreferencias.setBackground(SystemColor.window);
		btnModificarPreferencias.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnModificarPreferencias.setBounds(57, 444, 178, 23);
		contentPane.add(btnModificarPreferencias);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				ctrlViews.sincViewOpcionesToViewMenuPrincipal();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnSalir.setBounds(539, 443, 87, 23);
		contentPane.add(btnSalir);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(133, 147, 1, -9);
		contentPane.add(verticalStrut);
		
		Component glue = Box.createGlue();
		glue.setBounds(81, 103, 1, 1);
		contentPane.add(glue);
		
		imgOpc6 = new JLabel("");
		imgOpc6.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ctrlViews.sincViewOpcionesToViewCalcularPuntos();
			}
		});
		imgOpc6.setIcon(new ImageIcon(ViewOpciones.class.getResource("/Imagenes/toponimos1.png")));
		imgOpc6.setBounds(423, 242, 150, 112);
		contentPane.add(imgOpc6);
		
		btnOpc6 = new JButton("6.Toponimos cercanos");
		btnOpc6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlViews.sincViewOpcionesToViewCalcularPuntos();
			}
		});
		btnOpc6.setBounds(433, 365, 140, 23);
		contentPane.add(btnOpc6);
		
		imgOpc3 = new JLabel("");
		imgOpc3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ctrlViews.sincViewOpcionesToViewListarPrincipales(3);
			}
		});
		imgOpc3.setIcon(new ImageIcon(ViewOpciones.class.getResource("/Imagenes/ciudprinc1.png")));
		imgOpc3.setBounds(423, 45, 150, 112);
		contentPane.add(imgOpc3);
	}
	
	public void hacerVisible(){
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
}
