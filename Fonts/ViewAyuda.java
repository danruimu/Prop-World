import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.SystemColor;
import java.awt.Font;


public class ViewAyuda extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewAyuda dialog = new ViewAyuda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewAyuda() {
		setResizable(false);
		setModal(true);
		setTitle("Ayuda");
		setBounds(100, 100, 1000, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnFllsWaterfallsA = new JTextPane();
		txtpnFllsWaterfallsA.setEditable(false);
		txtpnFllsWaterfallsA.setText("AIRP\tAirport\tA place where aircraft regularly land and take off, with runways, navigational aids, and major facilities for the commercial handling of passengers and cargo.\r\nHLL\tHill\tA rounded elevation of limited extent rising above the surrounding land with local relief of less than 300m.\r\nLK\tLake\tA large inland body of standing water.\r\nMT\tMountain\tAn elevation standing high above the surrounding area with small summit area, steep slopes and local relief of 300m or more.\r\nMTS\tMountains\tA mountain range or a group of mountains or high ridges.\r\nSEA\tSea\tA large body of salt water more or less confined by continuous.\r\nPK\tPeak\tA pointed elevation atop a mountain, ridge, or other hypsographic feature.");
		txtpnFllsWaterfallsA.setBounds(0, 0, 994, 155);
		contentPanel.add(txtpnFllsWaterfallsA);
	}
}
