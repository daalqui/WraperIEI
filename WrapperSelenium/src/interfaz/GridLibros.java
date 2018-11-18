package interfaz;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import com.google.common.collect.Table;



public class GridLibros extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridLibros frame = new GridLibros();
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
	public GridLibros() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.table();
		
	}
		
	private void table() {
		String[] columns = new String[] {
				"Sitio Web", "Titulo", "Autor", "Precio", "Descuento"
		};
		  Object[][] data = new Object[][] {
			  {"Sitio Web", "Titulo", "Autor", "Precio", "Descuento"},
			  {"Amazon", "El quijote", "Miguel de Cervantes", 12, "30 %"}
	      };
		JTable table = new JTable(data, columns);
		contentPane.add(table);
	}

}
