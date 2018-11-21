package interfaz;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import principal.ChromeAmazonController;
import principal.FirefoxCIController;
import principal.FirefoxFNACController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField tituloField;
	private JTextField autorField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Busqueda de peliculas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloLabel = new JLabel("T\u00EDtulo del libro");
		tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tituloLabel.setBounds(64, 40, 95, 20);
		contentPane.add(tituloLabel);
		
		tituloField = new JTextField();
		tituloField.setColumns(10);
		tituloField.setBounds(177, 43, 140, 20);
		contentPane.add(tituloField);
		
		JLabel autorLabel = new JLabel("Autor");
		autorLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		autorLabel.setBounds(108, 88, 46, 14);
		contentPane.add(autorLabel);
		
		autorField = new JTextField();
		autorField.setColumns(10);
		autorField.setBounds(177, 84, 140, 20);
		contentPane.add(autorField);
		
		JCheckBox amazonBox = new JCheckBox("Amazon");
		amazonBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amazonBox.setBounds(26, 142, 97, 23);
		contentPane.add(amazonBox);
		
		JCheckBox ciBox = new JCheckBox("El Corte Ingl\u00E9s");
		ciBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ciBox.setBounds(27, 178, 135, 23);
		contentPane.add(ciBox);
		
		JCheckBox fnacBox = new JCheckBox("Fnac");
		fnacBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fnacBox.setBounds(26, 211, 97, 23);
		contentPane.add(fnacBox);
		
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( amazonBox.isSelected() == true) {
					new ChromeAmazonController(tituloField.getText() +" "+ autorField.getText() );
				}
				if(fnacBox.isSelected() == true) {
					System.out.println("fnacBox.isSelected() == true");
					new FirefoxFNACController(tituloField.getText() +" "+ autorField.getText());
				}
				if (ciBox.isSelected() == true) {
					System.out.println("ciBox.isSelected() == true");
					new FirefoxCIController(tituloField.getText() +" "+ autorField.getText());
				}
				
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(button.getFont().deriveFont(button.getFont().getStyle() | Font.BOLD, button.getFont().getSize() + 1f));
		button.setBounds(225, 170, 89, 40);
		contentPane.add(button);
	}
	
	

}
