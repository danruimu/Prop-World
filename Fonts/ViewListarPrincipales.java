import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ViewListarPrincipales extends JDialog {

	private ControllerViews ctrlViews;
	private final JPanel contentPanel = new JPanel();
	private JLabel imagen;
	private JTextPane titulo;
	private final JComboBox paiscomboBox = new JComboBox();
	private JLabel subtitulo;
	private JLabel distanciaMaxCostafrontera;
	private final JTextField distCostFront;
	private final JTextField distop;
	private JLabel distMaxTop;
	private final JLabel mensaje;
	private JButton btnCalcula;
	private int opt;
	
	
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
	 * Create the dialog.
	 */
	public ViewListarPrincipales(ControllerViews controllerViews) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ctrlViews.sincViewListarPrincipalesToViewOpciones();
			}
		});
		ctrlViews = controllerViews;
		setBounds(100, 100, 450, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(ViewListarPrincipales.class.getResource("/Imagenes/mapa-mundi3.png")));
		imagen.setBounds(52, 11, 310, 82);
		contentPanel.add(imagen);
		
		titulo = new JTextPane();
		titulo.setText("Selecciona un pais:");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		titulo.setEditable(false);
		titulo.setBackground(SystemColor.menu);
		titulo.setBounds(41, 92, 150, 20);
		contentPanel.add(titulo);
		
		/*String[] petStrings = { "Afghanistan",
                "Albania",
                "Algeria",
                "American_Samoa", 
                "Andorra",
                "Angola",
                "Anguilla",
                "Antarctica",
                "Antigua_and_Barbuda",
                "Argentina",
                "Armenia",
                "Aruba",
                "Australia",
                "Austria", 
                "Azerbaijan",
                "Bahamas,_The",
                "Bahrain",
                "Baker_Island",
                "Bangladesh",
                "Barbados",
                "Belgium",
                "Belize",
                "Benin",
                "Bermuda",
                "Bhutan", 
                "Bolivia",
                "Bosnia_and_Herzegovina",
                "Botswana",
                "Bouvet_Island",
                "Brazil",
                "British_Indian_Ocean_Territory",
                "British_Virgin_Islands",
                "Brunei",
                "Bulgaria",
                "Burkina_Faso",
                "Burundi",
                "Byelarus",
                "Cambodia", 
                "Cameroon",
                "Canada",
                "Cape_Verde",
                "Cayman_Islands",
                "Central_African_Republic",
                "Chad",
                "Chile",
                "China",
                "Christmas_Island",
                "Cocos_(Keeling)_Islands",
                "Colombia", 
                "Comoros",
                "Congo",
                "Cook_Islands",
                "Costa_Rica",
                "Croatia",
                "Cuba",
                "Cyprus",
                "Czech_Republic",
                "Denmark", 
                "Djibouti",
                "Dominica",
                "Dominican_Republic",
                "Ecuador",
                "Egypt",
                "El_Salvador",
                "Equatorial_Guinea",
                "Eritrea",
                "Estonia",
                "Ethiopia",
                "Falkland_Islands_(Islas_Malvinas)",
                "Faroe_Islands",
                "Federated_States_of_Micronesia",
                "Fiji",
                "Finland",
                "France",
                "French_Guiana",
                "French_Polynesia",
                "French_Southern_&_Antarctic_Lands",
                "Gabon",
                "Gambia,_The",
                "Georgia",
                "Germany",
                "Ghana",
                "Gibraltar",
                "Greece",
                "Greenland",
                "Grenada",
                "Guadeloupe",
                "Guam",
                "Guatemala",
                "Guernsey",
                "Guinea",
                "Guinea-Bissau",
                "Guyana",
                "Haiti",
                "Honduras",
                "Hungary",
                "Iceland",
                "India",
                "Indonesia",
                "Iran",
                "Iraq",
                "Ireland",
                "Israel",
                "Italy",
                "Ivory_Coast",
                "Jamaica",
                "Jan_Mayen",
                "Japan",
                "Jarvis_Island",
                "Jersey",
                "Johnston_Atoll",
                "Jordan",
                "Juan_De_Nova_Island",
                "Kazakhstan",
                "Kenya",
                "Kiribati",
                "North_Korea",
                "South_Korea",
                "Kuwait",
                "Kyrgyzstan",
                "Laos",
                "Latvia",
                "Lebanon",
                "Lesotho",
                "Liberia",
                "Libya",
                "Liechtenstein",
                "Lithuania",
                "Luxembourg",
                "Macau",
                "Macedonia",
                "Madagascar",
                "Malawi",
                "Malaysia",
                "Maldives",
                "Mali",
                "Malta",
                "Marshall_Islands",
                "Martinique",
                "Mauritania",
                "Mauritius",
                "Mayotte",
                "Mexico",
                "Midway_Islands",
                "Moldova",
                "Monaco",
                "Mongolia",
                "Montenegro",
                "Montserrat",
                "Morocco",
                "Mozambique",
                "Myanmar_(Burma)",
                "Namibia",
                "Nauru",
                "Nepal",
                "Netherlands",
                "Netherlands_Antilles",
                "New_Caledonia",
                "New_Zealand",
                "Nicaragua",
                "Niger",
                "Nigeria",
                "Niue",
                "Norfolk_Island",
                "Northern_Mariana_Islands",
                "Norway",
                "Oman",
                "Pacific_Islands_(Palau)",
                "Pakistan",
                "Panama",
                "Papua_New_Guinea",
                "Paracel_Islands",
                "Paraguay",
                "Peru",
                "Philippines",
                "Pitcairn_Island",
                "Poland",
                "Portugal",
                "Puerto_Rico",
                "Qatar",
                "Reunion",
                "Romania",
                "Russia",
                "Rwanda",
                "Samoa",
                "San_Marino",
                "Sao_Tome_and_Principe",
                "Saudi_Arabia",
                "Senegal",
                "Serbia",
                "Seychelles",
                "Sierra_Leone",
                "Singapore",
                "Slovakia",
                "Slovenia",
                "Solomon_Islands",
                "Somalia",
                "South_Africa",
                "South_Georgia_and_the_South_Sandwi",
                "Spain",
                "Spratly_Islands",
                "Sri_Lanka",
                "St._Helena",
                "St._Kitts_and_Nevis",
                "St._Helena",
                "St._Pierre_and_Miquelon",
                "St._Vincent_and_the_Grenadines",
                "Sudan",
                "Suriname",
                "Svalbard",
                "Swaziland",
                "Sweden",
                "Switzerland",
                "Syria",
                "Taiwan",
                "Tajikistan",
                "Tanzania,_United_Republic_of",
                "Thailand",
                "Togo",
                "Tokelau",
                "Tonga",
                "Trinidad_and_Tobago",
                "Tunisia",
                "Turkey",
                "Turkmenistan",
                "Turks_and_Caicos_Islands",
                "Tuvalu",
                "Uganda",
                "Ukraine",
                "United_Arab_Emirates",
                "United_Kingdom",
                "United_States",
                "Uruguay",
                "Uzbekistan",
                "Vanuatu",
                "Venezuela",
                "Vietnam",
                "Virgin_Islands",
                "Yemen",
                "Zaire",
                "Zambia",
                "Zimbabwe"};*/
		//paiscomboBox = new JComboBox(petStrings);
		paiscomboBox.setBounds(41, 123, 216, 20);
		ArrayList<String> listPaises = new ArrayList<String>();
		listPaises = ctrlViews.getPaises();
		String [] strArrayPaises = new String[listPaises.size()];
		listPaises.toArray(strArrayPaises);
		paiscomboBox.removeAllItems();
		for(int i=0; i<listPaises.size(); ++i){
			paiscomboBox.addItem(strArrayPaises[i]);
		}
		paiscomboBox.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				//String seleccionado=(String)comboBox.getSelectedItem();
				String selec = (String)paiscomboBox.getSelectedItem();
			}

					
		});
		contentPanel.setLayout(null);
		paiscomboBox.setSelectedIndex(199);
		paiscomboBox.setMaximumRowCount(10);
		contentPanel.add(paiscomboBox);
		
		subtitulo = new JLabel("Introduce las distancias:");
		subtitulo.setBounds(41, 154, 380, 20);
		subtitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPanel.add(subtitulo);
		
		distanciaMaxCostafrontera = new JLabel("Distancia max a costa/frontera");
		distanciaMaxCostafrontera.setBounds(41, 185, 216, 17);
		contentPanel.add(distanciaMaxCostafrontera);
		
		distCostFront = new JTextField();
		distCostFront.setBounds(239, 185, 77, 17);
		distCostFront.setBackground(SystemColor.text);
		distCostFront.setFont(new Font("Calibri", Font.PLAIN, 11));
		contentPanel.add(distCostFront);
		distCostFront.setColumns(10);
				
		
		distop = new JTextField();
		distop.setBounds(239, 213, 77, 17);
		distop.setBackground(SystemColor.text);
		distop.setFont(new Font("Calibri", Font.PLAIN, 11));
		contentPanel.add(distop);
		distop.setColumns(10);
		
		
		distMaxTop = new JLabel("Distancia max a toponimo");
		distMaxTop.setBounds(41, 213, 150, 17);
		contentPanel.add(distMaxTop);
		
		
		mensaje = new JLabel("");
		mensaje.setBounds(41, 241, 371, 14);
		mensaje.setFont(new Font("Calibri", Font.PLAIN, 11));
		mensaje.setBackground(SystemColor.menu);
		contentPanel.add(mensaje);
		
		btnCalcula = new JButton("Calcular");
		btnCalcula.setBounds(326, 210, 100, 23);
		btnCalcula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (distCostFront.getText().length() == 0) {
					distCostFront.setBackground(new Color(255, 153, 102));//rojo claro.
					distop.setBackground(SystemColor.text);
					mensaje.setText("Distancia a costa/frontera incorecta");
				}
				else if (!isNumeric(distCostFront.getText())) {
					distCostFront.setBackground(new Color(255, 153, 102));//rojo claro.
					distop.setBackground(SystemColor.text);
					mensaje.setText("Distancia a costa/frontera incorecta");
				}
				else if (!isNumeric(distop.getText())) {
					distCostFront.setBackground(new Color(204, 255, 153));//verde claro.
					distop.setBackground(new Color(255, 153, 102));//rojo claro.
					mensaje.setText("Distancia a toponimo incorrecta");
				}
				else {
					distCostFront.setBackground(SystemColor.text);
					distop.setBackground(SystemColor.text);
					String selectedPais = paiscomboBox.getSelectedItem().toString();
					switch(opt){
					case 3:
						ctrlViews.sincViewListarPrincipalesToViewResult(selectedPais, distCostFront.getText(), distop.getText());
						break;
					case 4:
						ctrlViews.sincViewListarPrincipalesToViewDrawMap(selectedPais, distCostFront.getText(), distop.getText());
						break;
					default:
						System.out.println("Opcion no valida en ViewListarPrincipal");
						break;
					
					}
									
				}
			}
		});
		contentPanel.add(btnCalcula);
		
		}
	
	public void hacerVisible(int option){
		opt = option;
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
}




