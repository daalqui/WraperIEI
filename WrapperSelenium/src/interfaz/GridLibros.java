package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import principal.InstanciaLibro;

public class GridLibros extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstanciaLibro elQuijote = new InstanciaLibro("Amazon", "El quijote", "Miguel de Cervantes", 30.0, 30.0);
					InstanciaLibro lib2 = new InstanciaLibro("","","",null,null);
					GridLibros frame = new GridLibros(elQuijote);
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
	public GridLibros(InstanciaLibro libro) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//this.table();
		
		JTable tabla = construirTabla(libro);
		contentPane.add(tabla);
		
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
	
	private static JTable construirTabla(InstanciaLibro libro) {
		String[] columns = getNombreColumnas();
		Object[][] data = new Object[2][5]; /*{
			  {"Sitio Web", "Titulo", "Autor", "Precio", "Descuento"},
			  {"Amazon", "El quijote", "Miguel de Cervantes", 12, "30 %"}
	      };*/
	    data[0] = getPrimeraColumna();
	    Object[] columna = libro.toArray();
	    data[1] = columna;
	    JTable table = new JTable(data, columns);
	    return table;
	}
	
	private static String[] getNombreColumnas() {
		String[] columns = new String[] {
				"Sitio Web", "Titulo", "Autor", "Precio", "Descuento"
		};
		return columns;
	}
	
	private static Object[] getPrimeraColumna() {
		Object[] res = {"Sitio Web", "Titulo", "Autor", "Precio", "Descuento"};
		return res;
	}
}
