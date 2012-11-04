import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.SystemColor;


public class ViewAyudaPrincipal extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewAyudaPrincipal dialog = new ViewAyudaPrincipal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewAyudaPrincipal() {
		setModal(true);
		setResizable(false);
		setTitle("Manual de ayuda");
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setBackground(SystemColor.text);
		textArea.setEditable(false);
		textArea.setText("Aqui ira el manual de ayuda si finalmente se hace...");
		textArea.setBounds(0, 0, 994, 472);
		getContentPane().add(textArea);
	}
}
