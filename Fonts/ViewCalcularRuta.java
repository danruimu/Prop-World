import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class ViewCalcularRuta extends JDialog {

	private ControllerViews ctrlViews;
	private final JPanel contentPanel = new JPanel();
	private JComboBox ciudadOrigField = new JComboBox();
	private JComboBox ciudadDestField = new JComboBox();
	private JLabel imagen;
	private JTextPane titulo;
	private final JComboBox paisComboBox = new JComboBox();
	private JButton btnSeleccionar;
	private JLabel ciudadOrig;
	private JLabel ciudadDest;
	private JLabel subtitulo;

	/**
	 * Create the dialog.
	 * @param controllerViews 
	 */
	public ViewCalcularRuta(ControllerViews controllerViews) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				ctrlViews.sincViewCalcularRutaToViewOpciones();
			}
		});
		ctrlViews = controllerViews;
		setResizable(false);
		setTitle("Calcular ruta");
		setBounds(100, 100, 470, 315);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(ViewCalcularRuta.class.getResource("/Imagenes/mapa-mundi3.png")));
		imagen.setBounds(53, 11, 310, 82);
		contentPanel.add(imagen);
		
		titulo = new JTextPane();
		titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		titulo.setBackground(SystemColor.menu);
		titulo.setEditable(false);
		titulo.setText("Selecciona un pais:");
		titulo.setBounds(53, 109, 150, 20);
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
		//paisComboBox = new JComboBox(petStrings);
		ArrayList<String> listPaises = new ArrayList<String>();
		listPaises = ctrlViews.getPaises();
		String [] strArrayPaises = new String[listPaises.size()];
		listPaises.toArray(strArrayPaises);
		paisComboBox.removeAllItems();
		for(int i=0; i<listPaises.size(); ++i){
			paisComboBox.addItem(strArrayPaises[i]);
		}
		paisComboBox.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				//String seleccionado=(String)comboBox.getSelectedItem();
				String selec = (String)paisComboBox.getSelectedItem();
				System.out.println("Vamos a pedir las ciudades del pais: '" + selec + "'");
				ArrayList<String> list = new ArrayList<String>();
				System.out.println("hago el call al controlador de vistas");
				list = ctrlViews.getCiudadesOfPais(selec);
				System.out.println("Recupero la lista desde la clase");
				
				String [] strArray = new String[list.size()];
				list.toArray(strArray);
				ciudadOrigField.removeAllItems();
				ciudadDestField.removeAllItems();
				
				for(int i=0; i<list.size(); ++i){
					//System.out.println("Añado: '" + strArray[i] + "'");
					ciudadOrigField.addItem(strArray[i]);
					ciudadDestField.addItem(strArray[i]);
				}
			}					
		});
		paisComboBox.setSelectedIndex(199);
		paisComboBox.setMaximumRowCount(10);
		paisComboBox.setBounds(53, 140, 216, 20);
		contentPanel.add(paisComboBox);
		
		btnSeleccionar = new JButton("Calcular ruta");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedPais = paisComboBox.getSelectedItem().toString();
				ctrlViews.sincViewCalcularRutaToViewDrawMap(selectedPais, ciudadOrigField.getSelectedItem().toString(), ciudadDestField.getSelectedItem().toString());
			}
		});
		btnSeleccionar.setBounds(310, 217, 121, 23);
		contentPanel.add(btnSeleccionar);
		
		ciudadOrig = new JLabel("Ciudad origen");
		ciudadOrig.setBounds(53, 193, 100, 17);
		contentPanel.add(ciudadOrig);
		
		ciudadDest = new JLabel("Ciudad destino");
		ciudadDest.setBounds(53, 221, 100, 17);
		contentPanel.add(ciudadDest);
		
		//ciudadOrigField = new JComboBox();
		ciudadOrigField.setFont(new Font("Calibri", Font.PLAIN, 11));
		ciudadOrigField.setBounds(174, 193, 121, 17);
		contentPanel.add(ciudadOrigField);
		
		//ciudadDestField = new JComboBox();
		ciudadDestField.setFont(new Font("Calibri", Font.PLAIN, 11));
		ciudadDestField.setBounds(174, 220, 121, 17);
		contentPanel.add(ciudadDestField);
		
		subtitulo = new JLabel("Introduce el origen y el desino para la ruta:");
		subtitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		subtitulo.setBounds(51, 168, 380, 20);
		contentPanel.add(subtitulo);
	}
	
	public void hacerVisible(){
		setVisible(true);
	}
	
	public void hacerInvisible(){
		setVisible(false);
	}
}
